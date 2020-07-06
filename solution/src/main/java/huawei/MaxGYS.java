package huawei;

import java.util.*;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：最大公约数
 * 日期：2020年05月20日
 * 备注：6 10
 * 原理：
 * </pre>
 */
public class MaxGYS {

  public static void main(String[] args) {
    System.out.println(max(15,180));

    System.out.println(sqrt(9));

    Scanner sc = new Scanner(System.in);
    //boolean b = sc.hasNextFloat();
    String ss = "" ;
    //ss.split()

    Map<Integer,Integer>  sm = new TreeMap<>();
    sm.put(3,4);
    sm.put(1,1);
    sm.put(6,3);
    sm.keySet().forEach(key -> System.out.println(key));
    Map<Integer,Integer>  sm2 = new TreeMap<>(new Comparator<Integer>(){
      public int compare(Integer i1,Integer i2){
        return i1 - i2;
      }
    });
  }



  //阿基米德迭代
  public static  int max(int a,int b){
    if(a <= 0 || b <= 0){
      return 0;
    }
    //b 作为较小的数
    int temp = 0;
    while(b > 0){
      temp = a % b;
      a = b;
      b = temp;
    }
    return  a;

  }

  //牛顿迭代
  public static double sqrt(double d){
    double e = 10e-15;
    double x = d;
    double y = (x + d/x)/2;
    while(Math.abs(x - y) > e){
      x = y;
      y = (x + d/x)/2;
    }
    return x;
  }

}
