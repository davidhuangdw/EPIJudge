package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
public class SearchFrequentItems {

  public static List<String> searchFrequentItems(int k,
                                                 Iterable<String> stream) {

    HashMap<String, Integer> hash = new HashMap<String, Integer>();
    // TODO - you fill in here.
    int n=0;
    for(String s:stream){
      hash.put(s, hash.getOrDefault(s,0)+1);
      n++;
    }

//    PriorityQueue<String> que = new PriorityQueue<String>(k + 1, (s1, s2)->hash.get(s1)-hash.get(s2));
//    for(String s: hash.keySet()){
//      que.add(s);
//      if(que.size() > k)
//        que.poll();
//    }
//    return que.stream().collect(Collectors.toList());
    double th = n/k;
    return hash.keySet().stream().filter(key -> hash.get(key)>th).collect(Collectors.toList());
  }
  @EpiTest(testDataFile = "search_frequent_items.tsv")
  public static List<String> searchFrequentItemsWrapper(int k,
                                                        List<String> stream) {
    return searchFrequentItems(k, stream);
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFrequentItems.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
