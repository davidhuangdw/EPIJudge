package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    // TODO - you fill in here.

    if(A.size() == 0) return -1;

    int l=0, r=A.size()-1;
    while(l < r){
      int m = l + (r-l)/2;
      //l<=m<m+1<=r
      //keep: l<=min_i<=r
      if(A.get(m) > A.get(r)) //don't compare to A[l], because l==m might happen
        l = m+1;
      else
        r = m;
    }
    return l;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
