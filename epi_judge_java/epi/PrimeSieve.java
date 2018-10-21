package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.

    List<Integer> minp = new ArrayList<Integer>(Collections.nCopies(n+1, 0));
    List<Integer> primes = new ArrayList<>();

    for(int v=2; v<=n; v++){
      if(minp.get(v) == 0) {
        primes.add(v);
        minp.set(v, v); //not ever set
      }

      //multiply 1 extra minimal prime
      for(int i=0; i<primes.size() && primes.get(i)<=minp.get(v) && primes.get(i)*v <= n; i++)
        minp.set(primes.get(i)*v, primes.get(i));
    }

    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
