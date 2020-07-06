package huawei;

/**
 * 给定两个大数（正数）字字符串A和B，计算A-B。
 * Created by god on 2019/5/17.
 */
public class BigNumString {

    public static  void main(String[] agrs){
        String A = "1113";
        String B = "121";
       System.out.println(sub(A,B));
    }

    public static  String sub(String A,String B){
        char[] c1 = A.toCharArray();
        char[] c2 = B.toCharArray();
        int f = compare(c1,c2);
        //相等
        if(f == 0){
            return "0";
        }
        boolean negative = false;
        //大数为c1
        if(f == -1){
            char[] temp ;
            temp = c1;
            c1=c2;
            c2 =temp;
            negative = true;
        }
        int len = c1.length;
        int ans = 0; //借位
        int i = 0,a =0,b = 0;
        int[] result = new int[len];
        while (i < len){
            a = c1[len-i-1] -'0';
            if( i > c2.length-1){
                b = 0;
            } else {
                b = c2[c2.length-i-1] -'0';
            }
            a = a - ans;
            if(a < b){
                result[i] = 10+a-b;
                ans = 1;
            } else {
                result[i] = a-b;
                ans = 0;
            }
            i++;
        }
        StringBuffer buffer = new StringBuffer();
        //去掉高位0
        boolean flag = true;
        for( int k = len-1;k>=0;k--){
            if(result[k] == 0 && flag){
                continue;
            } else {
                flag = false;
            }
            buffer.append(result[k]);
        }
        if(negative){
            buffer.insert(0,"-");
        }
        return  buffer.toString();
    }



    public static  int compare(char[] c1,char[] c2){
        if(c1.length > c2.length){
            return 1;
        } else if(c1.length == c2.length){
            int i = 0;
            while(i < c1.length && c1[i] == c2[i]){
                i++;
            }
            if(i == c1.length){
                return 0;
            }
            if(c1[i]>c2[i]){
                return 1;
            }
        }
        return -1;
    }
}
