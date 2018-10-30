package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    // TODO - you fill in here.

    int max = 0;
    for(int i=0,j=heights.size()-1; i<j;){
      max = Math.max(max, (j-i)*Math.min(heights.get(i), heights.get(j)));
      if(heights.get(i) <= heights.get(j))
        i++;
      else
        j--;
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
