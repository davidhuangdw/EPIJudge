package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class KLargestInHeap {

  public static class HeapEntry{
    int i,v;
    public HeapEntry(int i, int v){
      this.i = i;
      this.v = v;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    // TODO - you fill in here.

    if(k == 0) return Collections.emptyList();

    List<Integer> res = new ArrayList<Integer>();
    PriorityQueue<HeapEntry> que = new PriorityQueue<HeapEntry>(k, new Comparator<HeapEntry>() {
      @Override
      public int compare(HeapEntry o1, HeapEntry o2) {
        return Integer.compare(o2.v, o1.v);
      }
    });
    que.add(new HeapEntry(0, A.get(0)));
    while(k-- > 0){
      HeapEntry e = que.poll();
      res.add(e.v);
      if(e.i*2+1 < A.size())
        que.add(new HeapEntry(e.i*2+1, A.get(e.i*2+1)));
      if(e.i*2+2 < A.size())
        que.add(new HeapEntry(e.i*2+2, A.get(e.i*2+2)));
    }
    return res;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
