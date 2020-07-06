package util;

import util.http.HttpRequestUtil;
import util.http.URLUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：存socket实现cmpp客户端
 * 日期：2019年12月11日
 * 备注：转为http实现
 * </pre>
 */
public class CmppClient {

  private static  String api_url = "http://39.105.26.145:7862/sms?action=send";

  private static  String acct = "924061";

  private static  String secret = "TmpHhX";

  private static  String extno = "106901013";

  private static  String rt = "json";

  public static void main(String[] args) {
    String md5psw = md5("TmpHhX",extno,"csdx","13621998748");
    System.out.println(md5psw);
    String content =  new String("【一起赢】尊敬的用户，您的验证码是：2355，请在1分钟内输入".getBytes(Charset.forName("utf-8")));
    String file = "/sms?action=send&account=924061&password=WnBtQT&mobile=13621998748&content="+content+"&extno=106901013&rt=json";
    try {
     String re = HttpRequestUtil.doPost(URLUtil.getHttpURL("39.105.26.145",7862,file),"");
      System.out.println(re);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }











 static void bindAndLogin(String ipAddr, int port){
    try {
      Socket socket = new Socket(ipAddr,port);
      OutputStream outputStream = socket.getOutputStream();
      ConnectReqMsgRole connectReqMsgRole = new ConnectReqMsgRole();
      connectReqMsgRole.setHeader(new MsgHeader(39,0x00000001,0x10));
      connectReqMsgRole.setSource_Addr(924061);//id
      String strTimeStamp = getTimeStamp();
      connectReqMsgRole.setAuthenticatorSource(getAuthenticatorSource(924061,106901000,"TmpHhX",strTimeStamp));
      connectReqMsgRole.setTimestamp(Integer.valueOf(strTimeStamp).intValue());
      connectReqMsgRole.setVersion((byte) 0x10);
      outputStream.write(connectReqMsgRole.toBytes());
      outputStream.flush();
      InputStream inputStream = socket.getInputStream();
      int status = inputStream.read();
      if(status == 0){
        System.out.println("登录成功...");
      } else {
        System.out.println("登录失败："+status);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static String getTimeStamp(){
    SimpleDateFormat sf =  new SimpleDateFormat("MMddHHmmss");
    return sf.format(new Date());
  }

  static String md5(String psw,String extno,String contend,String mobile){
    MessageDigest md5 = null;
    byte[] temp = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      temp = md5.digest(String.format("%s%s%s%s",psw,extno,contend,mobile).getBytes("UTF8"));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return new String(temp);
  }
  //虚拟接入码
  int shareCode = 106901000;
  static String getAuthenticatorSource(int spId,int shareCode,String pwd,String timeStamp){
    String str = String.format("%d%d%s%s",spId,shareCode,pwd,timeStamp);
    MessageDigest md5 = null;
    byte[] temp = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.update(str.getBytes("UTF8"));
      temp = md5.digest("".getBytes("UTF8"));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return  new String(temp);
  }

  private  static class MsgHeader{

    /**
     * 消息总长度
     */
    private int total_Length;

    /**
     * 命令或响应类型
     */
    private int command_Id;

    /**
     * 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）
     */
    private int sequence_Id;

   public  MsgHeader(int total_Length,int command_Id,int sequence_Id){
     this.total_Length = total_Length;
     this.command_Id = command_Id;
     this.sequence_Id = sequence_Id;
   }

    byte[] toBytes(){
      byte[] bytes = new byte[12];
      ByteBuffer bb = ByteBuffer.wrap(bytes, 0, 12);
      bb.putInt(this.total_Length);
      bb.putInt(this.command_Id);
      bb.putInt(this.sequence_Id);
      return bytes;

    }


  }

  private static  class ConnectReqMsgRole{

    /**
     * 消息头
     */
    private MsgHeader header;

    /**
     *源地址，此处为SP_Id，即SP的企业代码
     * 长度6
     */
    private int source_Addr;

    /**
     * 用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下：
     AuthenticatorSource =
     MD5（Source_Addr+9 字节的0 +shared secret+timestamp）
     Shared secret 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位
     *长度16
     * */
    private String authenticatorSource;

    /**
     *双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号)
     * 长度1
     */
    private byte version;

    /**
     * 时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐
     * 长度4
     */
    private int timestamp;


    public MsgHeader getHeader() {
      return header;
    }

    public void setHeader(MsgHeader header) {
      this.header = header;
    }

    public int getSource_Addr() {
      return source_Addr;
    }

    public void setSource_Addr(int source_Addr) {
      this.source_Addr = source_Addr;
    }

    public String getAuthenticatorSource() {
      return authenticatorSource;
    }

    public void setAuthenticatorSource(String authenticatorSource) {
      this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
      return version;
    }

    public void setVersion(byte version) {
      this.version = version;
    }

    public int getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(int timestamp) {
      this.timestamp = timestamp;
    }

    byte[] toBytes(){
      byte[] bytes = new byte[39];
      ByteBuffer bb = ByteBuffer.wrap(bytes, 0, 39);
      bb.put(header.toBytes());
      bb.putInt(source_Addr);
      bb.put(authenticatorSource.getBytes());
      bb.put(version);
      bb.putInt(timestamp);
      return bytes;
    }

  }


}
