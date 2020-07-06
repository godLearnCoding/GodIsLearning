package jvm;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：对象复活
 * jsr133中关于finalize()方法的说明
 * 日期：2020年06月09日
 * 备注：一个对象有（可达/不可达状态reachable，还有是可结束/已结束状态finalizable）
 * </pre>
 */
public class ObjectRelived {

  public static ObjectRelived SAVE_HOOK = null;

  //覆盖Object中的终止方法
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("execute method finalize()");
    //对象复活
    SAVE_HOOK = this;
  }


  public static void main(String[] args) throws InterruptedException {
    //新建对象首先处于[reachable, unfinalized]状态
    SAVE_HOOK = new ObjectRelived();
    //置为空对象不可达[unreachable, unfinalized] 垃圾回收器可以回收
    SAVE_HOOK = null;
    //主动gc
    System.gc();
    //释放cpu  GC会判断该对象是否覆盖了finalize方法 覆盖了放入F-Queue队列 由一低优先级线程执行该队列中对象的finalize方法（unfinalized）
    Thread.sleep(500);
    if(SAVE_HOOK != null){
      //finalize方法后 SAVE_HOOK对象变为[reachable, finalized]
      //SAVE_HOOK = this;复活了
      System.out.println("I am alived");//输出
    } else {
      System.out.println("I am dead");
    }
    //再次置为null SAVE_HOOK对象变为[unreachable, finalized]
    SAVE_HOOK = null;
    System.gc();
    //释放cpu
    Thread.sleep(500);
    //SAVE_HOOK对象变为[unreachable, finalized] gc直接回收掉 finalize方法不执行
    if(SAVE_HOOK != null){
      System.out.println("I am alived");
    } else {
      System.out.println("I am dead");//输出
    }
  }

}
