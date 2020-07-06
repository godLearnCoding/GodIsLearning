package leetcode;

import java.util.Arrays;

/**
 * 找出只出现一次的数字(已知整数数组包的数字要么出现两次要么出现一次)
 * 推荐是异或运算符
 * Created by god on 2019/4/2.
 */
public class OccurOnce {

    public static  void  main(String[] arg) throws  Exception{
        System.out.println(getOnceNum_(new int[]{1,2,4,4,2,5,1}));

    }

    //good solution
    public static int getOnceNum_(int[] nums){
        int result = 0;
        for(int i :nums){
            result ^= i;
        }
        return result;

    }

    //bad solution
    public static int getOnceNum(int[] nums)throws Exception{
        int result = -1;
        if(nums.length<2){
            throw new Exception("nums is too less!");
        }
        Arrays.sort(nums);
        for(int i =0 ;i<nums.length;i++){
            if(i == nums.length-1){
                if(nums[i-1] != nums[i] && nums[i-2] == nums[i-1]){
                    return nums[i];
                }
            }
            if(i == 0){
                if(nums[0] != nums[1] && nums[1] == nums[2]){
                    return nums[0];
                }
            }
            if(i>0 && i<nums.length-1)
            if(nums[i-1] != nums[i] && nums[i] != nums[i+1]){
                return nums[i];

            }
        }
        return result;
    }
}
