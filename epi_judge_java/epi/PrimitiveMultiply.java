package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long res=0;
    int k =0;
    while(y>0){
      if((y&1) != 0)
        res = add(res, x<<k);
      y >>= 1;
      k++;
    }
    return res;
  }

  public static long add(long x, long y){
    long res = 0;
    long ca=0;
    int k=1;
    while(x>0 || y>0){
      long xb = x & k;
      long yb = y & k;
      res |= xb ^ yb ^ ca;
      ca = (xb & ca) | (yb & ca) | (xb & yb);
      ca<<=1;

      x ^= xb;
      y ^= yb;
      k <<= 1;
    }
    return res | ca;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
