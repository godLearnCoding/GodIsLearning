package leetcode;

import java.util.Arrays;

/**
 * 找出只出现一次的两个数字(已知整数数组包的数字要么出现两次要么出现一次，但是有且仅有俩数字出现一次)
 * 算法核心：
 * 1.计算出两个数的异或值r。
 * 2.找到区分这个两个数的标记：r二进制最左侧1的位置。两个数在此位置的必定是：一个0和一个1。
 * Created by god on 2019/4/11.
 */
public class OccurOnce2 {

    public static void main(String[] args) throws Exception{
        //应该返回1,5
        int[] example = {1,2,2,4,5,4,5,3};
        int[] result1 = getOccurOnceNums2(example);
        System.out.println(result1[0]+"-"+result1[1]);
    }


    public  static int[] getOccurOnceNums2(int[] inputs) throws Exception{
        int r = 0;
        if(inputs.length<2){
            throw  new Exception("样本数少于2");
        }
        for(int i = 0;i<inputs.length;i++){
            r ^= inputs[i];
        }
        int a = 0;
        int b =0;
        int pos = Integer.toBinaryString(r).length() -1;//找到区分两个数的位置，即r二进制最左侧1的位置。两个数字二进制这个位置必定是一个0和一个1。
        for(int num:inputs){
            if((num >>pos &1) == 0 ){
                a ^= num;
            } else {
                b ^=num;
            }

        }

        return new int[]{a,b};
    }

    //bad solution
    public  static int[] getOccurOnceNums(int[] inputs) throws Exception{
        int[] result= new int[2];
        int r = 0;
        if(inputs.length<2){
            throw  new Exception("样本数少于2");
        }
        int[] temp = new int[inputs.length];
        for(int i = 0;i<inputs.length;i++){
            r ^= inputs[i];
            temp[i] = inputs[i];
        }
        int out =0;
        for(int i = 0;i<temp.length;i++) {
                temp[i] = r ^ inputs[i];
                if (checkPair(temp)) {
                    System.out.println("occured once num:" + inputs[i]);
                    result[out] = inputs[i];
                    out++;
                }
                temp[i] =  inputs[i];
            }
        return result;
    }

    public static boolean checkPair(int[] beChecked){
        int[] temp = Arrays.copyOf(beChecked,beChecked.length);
        Arrays.sort(temp);
        //偶尔个元素
        boolean flag = true;
        if((temp.length & 1) == 0){
            for (int i = 0;i<temp.length;i=i+2){
                if(temp[i] != temp[i+1]){
                    flag =false;
                    return flag;
                }
            }
        }
        return  flag;
    }

}
