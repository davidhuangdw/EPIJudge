package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // TODO - you fill in here.
    HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
    for(char c:magazineText.toCharArray()){
      if(!hash.containsKey(c))
        hash.put(c, 1);
      else
        hash.put(c, hash.get(c)+1);
    }

    for(char c:letterText.toCharArray()){
      if(hash.containsKey(c)){
        int cnt = hash.get(c)-1;
        if(cnt < 0) return false;
        hash.put(c, cnt);
      }
      else return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
