package leetcode;

import java.util.*;

/**
 * Created by god on 2019/5/22.
 */
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{1,0,-1,-1,1,1,0,1,1,1,2,-3,3,0,0},0));
        System.out.println(threeSum(new int[]{1,0,-1,-1,1,1,0,1,1,1,2,-3,3,0,0}));
    }

    public static  List<List<Integer>> threeSum(int[] nums) {
        if(null == nums ||nums.length < 3){
            return new ArrayList<>();
        }
        List<List<Integer>> rsList = new ArrayList<>();
        int i = 2;
        while(i< nums.length){
            List<List<Integer>> list = twoSum(Arrays.copyOf(nums,i),-nums[i]);//o(n)
            if(list.size()>0){
                for(List<Integer> l : list){//o(n)
                    List<Integer> ls  = new ArrayList<>(l);
                    if(nums[i]<l.get(0)){
                        ls.add(0,nums[i]);
                    }else  if (nums[i]>l.get(1)){
                        ls.add(2,nums[i]);
                    }  else {
                        ls.add(1,nums[i]);
                    }
                    if(!rsList.contains(ls)){
                        rsList.add(ls);
                    }
                }
            }
            i++;
        }
        return  rsList;
    }

    //两个数相加的处理o(n)
    public static  List<List<Integer>> twoSum(int[] nums,int sum) {
        if(null == nums ||nums.length < 2){
            return new ArrayList<>();
        }
        int i = 0 ;
        List<List<Integer>> rsList = new ArrayList<>();
        Set<Integer> dic = new HashSet<>();
        Set<Integer> rubish = new HashSet<>();
        while (i < nums.length){
            if(!rubish.contains(nums[i]) && dic.contains(sum-nums[i])){
                List<Integer> list = new ArrayList<>();
                //保证顺序小数在前
                if(nums[i] < sum-nums[i]){
                    list.add(nums[i]);
                    list.add(sum-nums[i]);
                } else {
                    list.add(sum-nums[i]);
                    list.add(nums[i]);
                }
                rsList.add(list);
                //记录已经使用的元素，去重
                rubish.add(nums[i]);
                rubish.add(sum-nums[i]);
            }
            dic.add(nums[i]);
            i++;
        }
        //释放
        dic = null;
        rubish = null;
        return  rsList;
    }

}
