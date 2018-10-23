package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import epi.SortedArraysMerge;
public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // TODO - you fill in here.

    List<List<Integer>> arrays = new ArrayList<List<Integer>>();
    int j=0;
    boolean inc=true;

    for(int i=0; i<A.size(); i=j){
      for(j=i+1; j<A.size() && (inc == (A.get(j-1) <= A.get(j))); j++);
      List<Integer> cur = A.subList(i,j);
      if(!inc) Collections.reverse(cur);
      arrays.add(cur);
      inc = !inc;
    }

    return SortedArraysMerge.mergeSortedArrays(arrays);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
