package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    // TODO - you fill in here.

    double l=1.0, r=x;
    if(x < 1.0){
      l=x;
      r=1.0;
    }

    int maxCnt=1000;
    while(l < r){
      double m = l+(r-l)/2;

      if(m*m == x || maxCnt--<=0)
        return m;
      if(m*m < x)
        l = m;
      else
        r = m;
    }

    return l;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
