package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // TODO - you fill in here.

    Collections.sort(A);

    for(int i=0; i<A.size(); i++)
      for(int j=i, k=A.size()-1; j<=k;)
        if(A.get(j) + A.get(k) == t - A.get(i))
          return true;
        else if(A.get(j) + A.get(k) < t - A.get(i))
          j++;
        else
          k--;
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
