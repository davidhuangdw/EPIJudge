package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    int sign = 1;
    if(x < 0){
      x = -x;
      sign = -1;
    }

    long res=0;
    while(x>0){
      res *= 10;
      res += x %10;
      x /= 10;
    }
    return sign*res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
