package util.http;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.http
 * 说明：
 * 日期：2019年09月29日
 * 备注：
 * </pre>
 */
public class FastJsonUtil {

  /**
   * 获取 fastjson对象
   * @param json
   * @return
   */
  public static JSONObject toJsonObject(String json){
  return JSONObject.parseObject(json);
  }

  /**
   * 根据json获取对应的java模型
   * @param json
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T toJavaObject(String json, Class<T> clazz) {
    JSONObject jsonObject = toJsonObject(json);
    return  JSONObject.toJavaObject(jsonObject,clazz);
  }

}
