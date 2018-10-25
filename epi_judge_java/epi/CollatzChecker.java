package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.HashSet;

public class CollatzChecker {
  @EpiTest(testDataFile = "collatz_checker.tsv")

  public static boolean testCollatzConjecture(int n) {
    // TODO - you fill in here.

    HashSet<Integer> tried = new HashSet<Integer>();
    for(int i=3; i<=n; i++){
      int x = i;
      HashSet<Integer> seq = new HashSet<Integer>();
      while(x > i){
        if(!seq.add(x))
          return false;

        if((x & 1) == 0)
          x >>= 1;
        else {
          if(!tried.contains(x))
            break;
          int next = x*3+1;
          if(next <= x)
            throw new Error("Overflow for:"+i);
          x = next;
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CollatzChecker.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
