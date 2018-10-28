package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;
import java.util.stream.Collectors;

public class GrayCode {

  public static List<Integer> grayCode(int numBits) {
    // TODO - you fill in here.
    if(numBits ==0) return new ArrayList<>(Arrays.asList(0));

    List<Integer> prev = grayCode(numBits-1);
    int n = prev.size();
    for(int i=0; i<n; i++)
      prev.add(prev.get(n-1-i) | (1<<numBits));
    return prev;
  }

  public static List<Integer> grayCode1(int numBits) {
    // TODO - you fill in here.
    res = new ArrayList<>();
    set = new HashSet<>();
    set.add(0);
    res.add(0);
    search(numBits, (1<<numBits) - 1);
    return res;
  }

  private static List<Integer> res;
  private static HashSet<Integer> set;
  private static boolean search(int nbits, int remain){
    if(remain == 0) return true;

    int last = res.get(res.size()-1);
    for(int i=0; i<nbits; i++){
      int next = last ^ (1<<i);
      if(!set.contains(next)){
        set.add(next);
        res.add(next);
        if(search(nbits, remain-1)) return true;
        res.remove(res.size()-1);
        set.remove(next);
      }
    }
    return false;
  }
  private static boolean differsByOneBit(int x, int y) {
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }

  @EpiTest(testDataFile = "gray_code.tsv")
  public static void grayCodeWrapper(TimedExecutor executor, int numBits)
      throws Exception {
    List<Integer> result = executor.run(() -> grayCode(numBits));

    int expectedSize = (1 << numBits);
    if (result.size() != expectedSize) {
      throw new TestFailure("Length mismatch: expected " +
                            String.valueOf(expectedSize) + ", got " +
                            String.valueOf(result.size()));
    }
    for (int i = 1; i < result.size(); i++)
      if (!differsByOneBit(result.get(i - 1), result.get(i))) {
        if (result.get(i - 1).equals(result.get(i))) {
          throw new TestFailure("Two adjacent entries are equal");
        } else {
          throw new TestFailure(
              "Two adjacent entries differ by more than 1 bit");
        }
      }

    Set<Integer> uniq = new HashSet<>(result);
    if (uniq.size() != result.size()) {
      throw new TestFailure("Not all entries are distinct: found " +
                            String.valueOf(result.size() - uniq.size()) +
                            " duplicates");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GrayCode.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
