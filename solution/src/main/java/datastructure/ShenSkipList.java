package datastructure;

import java.util.Random;

/**
 * <pre>
 * 作者：shenliang
 * 项目：datastructure
 * 说明：自己实现一个跳表 线程不安全
 * 日期：2020年06月18日
 * 备注：链表+多级索引
 * </pre>
 */
public class ShenSkipList {

  private Node head;

  private int size;//链表原始数据量

  //默认索引2级
  public ShenSkipList(){
    this.size = 0;
    this.level = 2;
  }


  public ShenSkipList(int level){
    if(level < 0){
      throw new RuntimeException("索引层级不能小于0");
    }
    if(level > Index.max_level){
      throw new RuntimeException("索引层级最多支持16级");
    }
    this.size = 0;
    this.level = level;
  }

  //普通查询 同样需要遍历整个链表
  public Object find(int key){
    Node p = head;
    while(p != null){
      if(p.key == key){
        return p.data;
      }
      p = p.next;
    }
    return  null;
  }

  //多级索引查询
  public Object findWithIndex(int key){
    Index h = this.hIndex;
    Index pre = null;
    Node s = null;//真实数据链表指针
    while(h != null){
      if(h.node.key == key){
        return h.node.data;
      } else if(h.node.key > key){
        //索引头还小直接返回
        if(pre ==  null){
          return null;
        }
        h = pre.down;//下沉
        if(null != h){
          s = h.node;
        }
      } else {
        pre = h;
        h = h.next;
      }
    }
    while(s !=null){
      if(s.key == key){
        return s.data;
      }
      s = s.next;
    }
    return  s;
  }

  /**
   *  普遍插入需要遍历整个链表
   *  保证顺序（小到大）
   * @param i
   */
  public void add(int i,Object data){
    Node nd = new Node(i,data,null);
    if(head == null){
      this.head = nd;
      initIndex(this.level);
    } else {
      Node p = head;
      Node pre = null;
      //线性遍历
      while(p != null){
        if(p.key >= i){
          //加到头部
          if(pre == null){
            head = nd;
            //加到中间
          } else {
            pre.next = nd;
          }
          nd.next = p;
          break;
        } else {
          //加到对列尾部
          if(p.next == null){
            p.next = nd;
            break;
          }
          pre = p;
          p = p.next;
        }
      }
    }
    this.size++;
    //创建索引
    addIndex(nd);

  }



  @Override
  public String toString() {
    return this.head.toString();
  }

  private Index hIndex ;//索引头

  private int level;//索引层级

  private Random levelRandom = new Random();

  /**
   *  随机索引层级
   * @return
   */
  public int randomIndexLevel(){
    return levelRandom.nextInt(this.level);
  }
  //初始化索引头层级
  private void initIndex(int level) {
    //顶级索引
    this.hIndex = new Index(level -1,this.head);
    Index p = this.hIndex;
    int i = level -2;
    while(p.down == null && i >= 0){
      p.down = new Index(i,this.head);
      i--;
      p = p.down;
    }
  }

  /**
   * TODO down赋值
   * 创建索引
   * key值为2的n次方时创建
   * @param nd
   */
  private void addIndex(Node nd) {
    if(nd == null){
      return;
    }
    //key值需要2的n次方
    if(((nd.key -1) & (nd.key)) != 0 ){
      return;
    }
    int  level = randomIndexLevel();
     Index p = this.hIndex;
     //找到nd索引层级的头
    Index up = null;
     while(p != null){
       if(p.level == level){
         break;
       } else {
         up = p;
         p =  p.down;
       }
     }
     Index q = p;
     Index pre = null;
     Index newI = new Index(level,nd);
     //insertToLevel(level,,nd);
     //插入到合适的位置
     while(q !=null){
       if(nd.key < q.node.key){
         //换level层级链头
         if(pre == null){
           if(up != null){
             up.down = newI;
           }
           newI.down = q.down;
           q.down = null;
         } else {
           pre.next = newI;
         }
         //TODO down赋值
         newI.next = q;
       } else {
         if(q.next == null){
           q.next = newI;
           break;
         }
         pre = q;
         q = q.next;
       }
    }
  }


  //索引层级随机分布
  //最大层级为16
  private class Index{
    public  static final int max_level = 16;
    int level;//所在层级数
    Node node;
    Index next;
    Index down;
    Index(int level,Node node){
      this.level = level;
      this.node = node;
    }

    Index(int level,Node node, Index next, Index down){
      this.level = level;
      this.node = node;
      this.next = next;
      this.down = down;
    }

    @Override
    public String toString() {
      Index p = this;
      StringBuffer buffer = new StringBuffer();
      while(p != null){
        buffer.append("[level=").append(p.level).append(",").append("node key=").append(p.node.key).append("];");
        p = p.next;
      }
      return buffer.toString();
    }
  }

  //链表节点
  private class Node{
    int key;//主键
    Object data;
    Node next;
    Node(int key,Node next){
      this.key = key;
      this.next = next;
    }

    Node(int key,Object data,Node next){
      this.key = key;
      this.data = data;
      this.next = next;
    }

    public void setData(Object data) {
      this.data = data;
    }

    @Override
    public String toString() {
      Node p = this;
      StringBuffer buffer = new StringBuffer();
      while(p != null){
        buffer.append("[key=").append(p.key).append(",").append("data=").append(p.data).append("];");
        p = p.next;
      }
      return buffer.toString();
    }
  }


  public static void main(String[] args) {
    ShenSkipList shenSkipList = new ShenSkipList();
    for(int i = 0;i< 10000;i++){
      shenSkipList.add(i,"i"+i);
    }
    System.out.println(shenSkipList.find(500));
    System.out.println(shenSkipList.findWithIndex(500));

    //
    //System.out.println(shenSkipList.hIndex);
    //System.out.println(shenSkipList);
    //System.out.println(shenSkipList.find(5));


  }
}
