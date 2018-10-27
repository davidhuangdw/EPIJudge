package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RangeLookupInBst {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  private static List<Integer> range;

  @EpiTest(testDataFile = "range_lookup_in_bst.tsv")

  public static List<Integer> rangeLookupInBst(BstNode<Integer> tree,
                                               Interval interval) {
    // TODO - you fill in here.
    range = new ArrayList<Integer>();
    search(tree, interval);
    return range;
  }

  private static void search(BstNode<Integer> tree, Interval interval){
    if(tree == null) return;

    int l=interval.left, r=interval.right, v=tree.data;
    if(v >= l)
      search(tree.left, interval);
    if(l<=v && v<=r)
      range.add(v);
    if(v <= r)
      search(tree.right, interval);
  }

  public static void rangeLookupInBstHelper(BstNode<Integer> tree,
                                            Interval interval,
                                            List<Integer> result) {
    if (tree == null) {
      return;
    }
    if (interval.left <= tree.data && tree.data <= interval.right) {
      // tree.data lies in the interval.
      rangeLookupInBstHelper(tree.left, interval, result);
      result.add(tree.data);
      rangeLookupInBstHelper(tree.right, interval, result);
    } else if (interval.left > tree.data) {
      rangeLookupInBstHelper(tree.right, interval, result);
    } else { // interval.right >= tree.data
      rangeLookupInBstHelper(tree.left, interval, result);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RangeLookupInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
