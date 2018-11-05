package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxSumSubarray {
  @EpiTest(testDataFile = "max_sum_subarray.tsv")

  public static int findMaximumSubarray(List<Integer> A) {
    // TODO - you fill in here.
    int res = 0;
    int min = 0, sum=0;
    for(int v:A){
      sum += v;
      res = Math.max(res, sum - min);
      min = Math.min(min, sum);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSumSubarray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
