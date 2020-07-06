package util.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * <pre>
 * 作者：shenliang
 * 项目：ui.filter
 * 说明：响应包装类
 * 日期：2019年10月16日
 * 备注：支持响应数据加密
 * 修改后的响应内容使用原HttpServletResponse对象的writer写入即可
 * </pre>
 */
public class HttpResponseWrapper extends HttpServletResponseWrapper {

  private ByteArrayOutputStream buffer = null;
  private ServletOutputStream out = null;
  private PrintWriter writer = null;

  public HttpResponseWrapper(HttpServletResponse resp) throws IOException {
    super(resp);
    // 真正存储数据的流
    buffer = new ByteArrayOutputStream();
    out = new WapperedOutputStream(buffer);
    writer = new PrintWriter(new OutputStreamWriter(buffer,
        this.getCharacterEncoding()));
  }

  /** 重载父类获取outputstream的方法 */
  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return out;
  }


  /** 重载父类获取flushBuffer的方法 */
  @Override
  public void flushBuffer() throws IOException {
    if (out != null) {
      out.flush();
    }
    if (writer != null) {
      writer.flush();
    }
  }

  @Override
  public void reset() {
    buffer.reset();
  }

  /** 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据 */
  public byte[] getResponseData() throws IOException {
    flushBuffer();
    return buffer.toByteArray();
  }

  /** 内部类，对ServletOutputStream进行包装 */
  private class WapperedOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream bos = null;

    public WapperedOutputStream(ByteArrayOutputStream stream)
        throws IOException {
      bos = stream;
    }

    @Override
    public void write(int b) throws IOException {
      bos.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
      bos.write(b, 0, b.length);
    }

    @Override
    public boolean isReady() {
      return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
  }
}
