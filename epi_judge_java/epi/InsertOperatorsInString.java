package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class InsertOperatorsInString {
  @EpiTest(testDataFile = "insert_operators_in_string.tsv")

  public static boolean expressionSynthesis(List<Integer> digits, int target) {
    // TODO - you fill in here.
    if(digits.size()==0) return target == 0;

    return search(digits, 1, 0, 1, digits.get(0), target);
  }

  private static boolean search(List<Integer> digits, int i, int sum, int mul, int prev, int target){
    if(i==digits.size()){
      sum += mul*prev;
      return target == sum;
    }

    int v = digits.get(i);
    return search(digits, i+1, sum+mul*prev, 1, v, target)
        || search(digits, i+1, sum, mul*prev, v, target)
        || search(digits, i+1, sum, mul, prev*10+v, target);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "InsertOperatorsInString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
