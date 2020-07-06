package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：反射工具类
 * 日期：2020年01月08日
 * 备注：
 * </pre>
 */
public   class ReflectUtil<T> {

  /**
   *  动态获取带默认属性值的对象
   * @param tClass
   * @param keyValPair  默认值字段名-值对
   * @return
   */
  public T  getInstanceWithDefaulValue(Class<T> tClass,Map<String,Object> keyValPair) {
    T t = null;
    try {
      t = tClass.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    if(t == null){
      return  null;
    }
  Field[] fields = tClass.getDeclaredFields();
  if(null != fields && null != keyValPair){
    for(Field f :fields){
      //ignore Java language access checks
      f.setAccessible(true);
      String fName = f.getName();
       if(keyValPair.containsKey(fName)){
         try {
           f.set(t,keyValPair.get(fName));
         } catch (IllegalAccessException e) {
           e.printStackTrace();
         }
       }
    }
  }
  return  t;
  }

  public static void main(String[] args) {
    ReflectUtil<User>  util = new ReflectUtil<>();
    Map<String,Object> keyValuePair = new HashMap<>();
    keyValuePair.put("userName","shenliang");
    keyValuePair.put("userId",1);
    User user = util.getInstanceWithDefaulValue(User.class,keyValuePair);
    System.out.println(user);
  }


  public static class  User{
    private String userName;
    public int userId;

    @Override
    public String toString() {
      return "User{" + "userName='" + userName + '\'' + ", userId=" + userId + '}';
    }
  }


}
