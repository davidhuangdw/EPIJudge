package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    List<Integer> res = new ArrayList<Integer>();

    int di=0, dj=1, r=0, l=squareMatrix.size();
    int i=0, j=-1;
    while(l>0){
      for(int d=l; d>0; d--){
        i += di;
        j += dj;
        res.add(squareMatrix.get(i).get(j));
      }
      int t = di;
      di = dj;
      dj = -t;
      r ^= 1;
      if(r == 1) l--;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
