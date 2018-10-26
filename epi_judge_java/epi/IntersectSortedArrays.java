package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // TODO - you fill in here.

    List<Integer> inter = new ArrayList<Integer>();

    for(int i=0, j=0; i<A.size() && j<B.size();)
      if(A.get(i).equals(B.get(j))){
        if(inter.isEmpty() || !A.get(i).equals(inter.get(inter.size()-1)))
          inter.add(A.get(i));
        i++; j++;
      }else if(A.get(i) < B.get(j))
        i++;
      else
        j++;
    return inter;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
