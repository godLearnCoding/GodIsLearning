package nio;

import java.nio.ByteBuffer;

/**
 * <pre>
 * 作者：shenliang
 * 项目：nio
 * 说明：
 * 日期：2020年05月07日
 * 备注：
 * </pre>
 */
public class JavaNIOTest {


  public static void main(String[] args) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    byte[] dd = {1,2,5,4};
    byteBuffer.clear();
    byteBuffer.put(dd);
    byteBuffer.clear();
    byteBuffer.put(dd);
    System.out.println(byteBuffer);
    //System.out.println(byteBuffer);
    byteBuffer.flip();//读模式
    System.out.println(byteBuffer);
     byte[] reads = new byte[2];
     byteBuffer.get(reads);
    System.out.println(byteBuffer);
    byte b = byteBuffer.get();
    System.out.println(b);
    System.out.println(byteBuffer);
    byteBuffer.compact();
    System.out.println(byteBuffer);
    byteBuffer.put((byte) 1);
    System.out.println(byteBuffer);
    byteBuffer.flip();//读模式
    System.out.println(byteBuffer);
    byte  c= byteBuffer.get();
    System.out.println("c="+c);
  }




}
