package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<BinaryTreeNode<Integer>>();
    List<Integer> order = new ArrayList<Integer>();
    BinaryTreeNode<Integer> cur = tree;
    while(cur != null || !nodes.isEmpty()){
      if(cur == null)
        cur = nodes.pop();
      else {
        order.add(cur.data);
        nodes.push(cur.right);
        cur = cur.left;
      }
    }
    return order;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
