package huawei;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：
 * 日期：2020年05月21日
 * 备注：
 * </pre>
 */
public class PopTest {

  public static void main(String[] args) {
    //System.out.println(getNum(10));
    String[] strings = new String[]{"12,18","16,18","12,20"};
    int[] nums = new int[8];
    for(String s :strings){
      String[] split = s.split(",");
      int start = Integer.valueOf(split[0]);
      int end = Integer.valueOf(split[1]);
      if(start >= 12 && end <=20){
        for(int i = start; i < end; i++){
          nums[i-12] ++;
        }
      }
    }
    int index = 12;
    for(int i :nums){
      System.out.println("["+index+","+(1+index)+"):"+i);
      index++;
    }
  }

  public static int getNum(int n){
    int a = 0;//每次参与兑换的空瓶剩余数量
    int b = n;//空瓶数量
    int count = 0;//兑换的汽水
    while(b  >= 3){
      count =  count + b/3;
      a = b % 3;
      b = a +  b/3;
    }
    if(b == 2){
      count++;
    }
    return count;
  }




}
