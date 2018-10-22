package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    // TODO - you fill in here.

    int h0 = height(node0);
    int h1 = height(node1);
    if(h0 > h1) node0 = stepUp(node0, h0-h1);
    else if(h0 < h1) node1 = stepUp(node1, h1-h0);

    while(node0 != null && node1 != null && node0 != node1){
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return node0;
  }

  private static int height(BinaryTree<Integer> node){
    return node == null ? 0 : height(node.parent)+1;
  }
  private static BinaryTree<Integer> stepUp(BinaryTree<Integer> node, int k){
    for(int i=0; i<k; i++)
      node = node.parent;
    return node;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
