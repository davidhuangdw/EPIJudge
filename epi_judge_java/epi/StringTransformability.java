package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
public class StringTransformability {

  @EpiTest(testDataFile = "string_transformability.tsv")

  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.

    Deque<String> que = new LinkedList<>(), next;
    int d=0;
    que.push(s);
    D.remove(s);
    while(!que.isEmpty()){
      next = new LinkedList<>();
      while(!que.isEmpty()){
        String w = que.poll();
        if(w.equals(t)) return d;
        for(int i=0; i<w.length(); i++){
          String l=w.substring(0,i), r=i+1<w.length()? w.substring(i+1):"";
          for(int j=0; j<26; j++){
            String nw = l+(char)('a'+j)+r;
            if(D.contains(nw)){
              next.push(nw);
              D.remove(nw);
            }
          }
        }
      }
      que = next;
      d++;
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
