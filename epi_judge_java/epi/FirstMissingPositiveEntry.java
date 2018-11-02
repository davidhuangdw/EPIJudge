package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class FirstMissingPositiveEntry {
  @EpiTest(testDataFile = "first_missing_positive_entry.tsv")

  public static int findFirstMissingPositive(List<Integer> A) {
    // TODO - you fill in here.

    int n = A.size();
    for(int i=0; i<n; i++){
      int v = A.get(i);
      while(1<=v && v<=n && v-1!=i && A.get(v-1) != v){ //bug: not check A.get(v-1)!=v
        Collections.swap(A, i, v-1);
        v = A.get(i);
      }
    }

    for(int i=0; i<n; i++)
      if(A.get(i) != i+1)
        return i+1;
    return n+1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "FirstMissingPositiveEntry.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
