package huawei;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：
 * 日期：2019年09月09日
 * 备注：
 * </pre>
 */
public class EFDefine {
    private double f;//数值浮点位，尾数
    private int e;//阶码
    public static  final  int BASE_TEN = 10;//基数

    public EFDefine(){

  }

  public EFDefine(int e,double f){
      this.e = e;
      this.f = f;
  }

  //目前仅处理正数
  public EFDefine(String str) {
      try {
        check(str);
        int len = str.length();
        this.e = len -1;
        this.f = getFbyChar(str.toCharArray());
      }catch (Exception e){
         e.printStackTrace();
      }
  }

  /**
   * @param str 代表正整数的字符串
   * @throws Exception
   */
  private  void check(String str) throws Exception{
    if(null == str || str.length() == 0){
      throw  new Exception("null or blank not allowed");
    }
    char[] chars  = str.toCharArray();
    int i = 0;
    while(i < chars.length && (chars[i]>='0' && chars[i]<='9')){
      i++;
    }
    if(i != chars.length){
      throw  new Exception("non-digital char included");
    }
  }

  /**
   * 获取约定尾数(定点数)
   * 基数>=0 且<=9，小数点在首位右侧
   * @param chars
   * @return
   */
  private double getFbyChar(char[] chars){
    StringBuffer buffer = new StringBuffer();
    buffer.append(chars[0]);
    buffer.append('.');
    int i = 1;
    while(i< chars.length){
      buffer.append(chars[i]);
      i++;
    }
    return Double.valueOf(buffer.toString());
  }


  public double getF() {
    return f;
  }

  public void setF(double f) {
    this.f = f;
  }

  public int getE() {
    return e;
  }

  public void setE(int e) {
    this.e = e;
  }

  /**
   * 获取EF表示的数值
   * @return
   */
  public double getValue(){
      return  f*(Math.pow(BASE_TEN,e));
    }

}
