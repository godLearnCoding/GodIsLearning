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
public class SortATest {

  public static void main(String[] args) {
    int[] nums = new int[]{2,1,4,6,7,2,5};
    sort(nums);
    for(int i :nums){
      System.out.println(i);
    }
  }


  public static void  sort(int[] nums){
    int len = nums.length;
    int i = 0;
    int j = 1;
    while(j < len){
        i = j;
        while (i >0 && nums[i-1] > nums[i]  ){
          //交换位置
          int temp = nums[i];
          nums[i] = nums[i-1];
          nums[i-1] = temp;
          i--;
        }
        j++;
    }
  }

}
