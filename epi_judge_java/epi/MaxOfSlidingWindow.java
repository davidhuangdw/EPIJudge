package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.*;

public class MaxOfSlidingWindow {
  @EpiUserType(ctorParams = {int.class, double.class})

  public static class TrafficElement implements Comparable<TrafficElement> {
    public int time;
    public double volume;

    public TrafficElement(int time, double volume) {
      this.time = time;
      this.volume = volume;
    }

    @Override
    public int compareTo(TrafficElement o) {
      int volumeCmp = Double.compare(volume, o.volume);
      return volumeCmp != 0 ? volumeCmp : time - o.time;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return compareTo((TrafficElement)o) == 0;
    }

    @Override
    public String toString() {
      return "[" + time + ", " + volume + ']';
    }
  }

  @EpiTest(testDataFile = "max_of_sliding_window.tsv")

  public static List<TrafficElement>
  computeTrafficVolumes(List<TrafficElement> A, int w) {
    // TODO - you fill in here.

    Collections.sort(A, (a,b)->a.time-b.time);
    List<TrafficElement> res=new ArrayList<>();
    Deque<TrafficElement> que = new LinkedList<>();
    for(int i=0; i<A.size(); i++){
      while(!que.isEmpty() && que.getLast().time+w<A.get(i).time)
        que.removeLast();
      while(!que.isEmpty() && que.peek().volume<=A.get(i).volume)
        que.pop();
      que.push(A.get(i));
      res.add(new TrafficElement(A.get(i).time, que.getLast().volume));
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxOfSlidingWindow.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
