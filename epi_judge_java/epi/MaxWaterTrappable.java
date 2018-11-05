package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxWaterTrappable {
  @EpiTest(testDataFile = "max_water_trappable.tsv")

  public static int calculateTrappingWater(List<Integer> heights) {
    // TODO - you fill in here.

    int maxi = 0, sum=0, cur;
    for(int i=1; i<heights.size(); i++)
      if(heights.get(i)>heights.get(maxi)) maxi = i;


    cur =0;
    for(int i=0; i<maxi; i++)
      if(heights.get(i) < cur)
        sum += cur-heights.get(i);
      else
        cur = heights.get(i);

    cur = 0;
    for(int i=heights.size()-1; i>=maxi; i--)
      if(heights.get(i) < cur)
        sum += cur-heights.get(i);
      else
        cur = heights.get(i);

    return sum;
  }

  public static int calculateTrappingWater1(List<Integer> heights) {
    // TODO - you fill in here.

    int n=heights.size();
    int lmax[] = new int[n], rmax=0, sum=0;

    for(int i=0; i<n-1; i++)
      lmax[i+1] = Math.max(lmax[i], heights.get(i));

    for(int i=n-1; i>=0; i--){
      sum += Math.max(0, Math.min(rmax, lmax[i])-heights.get(i));
      rmax = Math.max(rmax, heights.get(i));
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxWaterTrappable.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
