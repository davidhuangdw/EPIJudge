package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
public class LongestContainedInterval {
  @EpiTest(testDataFile = "longest_contained_interval.tsv")

  public static int longestContainedRange(List<Integer> A) {
    // TODO - you fill in here.
    int ma = 0;

    HashSet<Integer> set = new HashSet<>(A);
    while (!set.isEmpty()){
      int v = set.iterator().next();
      set.remove(v);
      int l,r;
      for(l=v-1; set.contains(l); l--)
        set.remove(l);
      for(r=v+1; set.contains(r); r++)
        set.remove(r);
      ma = Math.max(ma, r-l-1);
    }
    return ma;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
