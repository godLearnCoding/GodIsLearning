package leetcode;
import java.util.*;
/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * Created by god on 2019/4/18.
 */
public class TwoSum {

    public static void main(String[] args){
        int[] nums ={-1,-2,5,7,3,11,5,5,5,-1,-1,11,-1};
        int target = 10;
      System.out.println(getTwoSums(nums,target));

    }

  /**
   * 获取数组中所有的数字对，使得两个数字之和为target
   * @param nums
   * @param target
   * @return
   */
    public static List<String> getTwoSums(int[] nums,int target){
      //去重
      Set<String> set = new HashSet<>();
      //记录没有完成匹配元素的索引 key-》nums[i]  value ->i
      Map<Integer,Integer> map = new HashMap<>();
      for(int i = 0; i < nums.length; i++){
        int key = target - nums[i];
        //map记录中找到匹配的值
        if(map.containsKey(key)){
           //获取下标
           int v = map.get(key);
           String r = nums[i] + "+" + nums[v];
           //去重
           set.add(r);
        } else {
          //记录下标
          map.put(nums[i], i);
        }
      }
      return  new ArrayList<>(set);
    }

    public static  int[] getTwoSum(int[] nums,int target){
       //存放遍历过的数字 key=数字值 value=下标
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int tmp = target -nums[i];
            if(map.containsKey(tmp)){
                return new int[]{i,map.get(tmp)};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }
}
