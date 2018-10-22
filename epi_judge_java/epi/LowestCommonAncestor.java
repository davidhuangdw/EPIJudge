package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.
    if(tree == null) return null;
    BinaryTreeNode<Integer> tmp;
    tmp = LCA(tree.left, node0, node1);
    if(tmp != null) return tmp;
    tmp = LCA(tree.right, node0, node1);
    if(tmp != null) return tmp;
    if(find(tree, node0) && find(tree, node1))
      return tree;
    else
      return null;
  }

  private static boolean find(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node){
    if(root == null) return false;
    if(root == node) return true;
    return find(root.left, node) || find(root.right, node);
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
