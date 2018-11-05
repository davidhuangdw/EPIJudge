package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    // TODO - you fill in here.
    for(int l=0, r=s.length()-1; l<=r;)
      if(!Character.isLetterOrDigit(s.charAt(l)))
        l++;
      else if(!Character.isLetterOrDigit(s.charAt(r)))
        r--;
      else if(Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))){
        l++; r--;
      } else return false;
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
