package netease.design.singleton;

/**
 * <pre>
 * 作者：shenliang
 * 项目：netease.design.singleton
 * 说明：利用静态内部类实现单例
 * 具有延迟加载+单例+线程安全
 * 日期：2020年05月15日
 * 备注：
 * </pre>
 */
public class Singleton {

  private Singleton(){}

  public  Singleton getInstance(){
    return  SingletonHolder.INSTANCE;
  }

 private static class SingletonHolder{
    private static Singleton INSTANCE  = new Singleton();
  }

}
