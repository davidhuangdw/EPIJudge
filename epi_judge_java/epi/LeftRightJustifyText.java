package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class LeftRightJustifyText {
  @EpiTest(testDataFile = "left_right_justify_text.tsv")

  public static List<String> justifyText(List<String> words, int L) {
    // TODO - you fill in here.

    List<String> res = new ArrayList<>(), cur= new ArrayList<>();
    int sum=0;
    for(int i=0; i<words.size(); i++){
      int len = words.get(i).length();
      if(len > L) return Collections.emptyList();
      if(sum + len + cur.size() <= L) {
        sum += len;
        cur.add(words.get(i));
      } else{
        res.add(build(cur, L-sum));
        sum = len;
        cur = new ArrayList<>();
        cur.add(words.get(i));
      }
    }
    String last = String.join(" ", cur) + nSpace(L-sum-(cur.size()-1));
    res.add(last);
    return res;
  }

  private static String build(List<String> A, int space){
    int n = A.size();
    StringBuilder s = new StringBuilder(A.get(0));
    for(int i=1; i<n; i++){
      int sp = (space+n-i-1)/(n-i);
      for(int j=0; j<sp; j++)
        s.append(" ");
      s.append(A.get(i));
      space -= sp;
    }
    for(int i=0; i<space; i++)
      s.append(" ");
    return s.toString();
  }

  private static String nSpace(int n){
    StringBuilder s = new StringBuilder();
    for(int i=0; i<n; i++)
      s.append(" ");
    return s.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LeftRightJustifyText.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
