package leetcode;
import pojo.*;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * Created by god on 2019/4/24.
 */
public class AddTwoNums {

    public static  void  main(String[] args){
//        ListNode listNode = new ListNode(8888999);
//        ListNode addNode = new ListNode(8888123);
//        ListNode rs =listNode.add(addNode);
//        System.out.print(rs.gtetValue());

        SNode n1 = new SNode(341);
        SNode n2 = new SNode(123);
        System.out.print(addTwoNodes2(n1.getNode(),n2.getNode()));

    }

    //bad solution
    public static int addTwoNodes(SNode n1,SNode n2){
        int sum = 0;
        SNode rs = new SNode();
        //确保第二个如参较小
        if(n1.getValue() >= n2.getValue()){
            rs.setValue(n1.getValue());
            rs.setNode(n1.getNode());
            SNode rsNode = rs.getNode();
            checkSumNode(rsNode,n2.getNode());
        } else {
            rs.setValue(n2.getValue());
            rs.setNode(n2.getNode());
            SNode rsNode = rs.getNode();
            checkSumNode(rsNode,n1.getNode());
        }
        return rs.initValue();
    }

    public static SNode addTwoNodes2(SNode n1,SNode n2){
        SNode rs = new SNode(0);
        SNode p=n1,q=n2,curr =rs;
        int carry =0;

        while(p != null || q != null){
            int x = p == null? 0:p.getValue();
            int y = q == null? 0:q.getValue();
            int sum = carry + x + y;
            carry =  sum/10;
            SNode tNode = new SNode();
            tNode.setValue(sum % 10);
            curr.setNode(tNode);
            curr = curr.getNode();
            if(p != null){
                p = p.getNode();
            }
            if(q != null){
                q = q.getNode();
            }

        }
        if(carry == 1){
            curr.setNode(new SNode(1));
        }

        return rs.getNode();


    }






   static  void checkSumNode(SNode pre,SNode next){
        if(next == null){
            return;
        }
        int v1 = pre.getValue();
        int v2 = next.getValue();
        pre.setValue(v1+v2);
        SNode nx =  pre.getNode();
       if(pre.getValue() / 10 == 1){
           pre.setValue(pre.getValue() % 10);
           nx.setValue(1+nx.getValue());
        }
        checkSumNode(pre.getNode(),next.getNode());
    };



}


