package leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * Created by god on 2019/4/29.
 */
public class Palindrome {

    public  static  void  main(String[] args){

        System.out.print(manacher("sdssdssssssssssssssssssssss"));

    }

    public static  String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }


    /**
     * 中心扩展算法
     * 中心有两个情况（奇数回文left = right和偶数回文不同 left = right+1）
     * @param s
     * @param left
     * @param right
     * @return 返回能扩展的的最大长度
     */
    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 马拉车算法
     * 时间复杂度O(n)
     * @param s
     * @return
     */
    public static  String manacher(String s){
        if(s.length() == 0){
            return "";
        }
        String s_n = getNewString(s);//插入字符串首尾和之间插入"#",回文性质不变（字符串长度变为奇数）
        int len = s_n.length();
        int[] p = new int[len];//p[i]表示以为中心时最大回文长度
        int C = 0;//中心
        int R = 0;//回文右侧最大位置
        //int L = 0;// 回文左侧最大位置L = 2*C -R
        for(int i = 1;i < len-1;i++){
            int i_mirror = 2*C - i;
            int diff = R -i;
            if(diff >= 0 && i_mirror>=0){
                if(p[i_mirror] < diff ){//利用对称性直接获取p[i]
                    p[i] = p[i_mirror];
                } else {//失去对称性则通过字符判断
                    p[i] = diff;
                    while (p[i]+i< len-1 && p[i] <=i-1 && s_n.charAt(p[i]+i+1) == s_n.charAt(i-p[i]-1) ){
                        p[i]++;
                    }
                    C = i;
                    R = i + p[i];
                }
            } else {//以i为中心扩展计算
                p[i] = 0;
                while (p[i]+i< len-1 && p[i] <=i-1  && s_n.charAt(p[i]+i+1) == s_n.charAt(i-p[i]-1) ){
                    p[i]++;
                }
                C = i;
                R = i + p[i];
            }
        }
        int maxIndex = 0;
        int maxLen = 0;
        for(int i = 0;i<len;i++){
            if(p[i] > maxLen){
                maxLen = p[i];
                maxIndex = i;
            }
        }
        return s.substring((maxIndex-maxLen)/2,(maxIndex+maxLen-1)/2+1);

    }

    private  static String getNewString(String s){
        char[] chars = s.toCharArray();
        StringBuffer buffer = new StringBuffer("#");//a idfier char not in s
        for(char c :chars){
            buffer.append(c);
            buffer.append("#");
        }
        return  buffer.toString();
    }

    private  static  boolean isPalindrome(String s){
        int len = s.length();
        int i = 0,j = 0;
        if(len == 0){
            return  false;
        }
        if(len == 1){
            return true;
        }
        if((len & 1) == 0){
            j = len/2;
            i= len/2 -1;
        } else {
            i = len/2 -1;
            j = len/2 +1;
        }
        while(i != -1 || j != len ){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i--;
            j++;
        }
     return true;
    }
}
