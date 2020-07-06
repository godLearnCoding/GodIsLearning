package huawei;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：两个大数（正整数）的加法运算
 * 日期：2019年09月09日
 * 备注：算法
 * 将大数字符串表示为科学计数法
 * 10^E *F;
 * </pre>
 */
public class BigNumString2 {



  public static void main(String[] args) {
    double d =   Double.valueOf("2.0000000001");
    System.out.println(d);
    EFDefine ef = new EFDefine();
    ef.setE(9);
    ef.setF(2.123456789d);
    System.out.println(ef.getValue());
    EFDefine ef2 = new EFDefine("100000");
    System.out.println(ef2.getE());
    System.out.println(ef2.getF());
    System.out.println(ef2.getValue());
    System.out.println(plus("52","111"));

  }

  public static  String plus(String A,String B){
    EFDefine ef1 = new EFDefine(A);
    EFDefine ef2 = new EFDefine(B);
    EFDefine result =  plus(ef1,ef2);
    return String.valueOf(result.getValue());
  }

  public static EFDefine plus(EFDefine A,EFDefine B){
    int e ;
    double f = 0.0d;
    int sub = A.getE() -B.getE();
    //対阶：e 先取大
    if(sub >= 0){
      e =  A.getE();
    } else {
      e = B.getE();
    }
    if(sub == Integer.MAX_VALUE){
      return  A;
    }
    if(sub == - Integer.MAX_VALUE){
      return  B;
    }
    //计算尾数和阶码
    double  f_;
    if(sub >=0){
      f_= B.getF()*Math.pow(EFDefine.BASE_TEN,-sub) +A.getF();
    } else{
      f_ = A.getF()*Math.pow(EFDefine.BASE_TEN,sub) +B.getF();
    }
    f = f_;
    return  new EFDefine(e,f);
  }

}
