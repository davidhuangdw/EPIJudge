package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.

    int n = squareMatrix.size();
    for(int si=0; n>1; si++,n-=2)
      for(int d=0; d<n-1; d++){
        int sj = si;
        int [][] corners = {{si, sj+d}, {si+d, sj+n-1}, {si+n-1, sj+n-1-d}, {si+n-1-d, sj}};
        int last = squareMatrix.get(corners[3][0]).get(corners[3][1]);
        for(int k=0; k<4; k++){
          int i = corners[k][0];
          int j = corners[k][1];
          int tmp = squareMatrix.get(i).get(j);
          squareMatrix.get(i).set(j, last);
          last = tmp;
        }
      }

    return;
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
