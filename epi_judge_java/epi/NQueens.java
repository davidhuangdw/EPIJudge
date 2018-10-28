package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {
  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    // TODO - you fill in here.
    cur = new ArrayList<>();
    res = new ArrayList<>();
    search(0,n);
    return res;
  }
  private static List<Integer> cur;
  private static List<List<Integer>> res;

  private static void search(int r, int n){
    if(r == n){
      res.add(new ArrayList<>(cur));
      return;
    }

    for(int i=0; i<n; i++){
      //check (r,i)
      boolean valid = true;
      for(int rj=0; rj<cur.size() && valid; rj++){
        int j = cur.get(rj);
        if(j==i || Math.abs(r-rj) == Math.abs(i-j))
          valid = false;
      }
      if(!valid) continue;
      cur.add(i);
      search(r+1, n);
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
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
