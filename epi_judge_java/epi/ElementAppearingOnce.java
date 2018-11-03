package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class ElementAppearingOnce {
  @EpiTest(testDataFile = "element_appearing_once.tsv")

  public static int findElementAppearsOnce(List<Integer> A) {
    // TODO - you fill in here.
    int cnt[] = new int [32];
    for(int x:A)
      for(int i=0; x!=0 && i<32; i++, x>>=1) //bug: !=0 not >0
        if((x&1) != 0) cnt[i]++;

    int res = 0;
    for(int i=0; i<32; i++)
      if(cnt[i]%3 == 1)
        res |= 1<<i;
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ElementAppearingOnce.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
