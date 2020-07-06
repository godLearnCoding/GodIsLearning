package util.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.cache
 * 说明：最近最久未使用算法缓存
 * 日期：2019年09月24日
 * 备注：最近最久未使用的淘汰
 * 基于LinkedHashMap实现
 * 核心：
 * 1.访问时如果命中缓存已有的数据需更新顺序（newest）
 * 2.增加元素时需置顶表示最新，同时检查是否超过容量，超过容量需移除尾部数据（eldest）
 *
 * </pre>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

  /**
   * 缓存最大容量，不超过Integer.MAX_VALUE
   */
  private  int capatity;

   public LRUCache(int capatity){
     //accessOrder为true，访问（get）时会更新顺序;默认false
     super(capatity,0.75f,true);
     this.capatity = capatity;
  }

  /**
   * LinkedHashMap内部已经实现
   *  元素个数size超过容量时移除最近最久未使用的元素
   * @param eldest
   * @return
   */
  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > this.capatity;
  }

  public static void main(String[] args) {
    LRUCache<String,Integer> cache = new LRUCache<>(5);
    for(int i = 0 ;i<5;i++){
      cache.put("k"+i,i);
    }
    System.out.println("初始化缓存："+cache);
    int k0 = cache.get("k0");
    System.out.println("get(k0)："+cache);
    cache.get("k1");
    System.out.println("get(k1)："+cache);
    cache.put("k10",10);
    System.out.println("put(k10)："+cache);
  }
}
