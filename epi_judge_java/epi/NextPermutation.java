package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // TODO - you fill in here.

    int top = perm.size()-1;
    while(top-1>=0 && perm.get(top-1) >= perm.get(top))
      top--;
    if(top-1 < 0)
      return Collections.emptyList();

    // first value greater than perm[top-1]
    int gt = perm.size()-1;
    while(perm.get(top-1) >= perm.get(gt))
      gt --;
    Collections.swap(perm, top-1, gt);

    // reverse top..n-1
    for(int i=top, j=perm.size()-1; i<j; i++, j--)
      Collections.swap(perm, i, j);
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
