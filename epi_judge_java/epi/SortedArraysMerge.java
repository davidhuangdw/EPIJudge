package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  static class ArrayEntry{
    public int i;
    public List<Integer> arr;
    public ArrayEntry(List<Integer>a, int i){
      this.arr = a;
      this.i = i;
    }

    public boolean hasMore(){
      return i<arr.size();
    }
    public int value(){
      return arr.get(i);
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.

    List<Integer> res = new ArrayList<Integer>();
    PriorityQueue<ArrayEntry> que = new PriorityQueue<ArrayEntry>(sortedArrays.size() + 1,
        new Comparator<ArrayEntry>() {
          @Override
          public int compare(ArrayEntry o1, ArrayEntry o2) {
            if(o1.value() == o2.value()) return 0;
            return o1.value() < o2.value() ? -1: 1;
          }
        }
    );
    for(int i=0; i<sortedArrays.size(); i++){
      List<Integer> x = sortedArrays.get(i);
      if(x.size() == 0) continue;
      que.add(new ArrayEntry(x, 0));
    }

    while(!que.isEmpty()){
      ArrayEntry cur = que.poll();
      res.add(cur.value());
      cur.i++;
      if(cur.hasMore())
        que.add(cur);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
