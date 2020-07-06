package util.http;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.http
 * 说明：
 * 日期：2019年09月29日
 * 备注：
 * </pre>
 */
public class URLUtil {

  private static  final  String  PROTOCOL_HTTP = "http";
  private static  final  String  PROTOCOL_HTTPS = "https";

  /**
   *
   * @param host 服务器地址或域名
   * @param port 端口 可为-1表示无端口
   * @param file 请求路径 请求url(含query)，如/login.html?username=XX&psw=X
   * @return
   * @throws MalformedURLException
   */
  public static URL getHttpURL(String host,int port,String file) throws MalformedURLException {
    return  new URL(PROTOCOL_HTTP,host,port,file);
  }

  public static URL getHttpsURL(String host,int port,String file) throws MalformedURLException {
    return  new URL(PROTOCOL_HTTPS,host,port,file);
  }


}
