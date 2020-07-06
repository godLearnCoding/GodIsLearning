package util.http;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：http请求工具
 * 日期：2019年09月26日
 * 备注：发送单次http请求工具
 * 不适用与大量的http请求管理
 * </pre>
 */
public class HttpRequestUtil {

  private static  final int CONNECT_TIMEOUT = 5000;

  /**
   * 发送get请求
   * @param url
   * @return
   * @throws Exception
   */
  public static String doGet(URL url) throws Exception{
    if(null == url){
      throw  new Exception("url cannot be  null");
    }
    HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
    connection.setConnectTimeout(CONNECT_TIMEOUT);
    connection.setRequestProperty("accept", "*/*");
    connection.setRequestProperty("connection", "Keep-Alive");
    connection.setRequestProperty("charset", "utf-8");
    connection.setRequestProperty("user-agent",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    StringBuffer resultBuffer = new StringBuffer();
    BufferedReader bufferedReader= null;
    InputStream is = connection.getInputStream();
    if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
      bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      String line;
      while ((line = bufferedReader.readLine()) != null){
        resultBuffer.append(line);
      }
    }else{
      throw new Exception("get request failed : ResponseCode="+ connection.getResponseCode());
    }
    if(null != bufferedReader){
      bufferedReader.close();
    }
    connection.disconnect();//关闭连接
    return resultBuffer.toString();
  }


  /**
   * 发送post请求
   * @param url URL
   * @param jsonParams 请求参数对字符串，格式json
   * @return
   * @throws Exception
   */
  public static String doPost(URL url, String jsonParams) throws  Exception{
    if(null == url){
      throw  new Exception("url cannot be  null");
    }
    HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
    connection.setConnectTimeout(CONNECT_TIMEOUT);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("accept", "*/*");//任意类型
    connection.setRequestProperty("connection", "Keep-Alive");
    //数据被编码为key/value格式发送到服务器 默认请求类型
    //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setRequestProperty("Content-Type", "application/json");//使用json数据格式发送请求
    connection.setRequestProperty("charset", "utf-8");
    connection.setRequestProperty("user-agent",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    connection.setDoOutput(true);//支持参数
    DataOutputStream  os =  new DataOutputStream(connection.getOutputStream());
    os.writeBytes(jsonParams);
    os.flush();
    os.close();
    StringBuffer resultBuffer = new StringBuffer();
    BufferedReader bufferedReader= null;
    InputStream is = connection.getInputStream();
    if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
      bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      String line;
      while ((line = bufferedReader.readLine()) != null){
        resultBuffer.append(line);
      }
    }else{
      throw new Exception("post request failed : ResponseCode="+ connection.getResponseCode());
    }
    if(null != bufferedReader){
      bufferedReader.close();
    }
    connection.disconnect();//关闭连接
    return resultBuffer.toString();
  }

  /**
   *手机设备User-Agent关键字
   */
  private  final  static String[] deviceArray = new String[]{ "android", "iphone", "ipod","ipad", "windows phone", "mqqbrowser" };

  private  final  static String apacheClient = "Apache-HttpClient";

  /**
   * 请求来自手机
   * @param userAgent
   * @return
   */
  public static boolean isFromMobile(String userAgent){
    if(userAgent == null)
      return true;//保证安全认为是手机可能要加密处理
    userAgent = userAgent.toLowerCase();
    for(int i=0;i<deviceArray.length;i++){
      if(userAgent.indexOf(deviceArray[i])>0){
        return true;
      }
    }
    return false;
  }

  /**
   * 请求来自apacheClient
   * @param userAgent
   * @return
   */
  public static  boolean isFromApacheClient(String userAgent){
    return userAgent != null && userAgent.indexOf(apacheClient) != -1;
  }


  public static void main(String[] args) {
    try {
      //String result = HttpRequestUtil.doGet(URLUtil.getHttpURL("127.0.0.1",8080,"/login.html?username=555"));
      //System.out.println(result);
      URL url = URLUtil.getHttpURL( "127.0.0.1", 8080, "/hello.do");
      JSONObject object = new JSONObject();
      object.put("id","id_88888");
      object.put("name","admin");
      //System.out.println(FastJsonUtil.toJavaObject(object.toJSONString(),User.class));
      String code1 = HttpRequestUtil.doPost(url,object.toJSONString());
      System.out.println(code1);
    }catch (Exception e){
            e.printStackTrace();
    }
  }
}
