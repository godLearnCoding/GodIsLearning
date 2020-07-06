package finalabe;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 作者：shenliang
 * 项目：finalabe
 * 说明：终态变量的安全性
 * 日期：2020年06月09日
 * 备注：
 * </pre>
 */
public class FinalTest {

  final int  x;//只能初始化一次
  int y = 99;
  static FinalTest finalTest;

  public FinalTest(int x,int y) {
    this.x = x;
    this.y = y;
  }

  public static  void initInstance(){
    finalTest = new FinalTest(4,5);
  }

  public static void read(){
    if(finalTest !=null){
      System.out.println(finalTest.x);//4 guaranteed to be see
      System.out.println(finalTest.y);//0 can be see
    }
  }

  public static void main(String[] args) {

    Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
      while(true){
        initInstance();
      }
    },1,1, TimeUnit.MILLISECONDS);

    Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
      while(true){
        read();
      }
    },1,1, TimeUnit.MILLISECONDS);


  }


}
