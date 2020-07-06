package leetcode;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：

 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * Created by god on 2019/5/5.
 */
public class Atoi {

    public static  void main(String[] args){


        System.out.print(myAtoi("  -1"));

    }


    static  final  char[] DIGITTABLE  = {'0','1','2','3','4','5','6','7','8','9'};

    static  final  char NEGATIVE  = '-';
    static  final  char POSITIVE  = '+';

    public static  int myAtoi(String str) {
        int rs = 0;
        if(null == str ){
            return rs;
        }
        str = str.trim();
        if(str.length() == 0){
            return rs;
        }
        char[] chars = str.toCharArray();
        StringBuffer buf = new StringBuffer();
        int i = 0;
        if(chars[0] == NEGATIVE  && chars.length >=2 && chars[1] >=DIGITTABLE[0] && chars[1]<=DIGITTABLE[9]){
            buf.append(chars[0]);
            i = 1;
        } else if(chars[0] == POSITIVE && chars.length >=2 && chars[1] >=DIGITTABLE[0] && chars[1]<=DIGITTABLE[9]){
            i = 1;
        } else if(chars[0] >=DIGITTABLE[0] && chars[0]<=DIGITTABLE[9]){
            buf.append(chars[0]);
            i = 1;
        } else {
            return rs;
        }
        while(i < chars.length && chars[i] >=DIGITTABLE[0] && chars[i]<=DIGITTABLE[9]){
            buf.append(chars[i]);
            i++;
        }
        try {
            rs = new Integer(buf.toString());
        }catch (NumberFormatException e){
            if(chars[0] == NEGATIVE){
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return rs;
    }
}
