package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    // TODO - you fill in here.

    int sum=0;
    for(int i=0; i<s.length(); i++){
      if(i+1<s.length() && MAPPING.get(s.charAt(i)) < MAPPING.get(s.charAt(i+1)))
        sum -= MAPPING.get(s.charAt(i));
      else
        sum += MAPPING.get(s.charAt(i));
    }

    return sum;
  }

  private static Map<Character, Integer> MAPPING = new HashMap<Character, Integer>(){
    {
      put('I', 1);
      put('V', 5);
      put('X', 10);
      put('L', 50);
      put('C', 100);
      put('D', 500);
      put('M', 1000);
    }
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
