package redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 作者：shenliang
 * 项目：redis
 * 说明：Redission客户端
 * 日期：2020年06月19日
 * 备注：
 * </pre>
 */
public class RedisDemo {

  private static RedissonClient redissonClient = null;

  static {
    Config config = new Config();
    config.setCodec(new org.redisson.client.codec.StringCodec());
    //config.setTransportMode(TransportMode.EPOLL);
    //单节点模式
    config.useSingleServer().setAddress("redis://127.0.0.1:8989");
    redissonClient = Redisson.create(config);
  }

  public static void main(String[] args) throws InterruptedException {
    //获取key对象
    RBucket<String> name = redissonClient.getBucket("name");
    //设置过期时间
    boolean b = name.expire(1, TimeUnit.SECONDS);
    System.out.println(b);
    //设置value值
    //name.set("zhansan");
    String o = name.get();
    System.out.println(o);
    Thread.sleep(1000);
    //获取value
    o = name.get();
    System.out.println(o);
    //关闭
    redissonClient.shutdown();
  }

}
