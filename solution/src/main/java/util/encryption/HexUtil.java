package util.encryption;

/**
 * <pre>
 * 作者：shenliang
 * 项目：web.core.utils
 * 说明：16进制工具
 * 日期：2019年10月17日
 * 备注：//TODO 寻找更高效的方式
 * </pre>
 */
public class HexUtil {

  /**
   * 将二进制转换成16进制字符串
   *
   * @param buf
   * @return
   */
  public static String byte2HexStr(byte buf[]) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

  /**
   * 16进制字符串转换为二进制
   * @param hexStr
   * @return
   */
  public static byte[] hexStr2byte(String hexStr) {
    if (hexStr.length() < 1)
      return null;
    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; i++) {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
      result[i] = (byte) (high * 16 + low);
    }
    return result;
  }

  public static void main(String[] args) {
    String plainText = "this is plainText";
    String  hexStr =  byte2HexStr(plainText.getBytes());
    System.out.println("16进制："+hexStr);
    System.out.println("原始字符串："+new String(hexStr2byte(hexStr)));
  }

}
