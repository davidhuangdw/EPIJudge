package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumeratePalindromicDecompositions {
  @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")

  public static List<List<String>> palindromeDecompositions(String input) {
    // TODO - you fill in here.
    int n = input.length();
    isPal = new boolean[n][n];
    for(int d=0; d<n; d++)
      for(int i=0; i+d<n; i++)
        isPal[i][i+d] = (input.charAt(i)==input.charAt(i+d) && (d-2<=0 || isPal[i+1][i+d-1]));

    res = new ArrayList<>();
    cur = new ArrayList<>();
    search(input, 0);

    return res;
  }

  private static boolean [][]isPal;
  private static List<List<String>> res;
  private static List<String> cur;
  private static void search(String s, int st){
    if(st >= s.length()){
      res.add(new ArrayList<>(cur));
      return;
    }

    for(int r=st+1; r<=s.length(); r++)
      if(isPal[st][r-1]){
        cur.add(s.substring(st, r));
        search(s, r);
        cur.remove(cur.size()-1);
      }
  }
  @EpiTestComparator
  public static BiPredicate<List<List<String>>, List<List<String>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
