package leetcode;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 '*' 匹配零个或多个前面的元素。

 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

 说明:

 s 可能为空，且只包含从 a-z 的小写字母。
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。

 We define dp[i][j] to be true if s[0..i) matches p[0..j) and false otherwise. The state equations will be:

 dp[i][j] = dp[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
 dp[i][j] = dp[i][j - 2], if p[j - 1] == '*' and the pattern repeats for 0 time;
 dp[i][j] = dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*' and the pattern repeats for at least 1 time.
 * Created by god on 2019/5/8.
 */
public class Match {

    public static  void  main(String[] args){


        System.out.print(isMatch2("mississippi","mis*is*ip*."));
        System.out.print(isMatch2("aab","c*a*b"));
    }


    public static boolean isMatch(String s, String p) {
        if(p == null || s == null){
            return false;
        }
        if(p.length() == 0){
            return s.length() == 0? true:false;
        }
        //记录匹配结果d[i][j]表示s[0,i)匹配p[0,j)时的结果
        boolean[][]  dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 0;i<s.length()+1;i++){
            for(int j = 1;j<p.length()+1;j++){
                if(p.charAt(j-1) == '*'){
                    dp[i][j] =dp[i][j-2] || (i>0 && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2)|| p.charAt(j - 2) == '.'));
                } else {
                    dp[i][j] =i>0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //"aab","c*a*b" 不能正确匹配
    public static boolean isMatch2(String s, String p) {
        if(p == null || s == null){
            return false;
        }
        if(p.length() == 0){
            return s.length() == 0? true:false;
        }
        //p不允许以‘*’开头
        if(p.charAt(0) == '*'){
            return false;
        }
        //记录匹配结果d[i][j]表示s[0,i]匹配p[0,j]时的结果
        boolean[][]  dp = new boolean[s.length()][p.length()];
        dp[0][0] = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
        for(int i = 0;i<s.length();i++){
            for(int j = 1;j<p.length();j++){
                if(p.charAt(j) == '*'){
                    dp[i][j] =dp[i][j-1] ||(i > 0 && dp[i - 1][j - 1] && (s.charAt(i) == p.charAt(j-1)|| p.charAt(j-1) == '.'));
                } else {
                    dp[i][j] =i>0 && dp[i - 1][j - 1] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                }
            }
        }
        return dp[s.length()-1][p.length()-1];
    }
}
