package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.

    String last = "1";
    while(n-->1){
      StringBuilder s = new StringBuilder();
      int j, cnt;
      for(int i=0; i<last.length(); i++){
        cnt = 1;
//        for(j=i+1; j<last.length() && last.charAt(j)==last.charAt(i); j++)
//          cnt++;
        while(i+1 < last.length() && last.charAt(i+1) == last.charAt(i)){
          i++;
          cnt++;
        }
        s.append(cnt);
        s.append(last.charAt(i));
      }
      last = s.toString();
    }
    return last;
  }

//  public static String intToString(int x){
//    StringBuilder s = new StringBuilder();
//    while(x > 0){
//      s.append( (char)('0'+x%10));
//      x/=10;
//    }
//    return s.reverse().toString();
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
