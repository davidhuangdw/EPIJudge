package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class LongestSubarrayWithSumConstraint {
  @EpiTest(testDataFile = "longest_subarray_with_sum_constraint.tsv")

  public static int findLongestSubarrayLessEqualK(List<Integer> A, int k) {
    // TODO - you fill in here.

    int res = 0, n=A.size();
    int sum[] = new int[n+1];
    for(int i=0; i<n; i++)
      sum[i+1] = sum[i] + A.get(i);

    Deque<Integer> right = new LinkedList<>();
    right.push(n);
    for(int i=n-1; i>=0; i--)
      if(sum[i] < sum[right.peek()])
        right.push(i);

    int max = Integer.MIN_VALUE;
    for(int i=0; i<n && !right.isEmpty(); i++) {
      if(sum[i] <= max) continue;
      max = sum[i];
      while (!right.isEmpty() && sum[right.peek()]-sum[i] <= k) {
        res = Math.max(res, right.peek()-i);
        right.pop();
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithSumConstraint.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
