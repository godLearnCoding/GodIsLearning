package util.http;

import util.cache.LRUCache;

import java.util.UUID;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.http
 * 说明：Token管理工具
 * 日期：2019年09月26日
 * 备注：
 * </pre>
 */
public class TokenUtil {

  /**
   * token上限
   */
  private static int tokenNums = 10000;
  private static   LRUCache<String,Token> uuidCache = new LRUCache<>(tokenNums);

  /**
   * 生成token
   * @return token
   *
   */
  public static String createToken(){
    String uuid = UUID.randomUUID().toString();
    uuidCache.put(uuid,new Token(uuid,System.currentTimeMillis()));
    return uuid;
  };

  /**
   * 检查token是否存在
   * @param uuid token标识
   * @return
   */
  public static  boolean checkToken(String uuid){
    return uuidCache.containsKey(uuid);
  }

  /**
   * 删除token
   * @param uuid
   */
  public static void remove(String uuid){
    uuidCache.remove(uuid);
  }

}
