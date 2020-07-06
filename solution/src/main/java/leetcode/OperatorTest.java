package leetcode;

/**
 * Created by god on 2019/4/11.
 */
public class OperatorTest {

    public static void main(String args[]){
        int i = 5;//0101
        int j = 4;//0100
        int k =1; //0001
        int h =0; //0000
        int x=15;//1111
        System.out.println("1&5="+(1&5));//0001  1 都是1才是1
        System.out.println("1^5="+(1^5));//0100  4 相同才为0，不同为1
        System.out.println("5<<1="+(5<<1)); //1010 10 左移动 高位舍弃 低位补0 相当于乘以2
        System.out.println("5>>1="+(5>>1)); //0010 2  右移动 低位舍弃 高位补0 相当于除以2取整
        System.out.println("5|1="+(5|1));//0101 5 只要有1则为1
        System.out.println("~5="+(~5));//1010  -6  取反1变0,0变1

        System.out.println("i binary is "+Integer.toBinaryString(i));

        System.out.println("x binary is "+Integer.toBinaryString(x));


    }
}
