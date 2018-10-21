package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;

public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    // TODO - you fill in here.

    List<List<Integer>> res = new ArrayList<List<Integer>>();
    for(int i=0; i<numRows; i++){
      res.add(new ArrayList<Integer>());
      res.get(i).add(1);
      for(int j=1; j<=i; j++)
        res.get(i).add( j==i ? 1 : res.get(i-1).get(j-1) + res.get(i-1).get(j));
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
