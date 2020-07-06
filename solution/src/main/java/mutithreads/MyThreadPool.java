package mutithreads;

import java.util.concurrent.*;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：
 * 日期：2020年04月23日
 * 备注：
 * </pre>
 */
public class MyThreadPool {

  private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), new RejectedExecutionHandler() {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println("有任务拒绝执行了："+r);
    }
  });

  // 等价new ThreadPoolExecutor(2,2,5,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
  //private ExecutorService service = Executors.newFixedThreadPool(2);


  public static void main(String[] args) throws InterruptedException {

    for(int i = 0; i < 15;i++){
      pool.submit(new Task(i));
      System.out.println("任务提交了："+i);
    }

    Thread.sleep(1000);
    System.out.println("当前待执行任务数："+pool.getQueue().size());
    System.out.println("当前活跃线程数："+pool.getPoolSize());
    Thread.sleep(15000);
    System.out.println("当前待执行任务数："+pool.getQueue().size());
    System.out.println("当前活跃线程数："+pool.getPoolSize());

  }
  static class Task implements Runnable{

    int i;

    public Task(int i) {
      this.i = i;
    }

    @Override
    public void run() {
      System.out.println("任务开始执行："+i);
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("任务执行完成了："+i);
    }
  }

}
