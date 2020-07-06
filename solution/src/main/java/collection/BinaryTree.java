package collection;

import collection.node.TreeNode;

/**
 * <pre>
 * 作者：shenliang
 * 项目：collection
 * 说明：
 * 日期：2020年06月04日
 * 备注：
 * </pre>
 */
public class BinaryTree {

  private TreeNode root;

  public BinaryTree(){
  }

  public BinaryTree(TreeNode root){
    this.root = root;
  }

  /**
   * 根据node数组顺序(key升序)自动构建一个完全二叉树
   * @param treeNodes 未指定p,left,rignt的TreeNode数组
   */
  public  BinaryTree buildCompleteTree(TreeNode[] treeNodes){
    if(treeNodes == null || treeNodes.length <1){
      return null;
    }
    int len =  treeNodes.length;
    this.root = treeNodes[0];
    //int h = log2(treeNodes.length +1);//树高度
    for(int i = 0;i < len; i++){
      treeNodes[i].p = (i+1)/2 -1 >= 0 ? treeNodes[(i+1)/2 -1]  : null;
      treeNodes[i].left = (2*i+1) <= len-1 ? treeNodes[2*i+1] : null;
      treeNodes[i].right = (2*i+2) <= len-1 ? treeNodes[2*i+2] : null;
    }
    return this;
  }


  /**
   * 是否为完全二叉树
   * @return
   */
  public boolean isComplete(){
    //TODO
    return false;
  }

  /**
   * 是否为满二叉树
   * @return
   */
  public boolean isFull(){
    //TODO
    return false;
  }

  /**
   * 获取二叉树高度
   * @return
   */
  public int getH(){
    //TODO
    return 1;
  }

  private   int log2(int n){
    int h = new Double(Math.log(n)/Math.log(2)).intValue();
    double e = 10e-15;
    return n - Math.pow(2.0,h) > e ?h+1 : h;
  }

  private enum SearchModelEnum{
    pre,mid,last;
  }

  //递归遍历查找树-前中后序遍历
  public static void walkTree(TreeNode treeNode,SearchModelEnum model){
    if(treeNode != null){
        if(SearchModelEnum.pre == model){
          System.out.println(treeNode.getKey());//前序遍历
        }
        walkTree(treeNode.left,model);
      if(SearchModelEnum.mid == model){
        System.out.println(treeNode.getKey());//中序查找可以升序输出所有节点
      }
        walkTree(treeNode.right,model);
      if(SearchModelEnum.last == model){
        System.out.println(treeNode.getKey());//后序遍历
      }
    }
  }

  @Override
  public String toString() {
    return "BinaryTree{" + "root=" + root + '}';
  }

  public static void main(String[] args) {
    //构建完全二叉树
    TreeNode[] treeNodes = new TreeNode[]{new TreeNode(1,null),new TreeNode(2,null),new TreeNode(3,null),new TreeNode(4,null),new TreeNode(5,null),new TreeNode(6,null)};
     BinaryTree tree = new BinaryTree().buildCompleteTree(treeNodes);
    System.out.println(tree);
    //手动构建
    TreeNode root = new TreeNode(5,null);
    TreeNode node1 = new TreeNode(3,null);
    TreeNode node2 = new TreeNode(2,null);
    TreeNode node3 = new TreeNode(5,null);
    TreeNode node4 = new TreeNode(7,null);
    TreeNode node5 = new TreeNode(8,null);
    //root
    root.p = null;
    root.left = node1;
    root.right = node4;
    //node1
    node1.p = root;
    node1.left = node2;
    node1.right =node3;
    //node2
    node2.p = node1;
    //node3
    node3.p = node1;
    //node4
    node4.p = root;
    node4.right = node5;
    //node5
    node5.p = node4;
    walkTree(root,SearchModelEnum.mid);
  }
}
