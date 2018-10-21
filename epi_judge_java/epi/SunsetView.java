package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    List<Integer> bd = new ArrayList<Integer>();
    Deque<Integer> views = new LinkedList<Integer>();
    for(Iterator it=sequence; it.hasNext();)
      bd.add((Integer)it.next());
    for(int i=0; i<bd.size(); i++){
      while(!views.isEmpty() && bd.get(views.peek()) <= bd.get(i))
        views.pop();
      views.push(i);
    }

    return views.stream().collect(Collectors.toList());
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
