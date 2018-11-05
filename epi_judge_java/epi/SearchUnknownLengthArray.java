package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchUnknownLengthArray {
  @EpiTest(testDataFile = "search_unknown_length_array.tsv")

  public static int binarySearchUnknownLength(List<Integer> A, int k) {
    // TODO - you fill in here.

    //find size
    int l=0, r=1;
    while(true){
      try{
         A.get(r-1);
      }catch (IndexOutOfBoundsException e){
        break;
      }
      r <<= 1;
    }

    r -= 2;
    while(l<=r){
      int m = l + (r-l)/2;
      try{
        if(A.get(m) == k) return m;
        else if(A.get(m) < k) l = m+1;
        else r = m-1;
      }catch (IndexOutOfBoundsException e){
        r=m-1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchUnknownLengthArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
