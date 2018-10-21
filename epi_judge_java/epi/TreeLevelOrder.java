package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<BinaryTreeNode<Integer>> prev = new ArrayList<BinaryTreeNode<Integer>>(), next;
    if(tree != null)
      prev.add(tree);
    while(!prev.isEmpty()){
      List<Integer> list = new ArrayList<Integer>();
      next = new ArrayList<BinaryTreeNode<Integer>>();
      for(BinaryTreeNode<Integer> node:prev){
        list.add(node.data);
        if(node.left != null)
          next.add(node.left);
        if(node.right != null)
          next.add(node.right);
      }
      res.add(list);
      prev = next;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
