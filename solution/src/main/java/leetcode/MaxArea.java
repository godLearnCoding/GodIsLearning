package leetcode;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

 说明：你不能倾斜容器，且 n 的值至少为 2。
 * Created by god on 2019/5/17.
 */
public class MaxArea {

    public static  void main(String[] args){
        System.out.println(maxArea3(new int[]{1,8,106,102,5,4,8,3,7}));
    }

    //bad solution1
    public static  int maxArea(int[] height) {
        int area = 0;
        for(int i = 0;i<height.length;i++){
            for(int j = i+1;j < height.length;j++){
                int h  = height[i] > height[j]? height[j]:height[i];
                if(area < (j-i)* h){
                    area = (j-i)* h;
                }
            }
        }
        return  area;
    }
    //bad solution2
    public static  int maxArea2(int[] height) {
        int area = 0;
        int abs = height.length -1;
        while (abs > 0){
            for(int i = 0,j = i+abs;i<height.length && j<height.length;i++,j++){
               int  h1  = Math.min(height[i],height[j]);
                if(area < abs * h1){
                    area = abs * h1;
                }
            }
            abs--;
        }
        return  area;
    }

    //good solution
    public static  int maxArea3(int[] height) {
        int area = 0;
        int i = 0,j = height.length -1;
        while(i<j){
            int h = height[i] < height[j] ?height[i++]:height[j--];
            area = Math.max(area,(j-i+1)*h);
        }
        return  area;
    }



}
