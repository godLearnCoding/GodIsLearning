package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：自定义栈(后进先出)
 * 日期：2020年06月03日
 * 备注：线程不安全
 * </pre>
 */
public class MyStack {

  /**
   * 数组实现栈
   *
   */
   Object[] elements;

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

  public MyStack(){
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

  public static void main(String[] args) {
    MyStack stack = new MyStack();
    stack.push("1");
    stack.push("3");
    stack.push("4");
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    stack.push("5");
    System.out.println(stack.pop());
    System.out.println(stack.len);


  }

}
