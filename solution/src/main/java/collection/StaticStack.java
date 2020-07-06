package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：静态数组实现的栈
 * 日期：2020年06月04日
 * 备注：
 * </pre>
 */
public class StaticStack {

  /**
   * 数组实现栈
   *
   * 采用静态所有栈对象公用
   */
   static  Object[] elements;

  /**
   * 栈顶指针
   */

  int top = 0;

  /**
   * 元素个数
   */
  int len ;

  /**
   * 容量
   */
  volatile  int capacity ;

  public int getTop(){
    return top;
  }
  public void setTop(int top){
    this.top = top;
  }


  public StaticStack(){
    //默认栈容量16个
    this.capacity = 16;
    elements = new Object[capacity];
  }

  /**
   * 压栈操作
   */
  public void push(Object element){
    //检查容量扩容
    ensureCap(this.len+1);
    elements[top++] = element;
    //栈深度
    if(top == len+1){
      len++;
    }
  }

  /**
   * 出栈操作
   */
  public Object pop(){
    if(isEmpty()){
      throw new RuntimeException("栈已空，无法弹出");
    }
    top--;
    return  elements[top];
  }

  public  boolean isEmpty(){
    return top == 0;
  }


  /**
   * 扩容
   * @param len
   */
  private void ensureCap(int len) {
    if( len < 0){
      this.capacity = Integer.MAX_VALUE;
      elements = new Object[this.capacity];
      return;
    }
    if(len > this.capacity){
      do{
        this.capacity= this.capacity << 2;
      } while(len > this.capacity);
      elements = new Object[this.capacity];
    }
  }

}
