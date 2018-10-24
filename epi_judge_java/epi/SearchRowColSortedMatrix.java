package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    // TODO - you fill in here.
    if(A.size() == 0) return false;

    int i=0, j=A.get(0).size()-1;
    while(i<A.size() && j>=0){
      int v = A.get(i).get(j);
      if(v == x) return true;
      if(v < x) i++; //remove <=v
      else j--; //remove >=v
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
