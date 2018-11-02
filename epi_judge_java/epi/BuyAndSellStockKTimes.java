package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStockKTimes {
  @EpiTest(testDataFile = "buy_and_sell_stock_k_times.tsv")

  public static double buyAndSellStockKTimes(List<Double> prices, int k) {
    // TODO - you fill in here.
    // todo: ???

    int n = prices.size();
    double last[][] = new double[2][n];
    double res = Double.NEGATIVE_INFINITY;
    if(k*2 > n) return res;

    for(int r=0; r<k; r++){
      int turn = r&1;
      double max=Double.NEGATIVE_INFINITY, pre_max=last[turn^1][r];
      for(int i=0; i<r+1; i++)
        last[turn][i] = Double.NEGATIVE_INFINITY;
      for(int i=r; i+1<n; i++){
        pre_max = Math.max(pre_max, last[turn^1][i]);
        max = Math.max(max, pre_max-prices.get(i));
        last[turn][i+1] = max + prices.get(i+1);
      }
    }

    for(int i=0; i<n; i++)
      res = Math.max(res, last[(k-1)&1][i]);
    return res;
  }

  public static double buyAndSellStockKTimes1(List<Double> prices, int k) {
    // TODO - you fill in here.

    int n = prices.size();
    double last[][] = new double[2][n];
    double res = Double.NEGATIVE_INFINITY;
    if(k*2 > n) return res;

    for(int r=0; r<k; r++){
      int turn = r&1;
      double max=Double.NEGATIVE_INFINITY, pre_max=2*r-1>=0 ? last[turn^1][2*r-1]: 0;
      for(int i=0; i<2*r+1; i++)
        last[turn][i] = Double.NEGATIVE_INFINITY;
      for(int i=2*r; i+1<n; i++){
        max = Math.max(max, pre_max-prices.get(i));
        last[turn][i+1] = max + prices.get(i+1);
        pre_max = Math.max(pre_max, last[turn^1][i]);
      }
    }

    for(int i=0; i<n; i++)
      res = Math.max(res, last[(k-1)&1][i]);
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockKTimes.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
