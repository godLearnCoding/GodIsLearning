package leetcode;

/**
 * 经典算法
 * 求S序列不同子序列的个数
 * 算法核心：
 * 1.统计出以某个字符(26个小写字母之一)未尾部时，子序列的个数。
 * 2.找出S序列子序列个数与去掉尾部的子序列S-1拥有子序列的个数的关系。
 * 最后合计这些个数即可。
 * Created by god on 2019/4/2.
 */
public class DistinctSubSeq {

    private static final String  BLANK = "";

    public static void  main(String[] args){
        //long mod = (long)1e9+7;
        try {
            System.out.println(getDistinctSubSeqCount("abcb"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getDistinctSubSeqCount(String s) throws  Exception{
        if(s == null || BLANK.equals(s)){
            throw  new Exception("seq is null or blank");
        }
        int len =s.length();
        if(len >= 2000 ){
            throw  new Exception("seq is to big to caculate");
        }
        if(len == 1){
            return  1;
        }
//        char[] table = {
//                'a','b','c','d','e','f',
//                'g','h','i','j','k','l',
//                'm','n','o','p','q','r',
//                's','t','u','v','w','x',
//                'y','z'
//        };
        long[] cTable = new long[26];//记录以某个字母为尾部子序列的个数
        char[] _s = s.toCharArray();
        long sum = 0;
        for(char c : _s){
            cTable[c-'a']  = sumTable(cTable)+1;
        }
        sum = sumTable(cTable);
        return sum;


    }

    public static  long sumTable(long[] ctable){
        long sum = 0;
        for(int i = 0;i<ctable.length;i++){
            sum = sum + ctable[i];
        }
        return  sum;
    };

}
