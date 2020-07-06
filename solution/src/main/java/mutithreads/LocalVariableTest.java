package mutithreads;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：本地变量在多线程下的安全
 * 日期：2020年06月09日
 * 备注：
 * </pre>
 */
public class LocalVariableTest {


  public static int incr(int i){
    int j = i;
    j++;
    return j;
  }

  public static void main(String[] args) {
    for(int i = 0;i< 100;i++){
      int k = 0;
      new Thread(()->{
      //k = incr(k);//编译报错
      }).start();
    }
  }

}
