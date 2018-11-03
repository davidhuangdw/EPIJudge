package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class KthLargestElementInTwoSortedArrays {
  @EpiTest(testDataFile = "kth_largest_element_in_two_sorted_arrays.tsv")

  public static int findKthNTwoSortedArrays(List<Integer> A, List<Integer> B,
                                            int k) {
    // TODO - you fill in here.
    if(!(1<=k && k<=A.size()+B.size())) return -1;

    int sa=0, sb=0;
    while(k>1){
      int la = k/2, lb=(k+1)/2;
      if((sb+lb-1)>=B.size() || ((sa+la-1)<A.size() && A.get(sa+la-1) <= B.get(sb+lb-1))){
        k -= la;
        sa += la;
      }else{
        k -= lb;
        sb += lb;
      }
    }

    if(sb>=B.size() || (sa<A.size() && A.get(sa) <B.get(sb))) //bug: sa,sb out of size
      return A.get(sa);
    else return B.get(sb);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestElementInTwoSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
