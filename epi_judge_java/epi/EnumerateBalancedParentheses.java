package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumerateBalancedParentheses {
  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")

  public static List<String> generateBalancedParentheses(int numPairs) {
    // TODO - you fill in here.
    res = new ArrayList<>();
    cur = new char[numPairs*2];
    search(numPairs, 0, 0);
    return res;
  }

  private static void search(int remain, int unpaired, int st){
    if(remain == 0 && unpaired == 0){
      res.add(new String(cur));
      return;
    }

    if(remain>0){
      cur[st] = '(';
      search(remain-1, unpaired+1, st+1);
    }
    if(unpaired > 0){
      cur[st] = ')';
      search(remain, unpaired-1, st+1);
    }
  }

  private static char []cur;
  private static List<String> res;
  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
