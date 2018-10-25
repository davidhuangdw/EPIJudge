package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    // TODO - you fill in here.
    HashMap<String, Integer> lasti = new HashMap<String, Integer>();

    int mi = paragraph.size();
    for(int i=0; i<paragraph.size(); i++){
      String s = paragraph.get(i);
      if(lasti.containsKey(s))
        mi = Math.min(mi, i - lasti.get(s));
      lasti.put(s, i);
    }
    return mi==paragraph.size() ? -1 : mi;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
