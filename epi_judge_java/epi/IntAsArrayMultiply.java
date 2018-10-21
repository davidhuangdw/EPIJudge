package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    int sign = num1.get(0) * num2.get(0) >= 0 ? 1 : -1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> sum = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    for (int i = num1.size() - 1; i >= 0; i--) {
      int off = sum.size() - 1 - (num1.size() - 1 - i);
      for (int j = num2.size() - 1; j >= 0; j--, off--) {
        int s = sum.get(off) + num1.get(i) * num2.get(j);
        sum.set(off, s % 10);
        sum.set(off-1, sum.get(off-1)+ s/10);
      }
    }

    //remove leading 0
    int nz=0;
    while(nz<sum.size()-1 && sum.get(nz) == 0) nz++;
    sum = sum.subList(nz, sum.size());
    sum.set(0, sum.get(0)*sign);

    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
