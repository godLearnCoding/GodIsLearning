package mutithreads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mutithreads
 * 说明：基于跳表实现的map(高性能、有序的map)
 * 日期：2020年06月18日
 * 备注：单线程下基于跳表的Map性能远远低于ConcurrentHashMap
 * </pre>
 */
public class SkipListMapDemo {

  static  int N = 10000000;//数据量
  static  int threadNums = 50;


  public static void main(String[] args) {
    //基于顺序的SkipListMap
     ConcurrentSkipListMap<Integer,Integer> skipListMap = new ConcurrentSkipListMap<>();
     int i = 0;
     long t1 = System.currentTimeMillis();
     for(;;){
       skipListMap.putIfAbsent(i,i);
       i++;
       if(i == N){
         System.out.println("单线程下skipListMap 插入耗时："+(System.currentTimeMillis() - t1));
         break;
       }
     }
     //HashMap
    ConcurrentHashMap<Integer,Integer> hashMap = new ConcurrentHashMap<>();
    i = 0;
    long t2 = System.currentTimeMillis();
    for(;;){
      hashMap.putIfAbsent(i,i);
      i++;
      if(i == N){
        System.out.println("单线程下hashMap  插入耗时："+(System.currentTimeMillis() - t2));
        break;
      }
    }
    i = 0;
    long t3 = System.currentTimeMillis();
    for(;;){
      skipListMap.get(i);
      i++;
      if(i == N){
        System.out.println("单线程下skipListMap  读取耗时："+(System.currentTimeMillis() - t3));
        break;
      }
    }
    i = 0;
    long t4 = System.currentTimeMillis();
    for(;;){
      hashMap.get(1);
      i++;
      if(i == N){
        System.out.println("单线程下hashMap  读取耗时："+(System.currentTimeMillis() - t4));
        break;
      }
    }
    int num = threadNums;
    CountDownLatch latch1 = new CountDownLatch(num);
    long t5 = System.currentTimeMillis();
    for(int k = 0; k < num; k++){
      new Thread(() ->{
        skipListMap.get(N/2);
        latch1.countDown();
      }).start();
    }
    try {
      latch1.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("多线程下skipListMap  读取耗时："+(System.currentTimeMillis() - t5));

    CountDownLatch latch2 = new CountDownLatch(num);
    long t6 = System.currentTimeMillis();
    for(int k = 0; k < num; k++){
      new Thread(() ->{
        skipListMap.get(N/2);
        latch2.countDown();
      }).start();
    }
    try {
      latch2.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("多线程下hashMap  读取耗时："+(System.currentTimeMillis() - t6));
  }

}
