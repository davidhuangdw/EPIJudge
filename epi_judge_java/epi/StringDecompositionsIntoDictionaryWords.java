package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class StringDecompositionsIntoDictionaryWords {
  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")

  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    // TODO - you fill in here.
    List<Integer> res = new ArrayList<Integer>();
    if(words.size() == 0) return res;

    HashMap<String, Integer> count = new HashMap<String, Integer>();
    for(String w:words)
      count.put(w, count.getOrDefault(w,0)+1);

    int n= words.size(), m=words.get(0).length();

    for(int i=0; i+n*m<=s.length(); i++) {
      HashMap<String, Integer> cur = new HashMap<String, Integer>();
      int j;
      for(j=0; j<n; j++){
        String w = s.substring(i+j*m, i+j*m+m);
        if(!count.containsKey(w)) break;
        cur.put(w, cur.getOrDefault(w,0)+1);
        if(cur.get(w) > count.get(w)) break;
      }
      if(j == n)
        res.add(i);
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
