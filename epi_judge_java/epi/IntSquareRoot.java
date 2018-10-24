package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.

    if(k<0) return -1;

    long l=1, r=k;
    while(l<=r){
      long m = l+ (r-l)/2;
      if(m*m <=k)
        l = m+1;
      else
        r = m-1;
    }

    return (int)l-1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
