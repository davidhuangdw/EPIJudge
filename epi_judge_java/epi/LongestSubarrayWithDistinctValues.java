package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
public class LongestSubarrayWithDistinctValues {
  @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    // TODO - you fill in here.

    int res = 0;
//    HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
//    int repeats = 0;
//
//    for(int l=0, r=0; l<A.size();)
//      if(repeats > 0){
//        int v = A.get(l);
//        count.put(v, count.getOrDefault(v, 0) - 1);
//        if (count.get(v) == 1)
//          repeats--;
//        l++;
//      } else{
//        res = Math.max(res, r-l);
//
//        if(r >= A.size()) break;
//        //check A[r]
//        int v = A.get(r);
//        count.put(v, count.getOrDefault(v, 0) + 1);
//        if (count.get(v) == 2)
//          repeats++;
//        r++;
//      }

    HashMap<Integer, Integer> last = new HashMap<Integer, Integer>();
    int distinctStart=0;

    for(int i=0; i<A.size(); i++){
      int v = A.get(i);
      Integer pre = last.get(v);
      if(pre != null)
        distinctStart = Math.max(distinctStart, pre+1);
      res = Math.max(res, i+1-distinctStart);
      last.put(v, i);
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
