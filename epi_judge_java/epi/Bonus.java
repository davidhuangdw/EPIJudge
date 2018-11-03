package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class Bonus {

  private static class EmployeeData {
    public Integer productivity;
    public Integer index;

    public EmployeeData(Integer productivity, Integer index) {
      this.productivity = productivity;
      this.index = index;
    }
  }

  @EpiTest(testDataFile = "bonus.tsv")

  public static Integer calculateBonus(List<Integer> productivity) {
    // TODO - you fill in here.

    int n = productivity.size(), sum=0;
    int v[] = new int [n];
    v[0]=1;
    for(int i=1; i<n; i++)
      v[i] = 1 + (productivity.get(i) > productivity.get(i-1) ? v[i-1]:0);
    for(int i=n-1; i>=0; i--) {
      if (i + 1 < n && productivity.get(i) > productivity.get(i + 1))
        v[i] = Math.max(v[i], v[i + 1]+1);
      sum += v[i];
    }

    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Bonus.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
