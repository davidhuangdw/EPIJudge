package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // TODO - you fill in here.

    for(int i=0; i<perm.size(); i++){
      if(perm.get(i) <= 0) continue; // bug: <=0 not <0

      int j = perm.get(i);
      perm.set(i, -j);

      while(j != i){
        Collections.swap(A, i, j);
        //next j
        int t = perm.get(j);
        perm.set(j, -t);
        j = t;
      }
    }

    for(int i=0; i<perm.size(); i++) //restore
      perm.set(i, perm.get(i));
    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
