package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Gcd {
  @EpiTest(testDataFile = "gcd.tsv")

  public static long GCD(long x, long y) {
    // GCD of two numbers without using multiplication, division or the modulus operators.
    // TODO - you fill in here.

    if(x==0) return y;
    if(y==0) return x;

    if((x&1)==0 && (y&1)==0)
      return GCD(x>>1, y>>1)<<1;
    if((x&1)==0)
      return GCD(x>>1, y);
    if((y&1)==0)
      return GCD(x, y>>1);

    return x<=y ? GCD((y-x)>>1, x) : GCD((x-y)>>1, y);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Gcd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
