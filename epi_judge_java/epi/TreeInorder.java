package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    List<Integer> order = new ArrayList<Integer>();
    Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<BinaryTreeNode<Integer>>();
//    Deque<Integer> middles = new LinkedList<Integer>();
//    trv(tree, nodes, middles, order);

    BinaryTreeNode<Integer> cur = tree;
    while(cur != null || !nodes.isEmpty() ){
      if(cur == null){
        cur = nodes.pop();
        order.add(cur.data);
        cur = cur.right;
      } else{
        nodes.push(cur);
        cur = cur.left;
      }
    }
    return order;
  }

//  private static void trv(BinaryTreeNode<Integer> cur, Deque<BinaryTreeNode<Integer>> nodes, Deque<Integer> middles, List<Integer> order){
//    while(cur != null){
//      middles.push(cur.data);
//      nodes.push(cur.right);
//      cur = cur.left;
//    }
//    if(!middles.isEmpty()) {
//      order.add(middles.pop());
//      trv(nodes.pop(), nodes, middles, order);
//    }
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
