package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Base64;

public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    // TODO - you fill in here.

    if(s.length()==0)
      return 0;
    if(t.length()==0 || t.length()<s.length())
      return -1;

    int BASE = 26;
    int power = 1;
    int sHash = s.charAt(0);
    int tHash = t.charAt(0);
    for(int i=1; i<s.length(); i++){
      power *= BASE;
      sHash = sHash*BASE + s.charAt(i);
      tHash = tHash*BASE + t.charAt(i);
    }
    if(sHash == tHash && t.substring(0, s.length()).equals(s))
      return 0;

    for(int i=s.length(); i<t.length(); i++){
      tHash -= power*t.charAt(i-s.length());
      tHash = tHash*BASE + t.charAt(i);
      if(sHash == tHash && t.substring(i+1-s.length(), i+1).equals(s))
        return i+1-s.length();
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
