package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：
 * 日期：2019年11月08日
 * 备注：
 * </pre>
 */
public class StringUtil {

  /**
   * 检查是否为空
   * * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * @param cs
   * @return
   */
  public static boolean isBlank(CharSequence cs){
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for(int i = 0;i < strLen;i++){
      if(!Character.isWhitespace(cs.charAt(i))){
        return false;
      }
    }
      return  true;
  }

  /**
   * 检查是否不为空
   * @param cs
   * @return
   */
  public static boolean isNotBlank(CharSequence cs){
  return !isBlank(cs);
  }


  /**
   * list转换为固定分割符的字符串
   * @param list
   * @param sep
   * @return
   */
  public static String toSepString(List list,String sep){
    StringBuffer buffer =  new StringBuffer();
    int i = 0;
    for(Object object :list){
      buffer.append(object);
      if(i != list.size()-1){
        buffer.append(sep);
      }
      i++;
    }
    return buffer.toString();
  }

  /**
   * set转换为固定分割符的字符串
   * @param set
   * @param sep
   * @return
   */
  public static String toSepString(Set set,String sep){
    StringBuffer buffer =  new StringBuffer();
    int i = 0;
    for(Object object :set){
      buffer.append(object);
      if(i != set.size()-1){
        buffer.append(sep);
      }
      i++;
    }
    return buffer.toString();
  }

  public static List<String> splitWithComma(String strToSplite){
    char comma = ',';
    //char comma_chinese = '\ufffd';//中文逗号
    //strToSplite = strToSplite.replaceAll("，",",");
    if(null == strToSplite || strToSplite.indexOf(comma) == -1){
      return null;
    }
    List<String> resultList = new ArrayList<>();
    char[] chars = strToSplite.toCharArray();
    int next = 0;
    int offset = 0;
    while(next < chars.length){
      //if(isBjChar(chars[next])){
      //  chars[next] = (char) (chars[next] - 65248);
      //}
     if(comma == chars[next]){
       resultList.add(strToSplite.substring(offset,next));
       offset = next+1;
     } else {
       if(next == chars.length -1){
         resultList.add(strToSplite.substring(offset));
       }
     }
     next++;
    }
    return resultList;
  }
  private final static List<String> surportedFileSufixes = Arrays.asList( new String[]{"txt","xml","json","ini","bat","properties","yml"});

  public static void main(String[] args) {
    System.out.println("splitWithComma,adfa ,adfa da dfa ，ae".indexOf("，"));
    List<String> list = splitWithComma("splitWithComma,adfa ,adfa da dfa ,ae");
    for (String s : list){
      System.out.println(s);
    }
  }

}
