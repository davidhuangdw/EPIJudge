package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxSquareSubmatrix {

  @EpiTest(testDataFile = "max_square_submatrix.tsv")

  public static int maxSquareSubmatrix(List<List<Boolean>> A) {
    // TODO - you fill in here.

    int m=A.get(0).size();
    int res = 0;
    int cur[] = new int [m];

    for(int i=0; i<A.size(); i++)
      for(int j=0; j<m; j++)
        if(A.get(i).get(j)){
          int h = Math.min(cur[j], j-1>=0 ? cur[j-1] : 0);
          cur[j] = h + (A.get(i-h).get(j-h) ? 1:0);
          res = Math.max(res, cur[j]);
        }else
          cur[j] = 0;

    return res*res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSquareSubmatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
