package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    // TODO - you fill in here.
    List<Integer> res = new ArrayList<>();
    Deque<BstNode<Integer>> st = new LinkedList<BstNode<Integer>>();
    BstNode<Integer> cur = tree;
    while(res.size()<k &&(cur != null ||! st.isEmpty()))
      if(cur != null){
        st.push(cur);
        cur = cur.right;
      }else{
        cur = st.pop();
        res.add(cur.data);
        cur = cur.left;
      }
    return res;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
