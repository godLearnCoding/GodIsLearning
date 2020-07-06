package netease.design;

import java.util.Arrays;

/**
 * <pre>
 * 作者：shenliang
 * 项目：netease.design
 * 说明：
 * 日期：2020年05月15日
 * 备注：
 * </pre>
 */
public class GodsStringBuffer {

  char[] chars = null;//默认容纳16个字符

  int capity = 0;//容量

  volatile int len = 0;//包含字符的长度

  public  GodsStringBuffer(){
    this.capity = 16;
    this.chars = new char[16];
  }

  public GodsStringBuffer(int capity){
    this.capity = capity;
    this.chars = new char[capity];
  }


  public GodsStringBuffer append(char a){
    expandCapity(len +1);
    chars[len] = a;
    len++;
    return  this;
  }

  private void expandCapity(int i) {
    int newCapity = i * 2 ;//两倍扩容
    if(newCapity < 0){
      capity = Integer.MAX_VALUE;
      chars = new char[Integer.MAX_VALUE];
    }
    if(newCapity >= 2*this.capity){
      capity = newCapity;
      chars = Arrays.copyOf(chars,newCapity);
      System.out.println("扩容了");
    }
  }
  public int getLen() {
    return len;
  }

  public int getCapity() {
    return capity;
  }

  @Override
 public  String toString(){
    return  new String(chars);
  }
}
