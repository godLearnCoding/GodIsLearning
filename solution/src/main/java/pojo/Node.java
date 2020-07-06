package pojo;

import java.io.Serializable;

/**
 * 单链表
 * singly linked-List
 * Created by god on 2019/4/24.
 */
public class Node implements Serializable{

    int value;

    Node next;

    public Node(int value){
        this.value = value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return  this.next;
    }

}