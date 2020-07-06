package mutithreads;

import java.util.concurrent.locks.LockSupport;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：park/unpark
 * 不释放锁  锁会一起挂起
 * 日期：2020年04月28日
 * 备注：
 * </pre>
 */
public class Demo3 {

  public static  Object baozi = null;


  public static void main(String[] args) {

    Thread cThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(baozi == null){//不建议直接if判断 有伪唤醒的情况  建议使用while
          System.out.println("1.进入等待");
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //不会自动解锁 容易死锁
          LockSupport.park();
        }
        System.out.println("3.买到包子："+baozi);
      }
    });
    cThread.start();
    try {
      Thread.sleep(1000);
      baozi =new Object();
      LockSupport.unpark(cThread);
      System.out.println("2.通知消费者");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    cThread.resume();//唤醒
  }




}
