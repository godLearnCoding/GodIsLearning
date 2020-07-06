package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：双端队列dequeue
 * 日期：2020年06月04日
 * 备注：不安全  数组实现 固定最大容量
 * 支持在首尾进行插入和删除操作
 * 同时栈操作和队列操作
 * </pre>
 */
public class MyDequeue {

  Object[] elements;

  int head,tail;

  int capity = 0;

  int len = 0;

  public  MyDequeue(){
    this.capity = 16;
    elements = new Object[capity];
  }

  public  MyDequeue(int capity){
    this.capity = capity;
    elements = new Object[capity];
  }

  /**
   * 入栈
   * @param element
   */
  public void push(Object element){
    if(head == capity){
      throw new RuntimeException("栈已满");
    }
    len++;
    elements[head++] = element;
  }

  /**
   *出栈
   */
  public Object pop(){
    if(head  ==  0){
      throw new RuntimeException("栈已空");
    }
    len--;
   return elements[--head] ;
  }


  public void enqueue(Object element){
    if(tail  == capity){
      tail = 0;
    }
    if(len == capity - 1 || head  == tail + 1 ){
      throw new RuntimeException("双端队列已满");
    }
    len ++ ;
    elements[tail++] = element;
  }

  public Object dequeue(){
    if(isEmpty()){
      throw new RuntimeException("双端队列已空");
    }
    if(head  == capity){
      head = 0;
    }
    len--;
    return elements[head++];
  }

  public boolean isEmpty(){
    return len == 0 || head == tail;
  }

  public static void main(String[] args) {

    MyDequeue myDequeue = new MyDequeue(5);
    myDequeue.enqueue(1);
    myDequeue.enqueue(2);
    System.out.println(myDequeue.pop());
    //myDequeue.enqueue(3);
    //System.out.println(myDequeue.dequeue());


  }




}
