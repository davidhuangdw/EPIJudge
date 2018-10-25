package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    // TODO - you fill in here.

    Subarray res = null;
    int exists = 0;
    HashMap<String, Integer> count = new HashMap<String, Integer>();
    String s;
//    int j=0;
//    for(int i=0; i<=paragraph.size()-keywords.size(); i++){
//      while(exists < keywords.size() && j<paragraph.size()){
//        s = paragraph.get(j++);
//        if(!keywords.contains(s)) continue;
//        count.put(s, count.getOrDefault(s,0)+1);
//        if(count.get(s) == 1)
//          exists++;
//      }
//      if(exists == keywords.size())
//        res = getMin(res, new Subarray(i,j-1));
//      else if(j == paragraph.size())
//        break;
//
//      //remove p[i]
//      s = paragraph.get(i);
//      if(keywords.contains(s)) {
//        count.put(s, count.get(s) - 1);
//        if (count.get(s) == 0)
//          exists--;
//      }
//    }
    for(int l=0,r=0; l<paragraph.size();) {
      if (exists == keywords.size()) {
        res = getMin(res, new Subarray(l,r-1));
        //remove p[l]
        s = paragraph.get(l);
        if(keywords.contains(s)){
          count.put(s, count.get(s)-1);
          if(count.get(s) == 0)
            exists--;
        }
        l++;
      } else if(r<paragraph.size()){
        // add p[r]
        s = paragraph.get(r);
        if(keywords.contains(s)){
          count.put(s, count.getOrDefault(s,0)+1);
          if(count.get(s) == 1)
            exists++;
        }
        r++;
      }
      else break;
    }
    return res==null ? new Subarray(0,0) : res;
  }

  private static Subarray getMin(Subarray s1, Subarray s2){
    if(s1 == null) return s2;
    if(s2 == null) return s1;
    return s1.end-s1.start <= s2.end-s2.start ? s1: s2;
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
