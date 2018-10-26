package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class FindSalaryThreshold {
  @EpiTest(testDataFile = "find_salary_threshold.tsv")

  public static double findSalaryCap(int targetPayroll,
                                     List<Integer> currentSalaries) {
    // TODO - you fill in here.

    Collections.sort(currentSalaries);
    double sum=0;
    int n = currentSalaries.size();
    int i;
    for(i=0; i<n && sum+(n-i)*currentSalaries.get(i)<targetPayroll; i++)
      sum += currentSalaries.get(i);

    return i<n ? (targetPayroll-sum)/(n-i) : -1.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
