package util;

/**
 * <pre>
 * 作者：shenliang
 * 项目：
 * 说明：耗时计算工具
 * 日期：2019年10月24日
 * 备注：多线程下配合ThreadLocal使用
 * </pre>
 */
public final class TimeCounter {

  /**
   * 开始时间
   */
  private long startTime;

  /**
   *结束时间
   */
  private long  endTime;

  /**
   * 时间差
   */
  private long timeDiff;

  /**
   *时间单位
   */
  private String timeUnit = UNIT_MICROSECOND;//默认ms

  private boolean started = false;

  private boolean ended = false;

  public TimeCounter(){

  }

  public TimeCounter(String timeUnit){
    this.timeUnit = timeUnit;
  }

  /**
   * 开始计时标记
   */
  public   void start(){
    this.startTime = System.currentTimeMillis();
    this.endTime = 0;
    this.started = true;
  }

  /**
   * 结束计时标记
   */
  public  void end(){
    if(this.started){
      this.endTime = System.currentTimeMillis();
      this.timeDiff = this.endTime - this.startTime;
      if( this.timeDiff < 0 ){
        this.timeDiff = 0;
        return;
      }
      this.ended = true;
    } else {
      throw new RuntimeException("start()  method not invoke yet");
    }
  }

  /**
   * 获取耗时
   * @return
   */
  public  long getTimeDiff(){
    if(this.started && this.ended){
      switch (this.timeUnit){
        case UNIT_SECOND:
          return  this.timeDiff/1000;
        case  UNIT_MINUTE:
          return  this.timeDiff/1000/60;
        default:
          return this.timeDiff;
      }
    } else{
      throw new RuntimeException("start() and end() method not invoke correctly");
    }
  }

  public String getTimeUnit() {
    return timeUnit;
  }

  public void setTimeUnit(String timeUnit) {
    this.timeUnit = timeUnit;
  }

  public  final  static String UNIT_MINUTE = "m";//分钟
  public  final  static String UNIT_SECOND = "s";//秒
  public final  static  String UNIT_MICROSECOND = "ms";//毫秒

  public static void main(String[] args) {
    ThreadLocal<TimeCounter> timeCounterThreadLocal = new ThreadLocal<TimeCounter>(){
      @Override
      public TimeCounter get(){
        return  new TimeCounter();
      }
    };
    TimeCounter timeCounter = timeCounterThreadLocal.get();
    System.out.println("timeCounter addr: "+timeCounter.hashCode());
    timeCounter.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    timeCounter.end();
    System.out.println(timeCounter.getTimeDiff());
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //timeCounter.setTimeUnit(UNIT_SECOND);
    System.out.println("timeCounter addr: "+timeCounter.hashCode());
    timeCounter.end();
    System.out.println(timeCounter.getTimeDiff());
  }

}
