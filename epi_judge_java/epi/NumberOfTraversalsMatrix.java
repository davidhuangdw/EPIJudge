package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    // TODO - you fill in here.

    int cnt[] = new int [m];
    for(int i=0; i<n; i++)
      for(int j=0; j<m; j++)
        cnt[j] = i==0 || j==0? 1: cnt[j-1]+cnt[j];
    return cnt[m-1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
