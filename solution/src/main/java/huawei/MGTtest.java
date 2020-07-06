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
public class MGTtest {

  public static void main(String[] args) {
    //System.out.println(new Volume("60000M").getUnitV());
    String[] nums = new String[]{"20G","30M","10T","1T","60M","5G"};
    sort(nums);
    for(String n :nums){
      System.out.println(n);
    }

  }

 public static class Volume{
    private int m;
    private String unit;
    private int unitV;
    Volume(String vs){
      this.unit = vs.substring(vs.length() -1);
      this.m = Integer.valueOf(vs.substring(0,vs.length() -1));
      this.unitV = "T".equals(this.unit) ?3:"G".equals(this.unit)?2:1;
    }

   public int getM() {
     return m;
   }

   public String getUnit() {
     return unit;
   }

   public int getUnitV() {
      return  this.unitV;
   }

   public  String toString(){
      return m+unit;
    }
  }

  public static void sort(String[] nums) {
    int len = nums.length;
    int i = 1;
     while (i < len){
      int j = i;
       while( j > 0 ){
         Volume v1 = new Volume(nums[j-1]);
         Volume v2 = new Volume(nums[j]);
         //交换排序
         if(v1.getUnitV() > v2.getUnitV() || (v1.getUnitV() == v2.getUnitV() && v1.getM() > v2.getM())){
           String temp = nums[j-1];
           nums[j-1] = nums[j];
           nums[j] = temp;
         } else {
           break;
         }
         j--;
       }
       i++;
     }
  }



}
