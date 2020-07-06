package leetcode;

import java.util.*;

/**
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

 映射关系：
 2:abc;
 3:def;
 4:ghi;
 5:jkl;
 6:mno;
 7:pqrs;
 8:tuv;
 9:wxyz
 * Created by god on 2019/5/27.
 */
public class LetterCombinations {

    public static void main(String[] args) {
        System.out.println(letterCombinations("2897546"));
    }

   public static final Map<Character,String[]> table = new HashMap<>();

   static  {
       table.put('2',new String[]{"a","b","c"});
       table.put('3',new String[]{"d","e","f"});
       table.put('4',new String[]{"g","h","i"});
       table.put('5',new String[]{"j","k","l"});
       table.put('6',new String[]{"m","n","o"});
       table.put('7',new String[]{"p","q","r","s"});
       table.put('8',new String[]{"t","u","v"});
       table.put('9',new String[]{"w","x","y","z"});

    }
    public static final Map<Character,String> table2= new HashMap<>();
    static  {
        table2.put('2',"abc");
        table2.put('3',"def");
        table2.put('4',"ghi");
        table2.put('5',"jkl");
        table2.put('6',"mno");
        table2.put('7',"pqrs");
        table2.put('8',"tuv");
        table2.put('9',"wxyz");

    }

    private static List<String> rs = new ArrayList<>();

    private static  void backStrace(String c,String nextDigits){
        if(nextDigits.length() == 0){
            rs.add(c);
        }else {
            String[] letters = table.get(nextDigits.charAt(0));
            //循环递归
            for(String le :letters){
                backStrace(c+le,nextDigits.substring(1));
            }
        }
    }

    public  static List<String> letterCombinations(String digits){
        if(digits.length() == 0 || digits.contains("1")){
            return rs;
        }
        backStrace("",digits);
        return rs;
    }


    //动态规划
    public static List<String> letterCombinations2(String digits) {
        List<String> nextList = new LinkedList<>();//频繁插入操作选用
        List<String> preList = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            String letters = table2.get(digits.charAt(i));
            int j = 0;
            if (preList.size() == 0) {
                for (; j < letters.length(); ) {
                    nextList.add("" + letters.charAt(j++));
                }
            } else {
                int k = 0;
                for (String str : preList) {
                    j = 0;
                    for (; j < letters.length(); ) {
                        if(k < nextList.size()){
                            nextList.set(k++,str + letters.charAt(j++));//覆盖操作
                        } else {
                            nextList.add(k++,str + letters.charAt(j++));
                        }
                    }
                }
            }
            preList.clear();
            preList.addAll(nextList);//覆盖数据减少空间
        }
        preList = null;//释放
        return nextList;
    }

    //动态规划2 badsolution
    public static List<String> letterCombinations3(String digits) {
        List<String> rsList = new ArrayList<>();
        //结果个数
        int count = 1;
        int i = 0;
        while (i<digits.length()){
            count *= table2.get(digits.charAt(i++)).length();
        }
        String[] array = new String[count],tempArray =  new String[count];
        for(i = 0;i < digits.length();i++){
           String str = table2.get(digits.charAt(i));
           int j = 0,k = 0;
            if(tempArray[0] == null){
                while(j < str.length()){
                    array[k++] =  "" + str.charAt(j++);
                }
            } else {
                for(String t : tempArray){
                    if(t !=  null){
                        j = 0;
                        while (j < str.length()){
                            array[k++] = t + str.charAt(j++);
                        }
                    } else {
                        break;//减少循环
                    }
                }
            }
            System.arraycopy(array,0,tempArray,0,count);
        }
        for(String buffer :array){
            rsList.add(buffer);
        }
        return rsList;
    }



}
