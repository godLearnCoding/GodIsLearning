package util;

import pojo.Node;

import java.io.*;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：
 * 日期：2019年10月22日
 * 备注：
 * </pre>
 */
public class CloneUtil {

  /**
   * 相同对象的复制
   * 深复制、多层复制
   * @param obj
   * @param <T>
   * @return
   */
  public static <T extends Serializable> T clone(T obj) {
     T cloneObj = null;
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream obs = new ObjectOutputStream(out);
      obs.writeObject(obj);
      obs.close();
      ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(ios);
      cloneObj = (T)ois.readObject();
      ois.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    }
    return cloneObj;
  }

  public static void main(String[] args) {
    Node node = new Node(2);
    Node next = new Node(1);
    node.setNext(next);
    System.out.println("node next:"+node.getNext());
    Node clone = CloneUtil.clone(node);
    clone.setNext(null);
    System.out.println("clone next:"+clone.getNext());
  }

}
