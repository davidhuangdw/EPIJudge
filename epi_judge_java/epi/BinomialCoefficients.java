package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class BinomialCoefficients {
  @EpiTest(testDataFile = "binomial_coefficients.tsv")

  public static int computeBinomialCoefficient(int n, int k) {
    // TODO - you fill in here.

    int val[] = new int [k+1];
    val[0] = 1;

    for(int i=0; i<=n; i++)
      for(int j=k; j>=1; j--)
        if(j>i)
          val[j] = 0;
        else
          val[j] += val[j-1];

    return val[k];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
