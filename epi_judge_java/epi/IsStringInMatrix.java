package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsStringInMatrix {
  static class Pair{
    int i,j;
    public Pair(int ii, int jj){
      i =ii; j=jj;
    }

    @Override
    public boolean equals(Object obj) {
      Pair o = (Pair) obj;
      return i==o.i && j==o.j;
    }

    @Override
    public int hashCode() {
      return i<<4 ^ j;
    }
  }

  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    // TODO - you fill in here.
    if(pattern.size() == 0) return true;

    HashSet<Pair> set = new HashSet<>();
    for(int i=0; i<grid.size(); i++)
      for(int j=0; j<grid.get(i).size(); j++)
        if(pattern.get(0) == grid.get(i).get(j))
          set.add(new Pair(i,j));

    for(int k=1; k<pattern.size() && !set.isEmpty(); k++){
      HashSet<Pair> next = new HashSet<>();
      int v = pattern.get(k);
      for(Iterator<Pair> it=set.iterator(); it.hasNext();){
        Pair p = it.next();
        int i = p.i, j=p.j, di=0, dj=1, ni, nj;
        for(int r=0; r<4; r++){
          ni = i+di; nj = j+dj;
          if(0<=ni && ni<grid.size() && 0<=nj && nj<grid.get(ni).size() && grid.get(ni).get(nj)==v)
            next.add(new Pair(ni, nj));
          int tmp = di; di=dj; dj=-tmp;
        }
      }
      set = next;
    }

    return !set.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
