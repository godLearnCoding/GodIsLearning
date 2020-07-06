package huawei;

/**
 * <pre>
 * 作者：shenliang
 * 项目：huawei
 * 说明：
 * 日期：2020年05月19日
 * 备注：
 * </pre>
 */
public class Test {

  public static  int getLastWordLen(String str){
    if(null == str || str.length() < 1){
      return 0;
    }
    char[] chars = str.toCharArray();
    int i = chars.length - 1;
    int count = 0;
    while(i > 0 ){
      if(chars[i--] == ' '){
        break;
      }
      count ++ ;
    }
    return  count;
  }

  public static  int getLastWordLen2(String str){
    int i = str.lastIndexOf(" ");
    System.out.println(str.length() - 1 - i);
     return i;
  }

  public static  void  diveOf8(String line){
    int len = line.length();

    int s = len;
    int start = 0;
    while(s >= 8){
      System.out.println(line.substring(start ,start + 8));
      s = s-8;
      start = start + 8;
    }
    if(len > start){
      StringBuffer buffer = new StringBuffer();
      buffer.append(line.substring(start,len));
      for(int i = 0; i < 8 - len + start; i++ ){
        buffer.append('0');
      }
      System.out.println(buffer.toString());
    }
  }


  public static void  main(String[] args){
    String line = " sdfaaa8";
    System.out.println(line.length());
    diveOf8(line);

  }









}
