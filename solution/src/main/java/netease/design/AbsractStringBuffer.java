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
public class AbsractStringBuffer {

  protected char[] chars;

  protected int count;

  protected int capacity;

  public AbsractStringBuffer(int capacity){
    this.capacity = capacity;
    chars = new char[capacity];
  }

  protected AbsractStringBuffer append(char c){
    expandCapacity(count+1);
    chars[count++] = c;
    return this;

  }

  private void expandCapacity(int i) {
    int newCapacity = 2*i;
    if(newCapacity < 0){
      chars =new char[Integer.MAX_VALUE];
    }
    if(newCapacity >= 2* capacity){
      capacity = newCapacity;
      chars  = Arrays.copyOf(chars,newCapacity);
      System.out.println("扩容了");
    }
  }


}
