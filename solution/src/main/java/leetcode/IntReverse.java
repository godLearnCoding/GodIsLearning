package leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
 * Created by god on 2019/5/5.
 */
public class IntReverse {

    public static void main(String[] args){
        System.out.print(reverse2(-250000003));

    }
    public static  int reverse(int x) {
        Integer integer = new Integer(x);
        String  str = integer.toString();
        char[] chars = str.toCharArray();
        int len = chars.length;
        StringBuffer buffer = new StringBuffer();
        if(len > 0 ){
            int end = 0;
           if('-' == chars[0]){
                buffer.append("-");
                end = 1;
            }
            for(int i = len -1;i >= end; i--){
                buffer.append(chars[i]);
            }
        }
        Integer rs = null;
        try {
             rs = new Integer(buffer.toString());
        }catch (NumberFormatException e){
           // e.printStackTrace();
        }
        return null == rs? 0:rs.intValue();
    }

    //good solution
    public static  int reverse2(int x) {
        int rev = 0;
        //数字的pop和push
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
