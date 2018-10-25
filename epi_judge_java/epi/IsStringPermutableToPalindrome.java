package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.

    HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
    for(char c:s.toCharArray()){
      if(!hash.containsKey(c)) hash.put(c, 0);
      hash.put(c, hash.get(c)+1);
    }

    return hash.values().stream().filter(v -> v%2==1).count() <= 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
