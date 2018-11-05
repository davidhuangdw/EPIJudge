package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Arbitrage {
  @EpiTest(testDataFile = "arbitrage.tsv")

  public static boolean isArbitrageExist(List<List<Double>> graph) {
    // TODO - you fill in here.
    // todo: fix

    int n = graph.size();
    int src = 0; //source as 0
    List<Double> dist = new ArrayList<>(Collections.nCopies(n, 0.0));
    dist.set(src, 1.0);

    for(int r=0; r<graph.size(); r++)
      for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
          if( dist.get(j) < dist.get(i)*graph.get(i).get(j))
            dist.set(j, dist.get(i)*graph.get(i).get(j));

    return dist.get(src) > 1.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Arbitrage.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
