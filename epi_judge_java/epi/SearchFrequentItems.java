package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
public class SearchFrequentItems {

  public static List<String> searchFrequentItems(int k,
                                                 Iterable<String> stream) {

    // TODO - you fill in here.
    Map<String, Integer> remain = new ConcurrentHashMap<>();
    for(String s:stream)
      if(remain.containsKey(s) || remain.size()+1 < k)
        remain.put(s, remain.getOrDefault(s,0)+1);
      else{
        for(String rs: remain.keySet())
          if(remain.get(rs) <= 1)
            remain.remove(rs);
          else remain.put(rs, remain.get(rs)-1);
      }

    for(String rs: remain.keySet())
      remain.put(rs, 0);
    int n=0;
    for(String s: stream){
      if(remain.containsKey(s))
        remain.put(s, remain.get(s)+1);
      n++;
    }

    double th = n/k;
    return remain.keySet().stream().filter(key -> remain.get(key)>th).collect(Collectors.toList());
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
