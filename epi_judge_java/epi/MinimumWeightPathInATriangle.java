package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class MinimumWeightPathInATriangle {
  @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")

  public static int minimumPathTotal(List<List<Integer>> triangle) {
    // TODO - you fill in here.
    if(triangle.size() == 0) return 0;

    int n = triangle.size();
    int f[] = new int[n];
    f[0] = triangle.get(0).get(0);

    for(int i=1; i<n; i++)
      for(int j=i; j>=0; j--){
        int m = Integer.MAX_VALUE;
        if(j < i)
          m = Math.min(m, f[j]);
        if(j-1 >= 0)
          m = Math.min(m, f[j-1]);
        f[j] = m+triangle.get(i).get(j);
      }

    int mi = f[0];
    for(int i=0; i<n; i++)
      mi = Math.min(mi, f[i]);

    return mi;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
