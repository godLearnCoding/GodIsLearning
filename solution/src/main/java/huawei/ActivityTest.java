package huawei;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：
 * 日期：2020年05月21日
 * 备注：某商城举办了一个促销活动，如果某顾客是某一秒内第一个下单的顾客（可能是多个人），则可以 获取免单。请你编程计算有多少顾客可以获取免单。
 解答要求 时间限制：3000ms, 内存限制：64MB ，输入： 输入为n行数据，每一行表示一位顾客的下单时 间。 以（年-月-日 时-分-秒.毫秒）yyyy-MM-dd HH:mm:ss.ﬀf形式给出。

 0<n<50000 2000<yyyy<2020 0<MM<=12 0<dd<=28 0<=HH<=23 0<=mm<=59 0<=ss<=59 0<=ﬀf<=999 所有输 入保证合法。
 输出 输出一个整数，表示有多少顾客可以获取免单。
 样例：
 输入样例1
 2019-01-01 00:00:00.001 
 2019-01-01 00:00:00.002 
 2019-01-01 00:00:00.003
 输出样例1 
 1 

 * </pre>
 */
public class ActivityTest {

  public static  class Pair{
    private String ms;
    private int count;
    public Pair(String ms,int count){
      this.ms = ms ;
      this.count =count;
    }
    public String getMs() {
      return ms;
    }
    public int getCount() {
      return count;
    }
    public void increaseCount(){
       count++;
    }

  }

  public static void main(String[] args){
    String[] strings = new String[]{"2019-01-01 00:00:00.001"," 2019-01-01 00:00:01.002 ","2019-01-01 00:00:00.003"," 2019-01-01 00:00:01.002 "};
    Map<String,Pair> map = new HashMap<>();
    for(String time :strings){
      int index = time.indexOf(".");
      String second = time.substring(0,index);
      String ms = time.substring(index+1,time.length());
      if(map.containsKey(second)){
        Pair pair = map.get(second);
        int i = ms.compareTo(pair.getMs());
        if(i == 0){
          pair.increaseCount();
        }
        if(i == 1){
          //更新
          map.put(second, new Pair(ms,1));
        }
      } else {
        map.put(second, new Pair(ms,1));
      }
    }
    int total = 0;
    for(Pair pair : map.values()){
      total += pair.getCount();
    }
    System.out.println(total);








  }



}
