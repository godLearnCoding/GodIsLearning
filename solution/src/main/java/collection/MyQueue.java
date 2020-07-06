package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：自定义队列（先进先出）
 * 日期：2020年06月03日
 * 备注：先出不安全
 * </pre>
 */
public class MyQueue {

  Object[] elements;

  int head = 0,tail = 0;

  int capity = 0;//容量
  int len = 0;//队列长度

  public MyQueue(){
    this.capity = 16;
    elements = new Object[16];
  }

  public MyQueue(int capity){
    this.capity = capity;
    elements = new Object[capity];
  }

  public void enqueue(Object element){
    if(tail  == capity){
      tail = 0;
    }
    if(len == capity - 1 || head  == tail + 1 ){
      throw new RuntimeException("队列已满");
    }
    len ++ ;
    elements[tail++] = element;
  }

  public Object dequeue(){
    if(isEmpty()){
      throw new RuntimeException("队列空");
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
    MyQueue myQueue = new MyQueue(5);
    myQueue.enqueue(1);
    myQueue.enqueue(2);
    System.out.println(myQueue.dequeue());
    myQueue.enqueue(3);
    myQueue.enqueue(4);
    myQueue.enqueue(5);
    System.out.println(myQueue.dequeue());
    myQueue.enqueue(6);
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
    myQueue.enqueue(7);
    System.out.println(myQueue.dequeue());
    myQueue.enqueue(8);
    //myQueue.enqueue(9);
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());






  }




}
