package leetcode;

/**
 * <pre>
 * 作者：shenliang
 * 项目：leetcode
 * 说明：合并列表
 * 日期：2020年04月20日
 * 备注：
 * </pre>
 */
public class MergeList {


  public ListNode merge(ListNode l1 , ListNode l2){
    ListNode node  = null;
    ListNode p = l1;//l1指针
    ListNode q = l2;//l2指针

    while(null != p &&  null != q){



      q = q.next;
      p = p.next;
    }




    return  node;

  }



   public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

}
