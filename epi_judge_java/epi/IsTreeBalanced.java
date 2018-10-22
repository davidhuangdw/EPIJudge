package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    if(tree == null) return true;
    if(!(isBalanced(tree.left) && isBalanced(tree.right)))
      return false;
    return Math.abs(height(tree.left) - height(tree.right)) <= 1;
  }

  private static int height(BinaryTreeNode<Integer> tree){
    if(tree == null) return 0;
    return Math.max(height(tree.left), height(tree.right))+1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
