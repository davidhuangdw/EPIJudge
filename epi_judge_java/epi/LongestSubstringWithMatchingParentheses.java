package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class LongestSubstringWithMatchingParentheses {
  @EpiTest(testDataFile = "longest_substring_with_matching_parentheses.tsv")

  public static int longestMatchingParentheses(String s) {
    // TODO - you fill in here.

    Deque<Integer> left = new LinkedList<>();
    int res = 0, last=-1;
    for(int i=0; i<s.length(); i++)
      if(s.charAt(i) == '(') left.push(i);
      else if(!left.isEmpty()){
        left.pop();
        res = Math.max(res, i- (left.isEmpty()? last : left.peek()));
      }else last=i;
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "LongestSubstringWithMatchingParentheses.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
