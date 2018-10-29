package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsStaircase {
  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

  public static int numberOfWaysToTop(int top, int maximumStep) {
    // TODO - you fill in here.

    if(top <= 1) return 1;
    int f[] = new int[top+1];
    f[0] = f[1] = 1;
    for(int i=2; i<=top; i++) {
      f[i] = f[i - 1] << 1;
      if(i-maximumStep-1 >= 0)
        f[i] -= f[i-maximumStep-1];
    }
    return f[top];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
