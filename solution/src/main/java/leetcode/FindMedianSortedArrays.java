package leetcode;
import java.util.*;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

 你可以假设 nums1 和 nums2 不会同时为空。
 * Created by god on 2019/4/28.
 */
public class FindMedianSortedArrays {

    public static void main(String[] strs){
     System.out.print(findMedianSortedArrays(new int[]{},new int[]{1,3}));
    }

    public static double findMedianSortedArrays2(int[] nums1,int[] nums2){
        int a = 0;
        int b = 0;
        int len = nums1.length + nums2.length;

        int i = nums1.length/2;
        int j= nums2.length/2;
        List<Integer> list = new ArrayList<>();
        while(i< nums1.length && j < nums2.length){
            if(nums1[i]<nums1[j]){
                list.add(nums1[i++]);
            } else {
                list.add(nums2[j++]);
            }
        }

       return  (a+b)/2.0;
    }

    public static  double findMedianSortedArrays(int[] nums1,int[] nums2){
        int len = nums1.length +nums2.length;
        int index1 = 0;
        int index2 = 0;
        if((len & 1) == 1){
            index1 = index2 = (len +1)/2 -1;
        } else {
            index1 = len/2-1;
            index2=len/2;
        }
        int[] newArray = contactAarrays(nums1,nums2);
        return (newArray[index1] +newArray[index2])/2.0;
    }

    private static int[] contactAarrays(int[] nums1,int[] nums2){
        int[] result = new int[nums1.length + nums2.length];
        int i = 0,j=0,k=0;
        while (i< nums1.length && j< nums2.length){
            if(nums1[i]<nums2[j]){
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        while (i<nums1.length){
            result[k++] = nums1[i++];
        }
        while (j<nums2.length){
            result[k++] = nums2[j++];
        }
        return  result;

    }

    //获取一个数组的中位数
    private static double findMedianOneArray(int[] nums){
        int l = nums.length;
        double m = 0.0;
        if((l & 1) == 1){
            m = nums[(l+1)/2-1];
        } else {
            if(l == 0){
                m = 0;
            } else {
                m = (nums[l/2-1]+nums[l/2])/2.0;
            }
        }
        return  m;
    }

}
