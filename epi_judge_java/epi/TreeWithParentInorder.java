package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    // TODO - you fill in here.

    List<Integer> order = new ArrayList<Integer>();
    if(tree == null) return order;

    BinaryTree<Integer> cur = tree;
    while(cur.left != null) cur = cur.left;
    while(cur != null){
      order.add(cur.data);
      //successor
      if(cur.right != null){
        cur = cur.right;
        while(cur.left != null)
          cur = cur.left;
      } else{
        while(cur.parent != null && cur.parent.left != cur)
          cur = cur.parent;
        cur = cur.parent;
      }
    }
    return order;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
