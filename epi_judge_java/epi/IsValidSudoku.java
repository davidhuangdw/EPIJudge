package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.

    int n = 9;
    if(partialAssignment.size() != n || partialAssignment.get(0).size() != n)
      return false;

    //row
    for(int i=0; i<n; i++){
      List<Integer> v = new ArrayList<Integer>();
      for(int j=0; j<n; j++)
        v.add(partialAssignment.get(i).get(j));
      if(!check(v))
        return false;
    }

    //column
    for(int j=0; j<n; j++){
      List<Integer> v = new ArrayList<Integer>();
      for(int i=0; i<n; i++)
        v.add(partialAssignment.get(i).get(j));
      if(!check(v))
        return false;
    }

    //region
    for(int si=0; si<n; si+=3)
      for(int sj=0; sj<n; sj+=3){
        List<Integer> v = new ArrayList<Integer>();

        for(int i=si; i<si+3; i++)
          for(int j=sj; j<sj+3; j++)
            v.add(partialAssignment.get(i).get(j));
        if(!check(v))
          return false;
      }

    return true;
  }

  public static boolean check(List<Integer> values){
    List<Boolean> ex = new ArrayList<Boolean>(Collections.nCopies(10, false));
    for(int i=0; i<values.size(); i++){
      int v = values.get(i);
      if(v != 0){
        if(ex.get(v))
          return false;
        ex.set(v, true);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
