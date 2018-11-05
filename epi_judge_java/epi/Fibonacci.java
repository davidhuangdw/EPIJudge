package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Fibonacci {

  @EpiTest(testDataFile = "fibonacci.tsv")

  public static int fibonacci(int n) {
    // TODO - you fill in here.
    int v[] = new int [2];
    v[1] = 1;
    for(int i=2; i<=n; i++)
      v[i&1] = v[0]+v[1];
    return v[n&1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Fibonacci.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
