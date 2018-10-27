package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return inrange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean inrange(BinaryTreeNode<Integer> tr, int min, int max){
    if(tr == null) return true;
    return min<=tr.data && tr.data<=max
        && inrange(tr.left, min, tr.data) && inrange(tr.right, tr.data, max);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
