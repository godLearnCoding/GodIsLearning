package mutithreads;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：suspend/resume
 * 废弃api 容易死锁
 * 需要考虑锁和执行顺序
 * 日期：2020年04月28日
 * 备注：
 * </pre>
 */
public class Demo1 {

  public static  Object baozi = null;


  public static void main(String[] args) {

    Thread cThread = new Thread(new Runnable() {
      @Override
      public void run() {
        if(baozi == null){
          System.out.println("1.进入等待");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //不会自动解锁 容易死锁
          Thread.currentThread().suspend();
        }
        System.out.println("买到包子："+baozi);
      }
    });
    cThread.start();
    try {
      Thread.sleep(3000);
      baozi =new Object();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    cThread.resume();//唤醒
  }





}
