package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class EnumerateTrees {

  public static List<BinaryTreeNode<Integer>>
  generateAllBinaryTrees(int numNodes) {
    // TODO - you fill in here.
    List<BinaryTreeNode<Integer>> res = new ArrayList<>(), ltrees, rtrees;
    if(numNodes == 0) res.add(null);
    for(int l=0; l<=numNodes-1; l++){
      int r = numNodes-1-l;
      ltrees = generateAllBinaryTrees(l);
      rtrees = generateAllBinaryTrees(r);
      for(BinaryTreeNode<Integer> lt:ltrees)
        for(BinaryTreeNode<Integer> rt:rtrees)
          res.add(new BinaryTreeNode<Integer>(0, lt, rt));
    }
    return res;
  }
  public static List<Integer> serializeStructure(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> p = stack.pop();
      result.add(p == null ? 0 : 1);
      if (p != null) {
        stack.push(p.left);
        stack.push(p.right);
      }
    }
    return result;
  }

  @EpiTest(testDataFile = "enumerate_trees.tsv")
  public static List<List<Integer>>
  generateAllBinaryTreesWrapper(TimedExecutor executor, int numNodes)
      throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> generateAllBinaryTrees(numNodes));

    List<List<Integer>> serialized = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      serialized.add(serializeStructure(x));
    }
    serialized.sort(new LexicographicalListComparator<>());
    return serialized;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateTrees.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
