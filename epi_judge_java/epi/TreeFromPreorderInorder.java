package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    // TODO - you fill in here.

    HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
    for(int i=0; i<inorder.size(); i++)
      index.put(inorder.get(i), i);
    return buildTree(preorder, inorder, 0, 0, preorder.size(), index);
  }

  private static BinaryTreeNode<Integer> buildTree(List<Integer> preorder, List<Integer> inorder,
                                                   int ip, int ii, int n, HashMap<Integer, Integer> index){
    if(n == 0) return null;

    int v = preorder.get(ip);
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(v);
    int ln = index.get(v) - ii;
    tree.left = buildTree(preorder, inorder, ip+1, ii, ln, index);
    tree.right = buildTree(preorder, inorder, ip+1+ln, ii+1+ln, n-ln-1, index);
    return tree;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
