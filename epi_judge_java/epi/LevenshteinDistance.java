package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    // TODO - you fill in here.
    int n = A.length(), m = B.length();
    int cnt[][] = new int [2][m+1];

    for(int i=0; i<=n; i++) {
      int ii = i&1;
      for (int j = 0; j <=m; j++) {
        if(i==0 || j==0)
          cnt[ii][j] = i+j;
        else if(A.charAt(i-1) == B.charAt(j-1))
          cnt[ii][j] = cnt[ii^1][j-1];
        else
          cnt[ii][j] = 1 + Math.min(cnt[ii][j-1], Math.min(cnt[ii^1][j-1], cnt[ii^1][j]));
      }
    }
    return cnt[n&1][m];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
