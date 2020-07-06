package mutithreads;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：ThreadLocal
 * 线程变量  属于线程单独所有
 * 日期：2020年04月28日
 * 备注：
 * </pre>
 */
public class Demo4 {

  static  ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) {
    threadLocal.set("set 123");

    new Thread(new Runnable() {
      @Override
      public void run() {
        String value = threadLocal.get();
        System.out.println("线程1获取threadLocal的值："+value);
        threadLocal.set("set 456");
        System.out.println("线程1再次获取threadLocal的值："+threadLocal.get());
      }
    }).start();

    System.out.println("main 获取threadLocal的值");
    System.out.println(threadLocal.get());
  }



}
