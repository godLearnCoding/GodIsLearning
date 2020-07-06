package netease.design.singleton;

/**
 * <pre>
 * 作者：shenliang
 * 项目：netease.design.singleton
 * 说明：枚举实现单例
 * 线程安全 +单例＋延迟加载
 * 日期：2020年05月15日
 * 备注：
 * </pre>
 */
public class Singleton2 {

  private Singleton2(){

  }
  public Singleton2 getInstance(){
    return  SingletonEnum.INSTANCE.getIntance();
  }

  private enum SingletonEnum{
    INSTANCE;
    private Singleton2 singleton;

    private SingletonEnum(){
      singleton = new Singleton2();
    }

    private Singleton2 getIntance(){
      return  singleton;
    }
  }

}
