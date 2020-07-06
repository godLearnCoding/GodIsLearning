package collection.node;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection.node
 * 说明：树节点
 * 日期：2020年06月04日
 * 备注：
 * </pre>
 */
public class TreeNode {

   int key;//关键字

  Object data;//卫星数据

  public TreeNode p;//父节点

  public TreeNode left;//左节点

  public TreeNode right;//右节点

  public TreeNode(int key, Object data) {
    this.key = key;
    this.data = data;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public TreeNode getP() {
    return p;
  }

  public void setP(TreeNode p) {
    this.p = p;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  /**
   * 是否根节点
   * @return
   */
  boolean isRoot(){
    return p == null;
  }

  /**
   * 是否叶节点
   * @return
   */
  boolean isLeaf(){
    return  p != null && left == null && right == null;
  }

  @Override
  public String toString() {
    return "TreeNode{" + "key=" + key + ", data=" + data + ", left=" + left + ", right=" + right + '}';
  }
}
