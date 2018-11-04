package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaximumSubarrayInCircularArray {
  @EpiTest(testDataFile = "maximum_subarray_in_circular_array.tsv")

  public static int maxSubarraySumInCircular(List<Integer> A) {
    // TODO - you fill in here.
    int res=0, min=0, sum=0;
    for(int i=0; i<A.size(); i++){
      sum += A.get(i);
      res = Math.max(res, sum-min);
      min = Math.min(min, sum);
    }

    int max = 0;
    for(int i=A.size()-1, cur=0; i>=0; i--){
      cur += A.get(i);
      max = Math.max(max, cur);
      res = Math.max(res, sum-cur+max);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaximumSubarrayInCircularArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
