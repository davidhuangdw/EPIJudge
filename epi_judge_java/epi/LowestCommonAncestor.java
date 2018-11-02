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
    count = 0;
    return search(tree, node0, node1);
  }

  public static BinaryTreeNode<Integer>  search(BinaryTreeNode<Integer> tree,
                                                BinaryTreeNode<Integer> node0,
                                                BinaryTreeNode<Integer> node1){
    if(tree == null || count >= 2) return null;
    BinaryTreeNode<Integer> res=null;
    boolean visited = count > 0;
    if(tree == node0)
      count ++;
    if(tree == node1)
      count ++;

    if(count < 2)
      res = search(tree.left, node0, node1);
    if(count < 2)
      res = search(tree.right, node0, node1);
    if(visited || count<2)
      return null;
    else
      return res!=null ? res : tree;
  }

  private static int count = 0;

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
