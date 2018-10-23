package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    // TODO - you fill in here.

    List<Double> cur = new ArrayList<Double>();
    PriorityQueue<Integer> lq = new PriorityQueue<Integer>(100, Comparator.reverseOrder());
    PriorityQueue<Integer> rq = new PriorityQueue<Integer>();
    for(Iterator<Integer> it=sequence; it.hasNext();) {
      int v = it.next();
      if (lq.isEmpty() || v < lq.peek()) {
        lq.add(v);
        if (lq.size() > rq.size() + 1)
          rq.add(lq.poll());
      } else {
        rq.add(v);
        if (lq.size() < rq.size())
          lq.add(rq.poll());
      }
      if (lq.size() == rq.size())
        cur.add((lq.peek() + rq.peek()) / 2.0);
      else
        cur.add(lq.peek() / 1.0);
    }
    return cur;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
