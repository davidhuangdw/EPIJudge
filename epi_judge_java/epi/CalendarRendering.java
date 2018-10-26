package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    // TODO - you fill in here.

    List<Endpoint> list = new ArrayList<Endpoint>();
    for(Event e:A){
      list.add(new Endpoint(e.start, true));
      list.add(new Endpoint(e.finish, false));
    }

    Collections.sort(list, (e1, e2)-> (e1.time < e2.time || (e1.time == e2.time && e1.isStart)) ? -1: 1 );
    int res = 0;
    int overlaps = 0;
    for(Endpoint e:list)
      if(e.isStart)
        res = Math.max(res, ++overlaps);
      else
        overlaps--;
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
