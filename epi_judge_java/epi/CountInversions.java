package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class CountInversions {
  @EpiTest(testDataFile = "count_inversions.tsv")

  public static int countInversions(List<Integer> A) {
    // TODO - you fill in here.
    if(A.size() <= 1) return 0;

    int m = A.size()/2;
    List<Integer> l=new ArrayList<>(A.subList(0,m)), r=new ArrayList<>(A.subList(m,A.size()));

    int res = countInversions(l) + countInversions(r);
    for(int i=0,j=0,k=0; i<l.size() || j<r.size();){
      if(i>=l.size() || (j<r.size() && r.get(j) < l.get(i))){
        A.set(k++, r.get(j++));
        res += l.size()-i;
      }else
        A.set(k++, l.get(i++));
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountInversions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
