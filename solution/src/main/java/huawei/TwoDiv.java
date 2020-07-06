package huawei;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：二分搜索法
 * 日期：2020年05月21日
 * 备注：
 * </pre>
 */
public class TwoDiv {


  //求解立方根
  public static double getGenHao3(double x){
    double l = x>0? 0:x;
    double r = x>0?x:0;
    double m = 0;
    while(l < r){
       m = (l+r)/2;
      double y = m * m * m;
      if(x < y){
        r = m;
      } else {
        l = m;
      }
      //精度
      if(r-l <= 10e-15){
        break;
      }
    }
    return m;
  }

  //求解立方根
  public static double getGenHao2(double x){
    double l = 0;
    double r = x;
    double m = 0;
    while(l < r){
      m = (l+r)/2;
      double y = m * m;
      if(x < y){
        r = m;
      } else {
        l = m;
      }
      //精度
      if(r-l <= 10e-15){
        break;
      }
    }
    return m;
  }


  public static void main(String[] args) {
    System.out.println(getGenHao3(-27));
    System.out.println(getGenHao2(4));
  }

}
