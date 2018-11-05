package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class Combinations {
  @EpiTest(testDataFile = "combinations.tsv")

  public static List<List<Integer>> combinations(int n, int k) {
    // TODO - you fill in here.
    res = new ArrayList<>();
    cur = new ArrayList<>();
    search(n, k, 0);
    return res;
  }
  private static List<List<Integer>> res;
  private static List<Integer> cur;
  private static void search(int n, int k, int st){
    if(k == 0){
      res.add(new ArrayList<>(cur));
      return;
    }
    for(int i=st+1; i<=n-k+1; i++){
      cur.add(i);
      search(n, k-1, i);
      cur.remove(cur.size()-1);
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
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
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
