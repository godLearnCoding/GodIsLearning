package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by god on 2019/4/24.
 */
public class SNode {

    private int value;

    private SNode node = null;

    public SNode(){
        this.value =0;
        this.node =null;
    }

    public SNode(int value){
        this.value = value;
        initNode(value);
    }

    private void initNode(int value){
        if(value == 0){
            return;
        }
        List<SNode> nodes = new ArrayList<>();
        for(int i=0;i<Integer.MAX_VALUE;i++){
            int mo = new Double(Math.pow(10,i)).intValue();
             int rs = value /mo;
             rs = rs % 10;
            if(rs == 0){
                break;
            }
            SNode temp = new SNode();
            temp.value = rs;
            nodes.add(temp);
        }
        /*设置关系*/
        for(int i = 0;i<nodes.size()-1;i++){
            nodes.get(i).node = nodes.get(i+1);
        }
        this.node = nodes.get(0);
    }


    public int initValue(){
        StringBuffer buf = new StringBuffer();
        checkNode(buf,this.node);
        return new Integer(buf.toString()).intValue();
    }

    private void checkNode(StringBuffer buffer,SNode node){
        buffer.insert(0,node.value);
        if(node.getNode() == null){
            return;
        }
        checkNode(buffer,node.getNode());
    }


    public int getValue() {
        return value;
    }

    public SNode getNode() {
        return node;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNode(SNode node) {
        this.node = node;
    }

}
