package util.http;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.http
 * 说明：
 * 日期：2019年10月31日
 * 备注：
 * </pre>
 */
public class HttpResponseUtil {


  public static void write(HttpServletResponse response,String json){
    try {
      PrintWriter writer = response.getWriter();
      writer.write(json);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
