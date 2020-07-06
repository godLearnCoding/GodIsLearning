package util;

import java.util.StringTokenizer;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：
 * 日期：2020年03月10日
 * 备注：
 * </pre>
 */
public class StringTokenizerTest {

  public static void main(String[] args) {
    //默认空格分割符
    StringTokenizer tok = new StringTokenizer("get,post,shen",",",true);
    while (tok.hasMoreTokens()){
      System.out.println(tok.nextToken());
    }
  }

}
