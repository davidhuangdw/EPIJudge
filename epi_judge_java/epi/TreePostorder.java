package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreePostorder {
  @EpiTest(testDataFile = "tree_postorder.tsv")

  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    List<Integer> order = new ArrayList<Integer>();
    Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<BinaryTreeNode<Integer>>();
    BinaryTreeNode<Integer> cur = tree;
    while(cur != null || !nodes.isEmpty()){
      if(cur != null){
        order.add(cur.data);
        nodes.push(cur.left);
        cur = cur.right;
      }else
        cur = nodes.pop();
    }

    Collections.reverse(order);

    return order;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePostorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
