package pojo;

/**
 * Created by god on 2019/4/24.
 */
public class ListNode {

    private int value;

    private Node node;

    public ListNode(){}

    public ListNode(int value){
        this.value = value;
        initNode(value);
    }

    public Node getNode(){
        return this.node;
    }

    private void initNode(int value){
        String vs = new Integer(value).toString();
        char[] chars =vs.toCharArray();
        int i=chars.length-1;
        Node[] nodes = new Node[chars.length];
        for(char c :chars){
            Node n = new Node(c-48);
            nodes[i] = n;
            i--;
        }
        for(int j=0;j<chars.length-1;j++){
            nodes[j].setNext(nodes[j+1]);
        }
        this.node = nodes[0];
    }

    private int initValue(Node node){
        StringBuffer buf = new StringBuffer();
        checkNode(buf,node);
        return new Integer(buf.toString()).intValue();
    }

    private void checkNode(StringBuffer buffer,Node node){
        buffer.insert(0,node.value);
        if(node.next == null){
            return;
        }
        checkNode(buffer,node.next);
    }

    public void setValue(int value){
        this.value = value;
    }

    public int gtetValue(){
       return this.value ;
    }

    public void setNode(Node node){
        this.node = node;
    }

    public ListNode add(ListNode toAdd){
        ListNode rs = new ListNode();
        rs.setNode(this.node);
        rs.setValue(this.value);
        Node toAddNode = toAdd.getNode();
        rs.node.value += toAddNode.getValue();
        int flag  =  rs.node.value/10;
        if(flag == 1){
            rs.node.value = rs.node.value % 10;
            if(rs.node.next != null){
                rs.node.next.value += 1;
            } else {
                rs.node.setNext(new Node(1));
            }
        }
        checkNext(rs.node,rs.node.getNext(),toAddNode.getNext());
        Node rsNode = rs.getNode();
        rs.setValue(initValue(rsNode));
        return rs;
    }

    private void checkNext(Node pre,Node next,Node next_){
        if(next_ == null){
            return;
        }
        if(next == null){
            next = new Node(next_.value);
            pre.setNext(next);
        } else {
            next.value += next_.value;
            int flag =   next.value /10;
            if(flag == 1) {
                next.value = next.value % 10;
                if(next.next != null){
                    next.next.value += 1;
                } else {
                    next.setNext(new Node(1));
                }
            }
        }
        checkNext(next,next.getNext(),next_.getNext());
        }

}
