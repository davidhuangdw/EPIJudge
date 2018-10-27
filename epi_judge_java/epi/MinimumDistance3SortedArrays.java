package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int
  findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.
    int n = sortedArrays.size();
    int min = Integer.MAX_VALUE;
    NavigableSet<ArrayData> set = new TreeSet<>();
    List<Integer> inds = new ArrayList<Integer>(Collections.nCopies(n, 0)), arr;

    for(int i=0; i<sortedArrays.size(); i++)
      set.add(new ArrayData(i, sortedArrays.get(i).get(0)));

    while(set.size() >= n){
      min = Math.min(min, set.last().val-set.first().val);
      ArrayData fir = set.pollFirst();
      int i = inds.get(fir.idx);
      arr = sortedArrays.get(fir.idx);
      if(i+1 < arr.size()){
        inds.set(fir.idx, i+1);
        fir.val = arr.get(i+1);
        set.add(fir);
      }
    }
    return min;
  }

  public static int
  findMinDistanceSortedArrays1(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.

    List<List<Integer>> arrs = sortedArrays;
    int k = arrs.size();
    int res = Integer.MAX_VALUE;
    for(int st=0; st<k; st++){
      List<Integer> inds = new ArrayList<Integer>(Collections.nCopies(k, 0)), list;

      int rv = Integer.MIN_VALUE;
      boolean done = false;
      for(int v:arrs.get(st)){
        for(int i=1; i<k; i++){
          int j = (i+st)%k;
          list = arrs.get(j);
          int id = inds.get(j);
          while(id < list.size() && list.get(id) < v) id++;
          if(id < list.size()) {
            rv = Math.max(rv, list.get(id));
            inds.set(j, id);
          }else done=true;
        }
        if(done) break;
        if(rv-v < res) res = rv -v;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
