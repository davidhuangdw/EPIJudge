package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import epi.TreeConnectLeaves;

public class TreeExterior {

  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    if(tree == null) return Collections.emptyList();

    List<BinaryTreeNode<Integer>> lp = new ArrayList<BinaryTreeNode<Integer>>();
    List<BinaryTreeNode<Integer>> rp = new ArrayList<BinaryTreeNode<Integer>>();
    BinaryTreeNode<Integer> cur = tree;

    if(!isLeaf(tree)) lp.add(tree);
    cur = tree.left;
    while(cur != null && !isLeaf(cur)) {
      lp.add(cur);
      if(cur.left != null)
        cur = cur.left;
      else
        cur = cur.right;
    }
    cur = tree.right;
    while(cur != null && !isLeaf(cur)) {
      rp.add(cur);
      if(cur.right != null)
        cur = cur.right;
      else
        cur = cur.left;
    }
    Collections.reverse(rp);
    lp.addAll(TreeConnectLeaves.createListOfLeaves(tree));
    lp.addAll(rp);
    return lp;
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node){
    return node.left == null && node.right == null;
  }
  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
