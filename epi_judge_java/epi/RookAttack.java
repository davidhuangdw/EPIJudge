package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RookAttack {

  public static void rookAttack(List<List<Integer>> A) {
    // TODO - you fill in here.
    int n=A.size(), m=A.get(0).size();
    boolean rows[]=new boolean[n], cols[]=new boolean[m];

    for(int i=0; i<n; i++)
      for(int j=0; j<m; j++)
        if(A.get(i).get(j) == 0)
          rows[i] = cols[j] = true;

    for(int i=0; i<n; i++)
      if(rows[i])
        Collections.fill(A.get(i), 0);

    for(int j=0; j<m; j++)
      if(cols[j])
        for(int i=0; i<n; i++)
          A.get(i).set(j, 0);

    return;
  }
  @EpiTest(testDataFile = "rook_attack.tsv")
  public static List<List<Integer>> rookAttackWrapper(List<List<Integer>> A) {
    List<List<Integer>> aCopy = new ArrayList<>(A);
    rookAttack(aCopy);
    return aCopy;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RookAttack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
