package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int ca=1;
    for(int i=A.size()-1; i>=0 && ca>0; i--){
      int t = (A.get(i)+ca)/10;
      A.set(i, (A.get(i)+ca)%10);
      ca = t;
    }
    if(ca > 0)
      A.add(0, ca);

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
