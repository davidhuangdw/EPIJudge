package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    if(x==0) return "0";
    boolean neg = (x<0)? true : false;

    StringBuilder s = new StringBuilder();
    while(x!=0){
      s.append((char)('0'+Math.abs(x%10)));
      x/=10;
    }
    if(neg) s.append('-');

    return s.reverse().toString();
  }
  public static int stringToInt(String s) {
    // TODO - you fill in here.
    boolean neg = false;
    int res = 0, i=0;
    if(s.charAt(0) == '-') {
      neg = true;
      i++;
    }
    for(;i<s.length(); i++)
      res = res*10 + (s.charAt(i) - '0');
    if(neg) res = -res;
    return res;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
