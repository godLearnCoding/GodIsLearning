package netease.design;

/**
 * <pre>
 * 作者：shenliang
 * 项目：netease.design
 * 说明：
 * 日期：2020年05月15日
 * 备注：
 * </pre>
 */
public class GodsStringBuffer2 extends AbsractStringBuffer {

  public GodsStringBuffer2() {
    super(16);
  }

  public int getLen(){
    return super.count;
  }

  public int getCapacity(){
    return super.capacity;
  }

  @Override
  public  String toString(){
    return  new String(chars);
  }



}
