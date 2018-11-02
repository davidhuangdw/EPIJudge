package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxProductAllButOne {
  @EpiTest(testDataFile = "max_product_all_but_one.tsv")

  public static int findBiggestProductNMinusOneProduct(List<Integer> A) {
    // TODO - you fill in here.

    int maxNeg=-1, minNeg=-1, minPos=-1, nNeg=0, ind=-1;

    for(int i=0; i<A.size(); i++){
      if(A.get(i) < 0){
        nNeg ++;
        if(maxNeg <0 || A.get(i) > A.get(maxNeg))
          maxNeg = i;
        if(minNeg <0 || A.get(i) < A.get(minNeg))
          minNeg = i;
      }else if(minPos < 0 || A.get(i) < A.get(minPos))
        minPos = i;
    }

    ind = nNeg==A.size() ? minNeg: (nNeg%2==0) ? minPos:maxNeg;

    int res = 1;
    for(int i=0; i<A.size(); i++)
      if(i != ind)
        res *= A.get(i);

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxProductAllButOne.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
