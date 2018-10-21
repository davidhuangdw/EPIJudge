package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {

  public static String decoding(String s) {
    // TODO - you fill in here.

    StringBuilder res = new StringBuilder();
    int sum=0;
    for(int i=0; i<s.length(); i++){
      char c = s.charAt(i);
      if('0' <= c && c <= '9')
        sum = sum*10 + c - '0';
      else{
        while(sum>0){
          sum --;
          res.append(c);
        }
      }
    }
    return res.toString();
  }
  public static String encoding(String s) {
    // TODO - you fill in here.

    StringBuilder res = new StringBuilder();
    int cnt = 1;
    for(int i=1; i<=s.length(); i++){
      if(i<s.length() && s.charAt(i) == s.charAt(i-1))
        cnt++;
      else{
        res.append(cnt);
        res.append(s.charAt(i-1));
        cnt = 1;
      }
    }
//    if(s.length()>1) {
//      res.append(cnt);
//      res.append(s.charAt(s.length() - 1));
//    }
    return res.toString();
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
