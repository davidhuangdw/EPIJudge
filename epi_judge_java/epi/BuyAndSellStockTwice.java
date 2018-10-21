package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.

    double ma = Double.MIN_VALUE, res = 0.0;
    List<Double> snd = new ArrayList<Double>(Collections.nCopies(prices.size(), 0.0));
    for(int i=prices.size()-1; i>=0; i--){
      res = Math.max(ma - prices.get(i), res);
      snd.set(i, res);
      ma = Math.max(prices.get(i), ma);
    }

    double mi = Double.MAX_VALUE;
    for(int i=0; i+1<prices.size(); i++){
      res = Math.max(res, prices.get(i)-mi + snd.get(i+1));
      mi = Math.min(mi, prices.get(i));
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
