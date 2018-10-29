package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class PrettyPrinting {
  @EpiTest(testDataFile = "pretty_printing.tsv")

  public static int minimumMessiness(List<String> words, int lineLength) {
    // TODO - you fill in here.

    int n = words.size();
    int f[] = new int[n+1];
    f[0] = 0;
    for(int i=1; i<=n; i++){
      f[i] = Integer.MAX_VALUE;
      int sum = 0;
      for(int j=i-1; j>=0 && sum+words.get(j).length()+i-j-1 <= lineLength; j--){
        sum += words.get(j).length();
        int mess = lineLength-(sum+(i-j-1));
        f[i] = Math.min(f[i], mess*mess+f[j]);
      }
      if(f[i] == Integer.MAX_VALUE)
        return -1;
    }
    return f[n];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
