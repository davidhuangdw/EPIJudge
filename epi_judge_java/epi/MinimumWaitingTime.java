package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class MinimumWaitingTime {
  @EpiTest(testDataFile = "minimum_waiting_time.tsv")

  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    // TODO - you fill in here.

    Collections.sort(serviceTimes);
    int sum = 0, n=serviceTimes.size();
    for(int i=0; i<n-1; i++)
      sum += (n-1-i)*serviceTimes.get(i);
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWaitingTime.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
