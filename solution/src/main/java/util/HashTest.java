package util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：
 * 日期：2020年02月26日
 * 备注：
 * </pre>
 */
public class HashTest {

  public static void main(String[] args) {

    System.out.println("张三wwwww".hashCode());
    System.out.println("张三wwwww1".hashCode());

    Set<String> set = new HashSet<>();
    set.add("1");
    set.add("1");
    set.add("2");

    System.out.println(set);
    String filePath = String.format("%s%s%s%s", File.separator,"shenliang",File.separator,"god");
    System.out.println(filePath);
    System.out.println(filePath.replace('\\','/'));
    try {
      URL url = new URL("http://s"+filePath);
      System.out.println(url.toString());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }


  }

}
