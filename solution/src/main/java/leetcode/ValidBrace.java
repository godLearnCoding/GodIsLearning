package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：40 41     123 125   91  93

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。
 * Created by god on 2019/6/1.
 */
public class ValidBrace {

    public static int  c = 40;

    public static void main(String[] args) {
        System.out.println(isValid("(]"));
    }

    public static  boolean isValid(String s) {
        if(s.length() == 0){
            return true;
        }
        if(s.length() < 2){
            return false;
        }
        int i = 0;
        Stack<Character> stack = new Stack<>();
        while(i < s.length()){
            char c = s.charAt(i);
            if(c == 40 || c==123 || c ==91 ){
                stack.push(c);
            } else {
                if(!stack.isEmpty()){
                    if(Math.abs(stack.pop()-c)  > 2){
                        return false;
                    }
                } else {
                    return false;
                }
            }
            i++;
        }
        return stack.isEmpty();
    }

    //
    public static List<String> generate(int n){
        List<String> rsList = new ArrayList<>();


        return rsList;

    }

    private static List<String> backTrace(int n){
        List<String> rsList = new ArrayList<>();
        if(n == 1){
            rsList.add("()");
        } else {
           for(String str:backTrace(n-1)){

           }

        }
        return rsList;

    }
}
