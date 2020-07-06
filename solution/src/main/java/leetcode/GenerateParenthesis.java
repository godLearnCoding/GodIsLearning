package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * Created by god on 2019/6/21.
 */
public class GenerateParenthesis {

    public static void main(String[] args) {

     List<String> list =   generateParenthesis(4);
     for(String str : list){
         System.out.println(str);
     }
    }

    public static List<String> generateParenthesis(int n) {
        List<String>  rsList = new ArrayList<>();
        backStrace(rsList,"",0,0,n);
        return rsList;
    }

    public static void backStrace(List<String> rsList,String curr,int open,int close,int max){
        if(open + close == 2 * max){
            rsList.add(curr);
            return;
        }
        if(open < max){
            backStrace(rsList,curr+"(",open+1,close,max);
        }
        if(close < open){
            backStrace(rsList,curr+")",open,close+1,max);
        }
    }
}
