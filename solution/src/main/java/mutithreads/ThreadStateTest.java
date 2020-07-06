package mutithreads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：
 * 日期：2020年06月01日
 * 备注：
 * </pre>
 */
public class ThreadStateTest {

 static Lock lock =  new ReentrantLock();


  public static void main(String[] args) throws InterruptedException {
    Thread th1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          LockSupport.park();//waiting
          Thread.sleep(1000);
          synchronized (lock){
            System.out.println(Thread.currentThread().getName()+":获取到锁");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    System.out.println(th1.getState().toString());//未启动 NEW
    th1.start();
    System.out.println(th1.getState().toString());//启动 runnable
    Thread.sleep(100);
    System.out.println(th1.getState().toString());//park --》waiting
    LockSupport.unpark(th1);//终止waiting
    Thread.sleep(100);
    System.out.println(th1.getState().toString());   //sleep--》timed_waiting
    //主线程锁
    synchronized (lock){
      Thread.sleep(1000);
      System.out.println(th1.getState().toString());   //lock--》blocked
    }
    Thread.sleep(1000);
    System.out.println(th1.getState().toString());//结束terminated
  }
  
  

}
