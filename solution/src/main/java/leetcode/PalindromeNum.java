package leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * Created by god on 2019/5/8.
 */
public class PalindromeNum {

    public static  void main(String[] args){
        System.out.print(isPalindrome(12321));

    }

    public static boolean isPalindrome(int x){
        if(x < 0){
            return  false;
        }
        if(x%10 == 0){
            return false;
        }
        int pop = 0;
        int temp = x;
        int rs = 0;
        while (x != 0){
            pop  = x % 10;
            x = x/10;
            rs = rs*10 +pop;
        }
      if(rs == temp){
            return true;
      }
        return false;
    }
}
