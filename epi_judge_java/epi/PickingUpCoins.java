package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class PickingUpCoins {
  @EpiTest(testDataFile = "picking_up_coins.tsv")

  public static int pickUpCoins(List<Integer> coins) {
    // TODO - you fill in here.

    int n = coins.size(), sum=0;
    if(n==0) return 0;
    int f[] = new int[n];

    for(int l=1; l<=n; l++)
      for(int i=0; i+l<=n; i++)
        if(l==1)
          sum += f[i] = coins.get(i);
        else
          f[i] = Math.max(coins.get(i+l-1)-f[i], coins.get(i)-f[i+1]);
    return (sum+f[0])>>1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PickingUpCoins.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
