package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeConnectLeaves {

  public static List<BinaryTreeNode<Integer>>
  createListOfLeaves(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<BinaryTreeNode<Integer>> list = new ArrayList<BinaryTreeNode<Integer>>();
    build(tree, list);
    return list;
  }

  private static void build(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> list){
    if(tree == null) return;
    if(tree.left == null && tree.right == null)
      list.add(tree);
    else{
      build(tree.left, list);
      build(tree.right, list);
    }
  }
  @EpiTest(testDataFile = "tree_connect_leaves.tsv")
  public static List<Integer>
  createListOfLeavesWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> createListOfLeaves(tree));

    if (result.stream().anyMatch(x -> x == null || x.data == null)) {
      throw new TestFailure("Result can't contain null");
    }

    List<Integer> extractedRes = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      extractedRes.add(x.data);
    }
    return extractedRes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeConnectLeaves.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
