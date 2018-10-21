package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.

    boolean neg = numAsString.charAt(0) == '-' ? true : false;
    long x = 0;
    for(int i=neg ? 1: 0; i<numAsString.length(); i++) {
      int d = numAsString.charAt(i) - '0';
      if(!(0<=d && d<10))
        d = numAsString.charAt(i) - 'A' + 10;
      x = x*b1 + d;
    }

    StringBuilder s = new StringBuilder();
    if(x == 0) s.append("0");
    while(x > 0){
      s.append((char)(x%b2<10 ? '0'+x%b2 : 'A'+x%b2-10));
      x /= b2;
    }
    if(neg) s.append('-');
    return s.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
