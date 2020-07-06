package leetcode;
import  java.util.*;

/**
 * Z形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

 L   C   I   R
 E T O E S I I G
 E   D   H   N
 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

 请你实现这个将字符串进行指定行数变换的函数
 * Created by god on 2019/4/30.
 */
public class Zconvert {
    public static  void  main(String[] args){

        System.out.print(zconvert2("w",3));


    }

    public static  String zconvert(String s,int numRows){
        int len = s.length();
        if(len == 0){
            return "";
        }
        if(numRows == 1){
            return s;
        }
        //创建numRows个数组
        List<Character>[] listArray = new List[numRows];
        int[] p = new int[len];//p[i] 第i个字符属于的行数 回文数列
        int C = 0,R = 0;
         p[0] = 0;
        for(int i =0;i<len;i++){
            R = C + numRows-1;
            if(i > R){
                C = i-1 ;
                R = 2*C;
            }
            int i_mirror = 2* C - i;
            if(i_mirror >= 0 && i <= R){
                p[i] = p[i_mirror];
            } else {
                p[i] = i;
            }
            //根据行号分类
            if(null == listArray[ p[i]]){
                listArray[ p[i]] = new ArrayList<>();
            }
            listArray[ p[i]].add(s.charAt(i));
        }

        StringBuffer buffer = new StringBuffer();
        for(int k = 0;k<numRows;k++){
            if(null != listArray[k]){
                for(Character c:listArray[k]){
                    buffer.append(c);
                }
            }
        }
        return buffer.toString();
    }

    public static  String zconvert2(String s,int numRows){
        int len = s.length();
        if(len == 0){
            return "";
        }
        if(numRows == 1){
            return s;
        }
        List<StringBuffer> list = new ArrayList<>();
        //init list
        int count = numRows ;
        if(len < numRows){
            count = len;
        }
        for(int i = 0;i<count;i++){
            list.add(new StringBuffer());
        }
        int[] p = new int[len];//p[i] 第i个字符属于的行数 回文数列
        int C = 0,R = 0;
        for(int i =0;i<len;i++){
            R = C + numRows-1;
            if(i > R){
                C = i-1 ;
                R = 2*C;
            }
            int i_mirror = 2* C - i;
            if(i_mirror >= 0 && i <= R){
                p[i] = p[i_mirror];
            } else {
                p[i] = i;
            }
            list.get(p[i]).append(s.charAt(i));
        }
        StringBuffer buffer = new StringBuffer();
        for (StringBuffer buf: list){
            buffer.append(buf);
        }
        return  buffer.toString();
    }

}
