package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class LongestIncreasingSubarray {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findLongestIncreasingSubarray(List<Integer> A) {
    // TODO - you fill in here.
    int l=0, r=0, n=A.size();
    Subarray res = new Subarray(0,-1);

    while(l<A.size()){
      r = l;
      while(r+1<n && A.get(r)<A.get(r+1))
        r++;
      if(res.end - res.start + 1 < r-l+1){
        res.start = l; res.end=r;
      }
      l = r+1;
    }
    return res;
  }
  @EpiTest(testDataFile = "longest_increasing_subarray.tsv")
  public static int findLongestIncreasingSubarrayWrapper(List<Integer> A) {
    Subarray result = findLongestIncreasingSubarray(A);
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestIncreasingSubarray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
