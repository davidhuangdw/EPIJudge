package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return sumToNode(tree, 0);
  }

  private static int sumToNode(BinaryTreeNode<Integer> tree, int cur){
    if(tree == null) return 0;
    cur = (cur<<1) + tree.data;
    if(tree.left == null && tree.right == null)
      return cur;
    else
      return sumToNode(tree.left, cur) + sumToNode(tree.right, cur);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
