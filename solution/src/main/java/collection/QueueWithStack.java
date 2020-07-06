package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：使用两个栈实现一个队列
 * 日期：2020年06月04日
 * 备注：
 * </pre>
 */
public class QueueWithStack {

  private StaticStack head = new StaticStack();

  private StaticStack tail =  new StaticStack();



  public void enqueue(Object element){
    tail.push(element);
  }

  public Object dequeue(){
  return   head.pop();
  }

  public static void main(String[] args) {
    QueueWithStack queueWithStack = new QueueWithStack();
    queueWithStack.enqueue(1);
    queueWithStack.enqueue(2);
    System.out.println(queueWithStack.dequeue());

  }



}
