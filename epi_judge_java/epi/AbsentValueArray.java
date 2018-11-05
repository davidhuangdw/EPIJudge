package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbsentValueArray {

  @EpiTest(testDataFile = "absent_value_array.tsv")
  public static int findMissingElement(Iterable<Integer> stream) {
    // TODO - you fill in here.

    List<List<Integer>> group = new ArrayList<>();
    for(int i=0; i<(1<<16); i++)
      group.add(new ArrayList<>());

    for(int v:stream)
      group.get(v>>16).add(v);

    for(int pre=0; pre<group.size(); pre++){
      Set<Integer> set = new HashSet<>();
      for(int v: group.get(pre))
        set.add(v);

      for(int x=0; x<(1<<16); x++)
        if(!set.contains(x | pre))
          return x|pre;
    }

    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AbsentValueArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
