package leetcode;
import java.util.*;

/**
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * Created by god on 2019/4/26.
 */
public class LongestSubString {

    public static  void main(String[] args){

        System.out.print(getLenOfLongestSubString("pwwkew"));

    }

    static  int  getLenOfLongestSubString(String seq){
        char[] chars = seq.toCharArray();
        if(chars.length == 0){
            return 0;
        }
        Map<Character,Integer> charDic = new HashMap<>();
        int i = 0,j = 0,tempLen=0;
        for(char c :chars){
            if(charDic.containsKey(c)){
                if(charDic.get(c) > i){
                    i = charDic.get(c);
                }
            }
            if(tempLen < j-i+1){
                tempLen = j-i+1;
            }
            charDic.put(c,j+1);
            j++;
        }

        return tempLen;

    }

    static  int  getLenOfLongestSubString2(String s){
        if(s.length() == 0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int i = 0,j=0 ,ans = 0;
        while(i< s.length() && j<s.length()){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }


        return ans;

    }

}
