package collection;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：一个数组实现两个栈
 * 日期：2020年06月03日
 * 备注：
 * 策略：
 * 1.设置两个top，一个从头部开始，一个从尾巴开始
 * 2.双方压栈时候检查是否超过对方的top位置，否则栈满
 * </pre>
 */
public class TwoStack {

  //公用一个数组(固定大小)
  private  static Object[] elements;
  static {
    elements = new Object[16];
  }

  int hTop = 0;

  int tTop = elements.length - 1;

  public TwoStack(){
  }

  public TwoStack(int initCapity){
    if(initCapity <= 0 ){
      throw  new RuntimeException("容量不能小于或等于0");
    }
    elements = new Object[initCapity];
  }

  public void push1(Object element){
    if(hTop+1 <= tTop ){
      elements[hTop++] = element;
    } else {
      throw new RuntimeException("栈已满，无法压入");
    }
  }

  public Object pop1(){
    if(hTop == 0){
      throw new RuntimeException("栈已空，无法弹出");
    }
    return  elements[--hTop] ;
  }

  public void push2(Object element){
    if(hTop <= tTop-1 ){
      elements[tTop--] = element;
    } else {
      throw new RuntimeException("栈已满，无法压入");
    }
  }

  public Object pop2(){
    if(tTop ==  elements.length - 1){
      throw new RuntimeException("栈已空，无法弹出");
    }
    return  elements[++tTop] ;
  }

  public static void main(String[] args) {
    TwoStack stack = new TwoStack();
    for(int i = 1;i < 13;i++){
      stack.push1(i);
    }
    //System.out.println(stack.pop1());
    //System.out.println(stack.pop1());
    stack.push2("444");
    stack.push2("555");
    stack.push2("666");
    //stack.push2("777");
    System.out.println(stack.pop2());
    System.out.println(stack.pop1());
  }



}
