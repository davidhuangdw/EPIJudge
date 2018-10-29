package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class LongestNondecreasingSubsequence {
  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    // TODO - you fill in here.

    int n = A.size();
    Integer f[] = new Integer[n];
    for(int i=0; i<n; i++){
      f[i] = 1;
      for(int j=i-1; j>=0; j--)
        if(A.get(j) <= A.get(i))
          f[i] = Math.max(f[i], f[j]+1);
    }
    return Collections.max(Arrays.asList(f));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
