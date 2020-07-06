package util.http;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * <pre>
 * 作者：shenliang
 * 项目：ui.filter
 * 说明：请求包装类型
 * 日期：2019年10月16日
 * 备注：支持修改body
 * </pre>
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

 private byte[] body;

  public HttpRequestWrapper(HttpServletRequest request) {
    super(request);
  }
  public HttpRequestWrapper(HttpServletRequest request,byte[] body) {
    super(request);
    this.body = body;
  }

  @Override
  public BufferedReader getReader(){
    return  new BufferedReader(new StringReader( new String(body)));
  }

  @Override
  public ServletInputStream getInputStream(){
    return  new ServletInputStream() {
      private InputStream in = new ByteArrayInputStream(body);
      @Override
      public int read() throws IOException {
        return in.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      public boolean isReady(){
        return  true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }
    };
  }
}
