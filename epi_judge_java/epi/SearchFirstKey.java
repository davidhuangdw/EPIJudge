package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    // TODO - you fill in here.

    int l=0, r=A.size()-1;
    boolean exist = false;
    while(l<=r){
      int m = l + (r-l)/2;
      if(A.get(m) < k)
        l = m+1;
      else {
        if(A.get(m) == k)
          exist = true;
        r = r - 1;
      }
    }
    return exist? r+1 : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
