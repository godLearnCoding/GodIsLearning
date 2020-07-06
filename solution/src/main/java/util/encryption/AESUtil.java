package util.encryption;

import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * <pre>
 * 作者：shenliang
 * 项目：web.core.utils
 * 说明：AES工具
 * 日期：2019年10月16日
 * 备注：
 * </pre>
 */
public class AESUtil {

  public static  final  String  AGLORITHM_AES = "AES";

  private static  final  String ENCRYPTMODE = "AES/ECB/NOPadding";

  public static final  int keySize = 128;

  /**
   * 随机生成AES密钥
   * @return
   * @throws NoSuchAlgorithmException
   */
  public static SecretKey createKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(AGLORITHM_AES);
    keyGenerator.init(keySize,new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes()));
    return keyGenerator.generateKey();
  }

  /**
   * 将SecretKey转换成16进制编码的字符串
   * @param key
   * @return
   */
  public static String keyToString(SecretKey key){
      return HexUtil.byte2HexStr(key.getEncoded());
  }


  /**
   * 将Base64进制编码的aes密钥转换为SecretKey对象
   * @param aesKey_hexStr
   * @return
   */
  public static  SecretKey stringToKey(String aesKey_hexStr) throws Exception {
    if(aesKey_hexStr == null){
      throw  new Exception("aesKey string cannot by null");
    }
    byte[] bytes = Base64.getDecoder().decode(aesKey_hexStr);
    return new SecretKeySpec(bytes,AGLORITHM_AES);
  }

  /**
   *aes加密
   * @param content 明文
   * @param aesKey aes密钥
   * @return
   * @throws Exception
   */
  public static byte[] encode(byte[] content,byte[] aesKey) throws Exception {
    Cipher cipher = Cipher.getInstance(ENCRYPTMODE,new BouncyCastleProvider());
    cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(aesKey,AGLORITHM_AES));
   return cipher.doFinal(content);
  }

  /**
   *
   * @param encodeContent 密文
   * @param aesKey aes密钥byte
   * @return
   * @throws Exception
   */
  public static byte[] decode(byte[] encodeContent,byte[] aesKey) throws  Exception{
    Cipher cipher = Cipher.getInstance(ENCRYPTMODE,new BouncyCastleProvider());
    cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(aesKey,AGLORITHM_AES));
    return cipher.doFinal(encodeContent);
  }

  public static void main(String[] args) throws Exception {
    //生成一个aes密钥
    SecretKey key = createKey();
    //String aesKey = keyToString(key);
    String aesKey = "FEFE3A4B96096A84063AFC7D02C681A5";
    System.out.println("aesKey string:"+aesKey);
    JSONObject object = new JSONObject();
    object.put("custCode","13621998748");
    object.put("custPswd","f944h2fb4944baad3e3g");
    object.put("functionNo",110090);
    object.put("operateStation","");
    object.put("paramList","[]");
    object.put("remarksInfo","0");
    object.put("requestUid","0");
    object.put("systemId","901");
    object.put("token","");
    object.put("version","v1.0");
    object.put("rechargeType","0");
    object.put("onOrOffLine","0");
    object.put("rechargeAmt","100");
    object.put("rechargeAmt","100");
    String plainText = object.toJSONString();
    //加密
    //16进制解码
    key = stringToKey(aesKey);
    //System.out.println(new String(key.getEncoded()));
    byte[] encodeBytes = encode(plainText.getBytes(),key.getEncoded());
    System.out.println(HexUtil.byte2HexStr(encodeBytes));
    //解密
    System.out.println("decoded:"+new String(decode(encodeBytes,key.getEncoded())));
  }

}
