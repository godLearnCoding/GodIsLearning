package util.encryption;


import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * <pre>
 * 作者：shenliang
 * 项目：web.core.utils
 * 说明：RSA工具
 * 日期：2019年10月15日
 * 备注：
 * </pre>
 */
public class RSAUtil {

  private  static  final  String  ALGORITHIM_RSA = "RSA";

  private static  final  String  ENCRYPTMODE = "RSA/ECB/PKCS1Padding";

  private static final int  KEY_SIZE = 1024;

  /**
   * 随机生成密钥对
   * @return
   */
  public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHIM_RSA);
    //强随机生成密钥对
    byte[] seed =String.valueOf(System.currentTimeMillis()).getBytes();
    generator.initialize(KEY_SIZE,new SecureRandom(seed));
    return generator.generateKeyPair();
  }

  /**
   *加密
   * @param content 加密内容
   * @param key 密钥
   * @return
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static byte[] encodeByKey(byte[] content,Key key) throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHIM_RSA);
    cipher.init(Cipher.ENCRYPT_MODE,key);
    return cipher.doFinal(content);
  }

  /**
   *解密
   * 私钥加密需公钥解密
   * 反之公钥加密需私钥解密
   * @param content 解密内容
   * @param key 密钥
   * @return
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static byte[] decodeByKey(byte[] content,Key key) throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHIM_RSA);
    cipher.init(Cipher.DECRYPT_MODE,key);
    return cipher.doFinal(content);
  }
  public static byte[] decodeByKey_js(byte[] content,Key key) throws Exception {
    Cipher cipher = Cipher.getInstance(ENCRYPTMODE,new BouncyCastleProvider());
    cipher.init(Cipher.DECRYPT_MODE,key);
    return cipher.doFinal(content);
  }

  public static byte[] encodeByKey_js(byte[] content,Key key) throws Exception {
    Cipher cipher = Cipher.getInstance(ENCRYPTMODE,new BouncyCastleProvider());
    cipher.init(Cipher.ENCRYPT_MODE,key);
    return cipher.doFinal(content);
  }


  /**
   *将密钥对象转换为base64编码的string
   * @param key 密钥
   * @return
   */
  public static String keyToStringByBase64(Key key){
    byte[] keyBytes = key.getEncoded();
    return Base64.getEncoder().encodeToString(keyBytes);
  }

  /**
   *base64编码的string 转换为密钥对象（私钥）
   * @param keyStr_base64Encoded
   * @return
   * @throws Exception
   */
  public static PrivateKey stringToPrivateKey(String keyStr_base64Encoded) throws Exception {
   if(keyStr_base64Encoded == null){
       throw new Exception("keyStr cannot be null");
   }
    KeyFactory factory = KeyFactory.getInstance("RSA");
    byte[] bytes = Base64.getDecoder().decode(keyStr_base64Encoded);
    //私钥格式规范PKCS8EncodedKeySpec
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
    return factory.generatePrivate(keySpec);
  }

  /**
   * base64编码的string 转换为密钥对象（公钥）
   * @param keyStr_base64Encoded
   * @return
   * @throws Exception
   */
  public static PublicKey stringToPublicKey(String keyStr_base64Encoded) throws Exception {
    if(keyStr_base64Encoded == null){
      throw new Exception("keyStr cannot be null");
    }
    KeyFactory factory = KeyFactory.getInstance(ALGORITHIM_RSA);
    byte[] bytes = Base64.getDecoder().decode(keyStr_base64Encoded);
    //公钥格式规范X509EncodedKeySpec
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
    return factory.generatePublic(keySpec);
  }

  public static void main(String[] args) throws  Exception {
    KeyPair keyPair = generateKeyPair();
    Key privateKey = keyPair.getPrivate();
    Key publicKey = keyPair.getPublic();
    //String content = "this is plain text, ready to encrypte";
    JSONObject object = new JSONObject();
    object.put("error","10000");
    object.put("message","查询成功");
    //String content = object.toJSONString();
    String c = "12366611111111111111111161a";
    //String content = "d5yA8+w9AODcwZXExR2EQW6bZZEku/tC3A/X4K1wy55KnZykzquU2GM56ck8gWMRX1USTN8kPU8UcWaw411+Sz5gZy86Rkev+Jp5i32zlqMJr95ahE/1E/ZFOcKvVWe8SU5GM23mO7tBE9uSe+f16YldkgSB0yvjglw0Qkc76dg=";//ase
    //String content = "NJwGGxrC/opijFkitkYk6z+LJX8sNOLNbP3q5goAuw0HeW3b2tcxy3+l0lj0Mxq3l2YdqaB+FfWiWd/Mvx0qsRlqPB6T2mVq6AKGw9U9MZcWnYk/bckb8y0NM22Hw9TrEO3sxQUoiY+NEOJC6Ow1BY/vDqhPcoy05d7HvFbqC/I=";
    String content = "FS70MHzJB7TuENhqkJt+dIL0dEHou6GTehWyCdFiviv2BLFJ0LU7JU/O3Wgo5CpiqIN+mSOFcH8KVifbdDi8XNii6OVSHJHSHCtgcnqv3tDBVKU35N5WKSJdypk8mEcUoCsFVjVp6gGgZxqPgci/Hx5eZPsBDTV1aNWzSTl8F0Q=";
    //密钥字符串,base64编码
   //String priStr =  keyToStringByBase64(privateKey);
   //String pubStr = keyToStringByBase64(publicKey);
    String priStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALXOt80bnq4tD87jXRA7Vg0MgoIEl4iN1/5tqhAzHIKCYyCf7GrzEkL0zTUpHclMP0bLVQvC20/P+e8IHGDQC7iKF9TEl9NaLLs81n8rU9Yo7d1bvGt/qJMC5DioRy8XTLu5VvE4PRj+Lu6l85Ahlf/OL8GLo9jtZ3wNW4Fh5bJTAgMBAAECgYBNzCOCtWr1hXraQRi1tW5hLxU5pdViLbBbw3mebQCqKDla4JQ+P710wWR+dxWz6di55pCB8+P9BzYgPwxFsUESAQarN/WsD4OhqyHb/jCQoVdxAFSKBbSSBbdesu4ransREqNI0HuPc0RKRwBrbxRQJv50Jyh1R+KOJesevo/gIQJBAO80w5gV1NHG7Bcot3WAxIFM/O7A0npDuflvKP5+24nW36ikfzWjlILCKO5usYcfS8gMrtu+0jv0jvZPqdR7v0UCQQDCklUQ5SE115zXlF/TzCRefXkhKmMkcZf8Cz1bmRegiNnb26NhGDjMmRYXO/cb3y1LH/DJjvVh4uVq8afG05i3AkBdv6ssrtmywaiDxxRnu+jH2DmFBRM4kdSQWq2RWHyIQCt198pXZl3tBVVvZPi0wWI87COXwei5ULa6+CvSOBGVAkAHNVypb5O3ZvGMmxahxiJizcs7C1L4+qxizhfL2PQjIm7P0mdPJZKVIebLnZEeGzqWWeyVyxIUOk+9ko5gs14pAkBgSUYAmpe7kERPhsrrbdzIi4KPdyWcYKJmEp6tvJWeiDSJeCOWOCwCzK3kcfCH8CajuaOAiju2NGjmOMXSrpyS";
    String pubStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1zrfNG56uLQ/O410QO1YNDIKCBJeIjdf+baoQMxyCgmMgn+xq8xJC9M01KR3JTD9Gy1ULwttPz/nvCBxg0Au4ihfUxJfTWiy7PNZ/K1PWKO3dW7xrf6iTAuQ4qEcvF0y7uVbxOD0Y/i7upfOQIZX/zi/Bi6PY7Wd8DVuBYeWyUwIDAQAB";
    System.out.println("私钥："+priStr);
    System.out.println("公钥："+pubStr);
    Key privateKey_ =  stringToPrivateKey(priStr);
    Key publicKey_ =  stringToPublicKey(pubStr);
    //加密
    byte[] a = c.getBytes();
    byte[] encodeBytes =encodeByKey_js(a,publicKey_);
    //base64
    System.out.println("加密后："+ new String(Base64.getEncoder().encode(encodeBytes)));
    //System.out.println(decodeByKey(HexUtil.hexStr2byte("8B5D323927F01B07475E4B2FFF0C0CCE465548F8C613A4038EDB15D4A2D805D04035C1BDBA2D04C8B16384A4C38BA9F877C5B641830BA1E3E068B061B661163A6D57E0498636C61AD680B0F96309AB572B7CD36814DFA7AD99566C516F954FECAB1C9A0851399F53C7C1698320C66CD3E7C99E1E327731D5232CFF42BA6E0A78"),privateKey));
    System.out.println("原始长度："+a.length);
    byte[] b = decodeByKey_js(Base64.getDecoder().decode(content),privateKey_);
    //解密
    System.out.println("解密后长度："+b.length);
    System.out.println(new String(b));
  }
}
