package mutithreads;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：volatile关键字
 * 日期：2020年06月09日
 * 备注：
 * </pre>
 */
public class Demo5 {

  private static  boolean flag = false;

  public static void main(String[] args) {
    new Thread(() ->{
      while(!flag);
      System.out.println("this thread1 doned");
    }).start();

    new Thread(() ->{
      flag = true;
      System.out.println("this thread2 doned");
    }).start();


  }

}
