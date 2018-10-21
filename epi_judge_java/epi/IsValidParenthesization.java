package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.serialization_traits.LinkedListTraits;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.

    Deque<Character> remain = new LinkedList<Character>();
    for(int i=0; i<s.length(); i++){
      char c = s.charAt(i);
      Character l = PAIR.get(c);
      if(l==null)
        remain.push(c);
      else{
        if(remain.isEmpty() || remain.peek() != l)
          return false;
        remain.pop();
      }
    }
    return remain.isEmpty();
  }

  private static HashMap<Character, Character> PAIR = new HashMap<Character, Character>(){{
    put('}', '{');
    put(')', '(');
    put(']', '[');
  }};

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
