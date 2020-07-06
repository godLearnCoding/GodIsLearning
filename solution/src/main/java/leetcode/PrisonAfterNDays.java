package leetcode;

import java.util.Arrays;

/**
 * N天后的牢房
 *  间牢房排成一排，每间牢房不是有人住就是空着。

 每天，无论牢房是被占用或空置，都会根据以下规则进行更改：

 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
 否则，它就会被空置。
 （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）

 我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。

 根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。

 * Created by god on 2019/4/12.
 */
public class PrisonAfterNDays {

    public static  void main(String[] args){
        int[] cells = {0,1,0,1,1,0,0,1};
        int[] rs = getCellStatusAfterNDays2(cells,7);
        for(int a:rs){
            System.out.print(a+",");
        }
    }

    public static int[] getCellStatusAfterNDays2(int[] cells,int days){
        int len =cells.length;
        int temp[] = new int[len];
        for(int i=0;i<days;i++){
            for(int j =1;j<len;j+=2) {
                if ( j == len - 1) {
                        cells[j-2] =temp[j-2];
                        cells[j-1]=temp[j-1];
                        cells[len-1]=0;
                    continue;
                }
                boolean mustChange = true;
                if((cells[j] ^cells[j]) == 1){
                    mustChange =false;
                }
                //交换位置
                if(mustChange){
                    int c  = cells[j];
                    cells[j]=  cells[j+1];
                    cells[j+1] = c;

                }
                if(cells[j-1] == cells[j]){
                    temp[j]=1;
                } else {
                    temp[j]=0;
                }
                if(cells[j+1] == cells[j+2]){
                    temp[j+1]=1;
                } else {
                    temp[j+1]=0;
                }
                //换回位置
                int c = cells[j+1];
                cells[j+1]= cells[j];
                cells[j] =c;
                if(j-2 >=1 && j-1<=len-1){
                    cells[j-2] =temp[j-2];
                    cells[j-1]=temp[j-1];
                }
            }
        }
        return cells;
    }


    //bad solution
    public static int[] getCellStatusAfterNDays(int[] cells,int days){
        int len =cells.length;
        for(int i=0;i<days;i++){
            int temp[] = Arrays.copyOf(cells,len);
            for(int j =0;j<len;j++) {
                if (j == 0 || j == len - 1) {
                    continue;
                }
                if (cells[j - 1] == cells[j + 1]) {
                    temp[j] = 1;
                } else {
                    temp[j] = 0;
                }
            }
            temp[0] = 0;
            temp[len-1]=0;
            cells = Arrays.copyOf(temp,len);
        }
        return cells;
    }
}
