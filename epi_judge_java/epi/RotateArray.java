package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RotateArray {

  public static void rotateArray(int rotateAmount, List<Integer> A) {
    // TODO - you fill in here.
    rotateAmount = rotateAmount % A.size();
    reverse(A, 0, A.size()-1);
    reverse(A, 0, rotateAmount-1);
    reverse(A, rotateAmount, A.size()-1);

    return;
  }
  private  static void reverse(List<Integer> A, int i, int j){
    while(i<j)
      Collections.swap(A, i++, j--);
  }

  @EpiTest(testDataFile = "rotate_array.tsv")
  public static List<Integer>
  rotateArrayWrapper(TimedExecutor executor, List<Integer> A, int rotateAmount)
      throws Exception {
    List<Integer> aCopy = new ArrayList<>(A);

    executor.run(() -> rotateArray(rotateAmount, aCopy));
    return aCopy;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RotateArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
