package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class MaxSafeHeight {
  @EpiTest(testDataFile = "max_safe_height.tsv")

  public static int getHeight(int cases, int drops) {
    // TODO - you fill in here.

    int cnt[] = new int[cases+1];

    for(int d=1; d<=drops; d++)
      for(int c=cases; c>=1; c--)
        cnt[c] += 1+cnt[c-1];
    return cnt[cases];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSafeHeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
