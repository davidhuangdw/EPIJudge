package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int xx, int yy) {
    long x = (long)xx;
    long y = (long)yy;
    int k=32;
    int res = 0;
    while(k>=0){
      if(x >= (y<<k)){
        x -= (y<<k);
        res |= (1<<k);
      }
      k--;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
