package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashMap;
import java.util.List;
public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray
  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                         List<String> keywords) {
    // TODO - you fill in here.

    int [] len = new int[keywords.size()];
    int [] last = new int[keywords.size()];
    HashMap<String, Integer> kwIndex = new HashMap<String, Integer>();
    for(int i=0; i<keywords.size(); i++) {
      kwIndex.put(keywords.get(i), i);
      last[i] = -1;
      len[i] = -1;
    }

    Subarray res = null;
    for(int i=0; i<paragraph.size(); i++){
      String s = paragraph.get(i);
      if(!kwIndex.containsKey(s)) continue;

      int j = kwIndex.get(s);
      if(j==0)
        len[j] = 1;
      else if(last[j-1]>=0 && len[j-1]>=0)
        len[j] = len[j-1] + (i-last[j-1]);
      last[j] = i;

      if(j==keywords.size()-1 && len[j] >= 0
        && (res == null || len[j] < res.end+1-res.start)){
        res = new Subarray(i+1-len[j], i);
      }
    }
    return res==null? new Subarray(0, 0) : res;
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
