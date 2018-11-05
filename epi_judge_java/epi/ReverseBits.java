package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {

  static int cache[] = new int[1<<16];
  static {
    for(int x=0; x<(1<<16); x++){
      int t = x;
      for(int i=0; i<16; i++){
        cache[x] <<= 1;
        cache[x] |= t&1;
        t >>=1;
      }
    }
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // TODO - you fill in here.

    long y=0, mask = (1<<16)-1;
    for(int i=0; i<4; i++){
      y <<= 16;
      y |= (long)cache[(int)(x & mask)];
      x >>= 16;
    }
    return y;
  }

  public static long reverseBits1(long x) {
    // TODO - you fill in here.
    long y = 0;
    for(int i=0; i<64; i++){
      y <<= 1;
      y |= x&1;
      x >>= 1;
    }
    return y;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
