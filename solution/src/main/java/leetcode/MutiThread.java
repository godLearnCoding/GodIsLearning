package leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * <pre>
 * 作者：shenliang
 * 项目：leetcode
 * 说明：
 * 日期：2020年04月20日
 * 备注：
 * </pre>
 */
public class MutiThread {

  class Foo {

    private volatile int mark = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
      try {
        printFirst.run();
        mark = 2;
        synchronized (this){
          notifyAll();
        }
      }catch (Exception e){
        e.printStackTrace();
      }finally {
      }

    }

    public void second(Runnable printSecond) throws InterruptedException {
      try {
        while (mark != 2){
          synchronized (this){
            wait();
          }
        }
        printSecond.run();
        mark = 3;
        notifyAll();
      }catch (Exception e){
        e.printStackTrace();
      }finally {
      }

    }

    public void third(Runnable printThird) throws InterruptedException {
      while(mark != 3){
        synchronized (this){
          wait();
        }
      }
      printThird.run();
    }
  }

  class FooBar {
    private int n;

    public FooBar(int n) {
      this.n = n;
    }

    private volatile  int  j = 0;

    private volatile  int  k = 0;

    public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        printFoo.run();
        j = 1 ;
        k = 0;
        while(j - k == 1){Thread.yield();}
        // printFoo.run() outputs "foo". Do not change or remove this line.

      }
    }

    public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        while( j - k == 0){}
        printBar.run();
        k = 1 ;
        // printBar.run() outputs "bar". Do not change or remove this line.

      }
    }
  }

  class FooBar2 {
    private int n;

    private volatile int  mark =  0;

    public FooBar2(int n) {
      this.n = n;
    }


    public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        while(mark == 1){Thread.yield();}
        printFoo.run();
        mark = 1 ;
        // printFoo.run() outputs "foo". Do not change or remove this line.

      }
    }

    public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        while(mark == 0 ){}
        printBar.run();
        mark = 0;
        // printBar.run() outputs "bar". Do not change or remove this line.
      }
    }
  }

  class FooBar3 {
    private int n;

    private Semaphore s1 = new Semaphore(1);

    private Semaphore s2 = new Semaphore(0);

    public FooBar3(int n) {
      this.n = n;
    }


    public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        s1.acquire();
        printFoo.run();
        s2.release();
        // printFoo.run() outputs "foo". Do not change or remove this line.

      }
    }

    public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        s2.acquire();
        printBar.run();
        s1.release();
        // printBar.run() outputs "bar". Do not change or remove this line.
      }
    }
  }

  class ZeroEvenOdd {
    private int n;

    private Semaphore s1 = new Semaphore(1);

    private Semaphore s2 = new Semaphore(0);

    private Semaphore s3 = new Semaphore(0);

    public ZeroEvenOdd(int n) {
      this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
      for(int i = 0 ; i < n ; i++){
        s1.acquire();
        printNumber.accept(0);
        if(i %2 == 0){
          s2.release();
        } else {
          s3.release();
        }
      }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
      for(int i = 1; i <= n; i = i+2){
        s2.acquire();
        printNumber.accept(i);
        s1.release();
        //
      }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
      for(int i = 2; i <= n; i = i+2){
        s3.acquire();
        printNumber.accept(i);
        s1.release();
      }
    }
  }

}
