package mutithreads;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：
 * 日期：2020年05月28日
 * 备注：
 * </pre>
 */
public class JoinTest {

  public static void main(String[] args) {

    Thread th1 = new Thread(new Runnable() {
      @Override
      public void run() {
          System.out.println("first");
      }
    });

    Thread th2 = new Thread(new Runnable() {
      @Override
      public void run() {
          try {
            th1.join();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("second");
        }
    });

    Thread th3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          th2.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("third");
      }
    });
    th1.start();
    th2.start();
    th3.start();

    //Thread.State.BLOCKED;
  }

}
