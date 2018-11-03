package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.PriorityQueue;
import epi.KthLargestInArray;

public class KthLargestElementInLongArray {

  public static int findKthLargestUnknownLength(Iterator<Integer> stream,
                                                int k) {
    // TODO - you fill in here.

    int len = k*4;
    List<Integer> cur = new ArrayList<>();
    for(Iterator<Integer> it=stream; it.hasNext();){
      int v = it.next();
      if(cur.size() == len){
        int vk = KthLargestInArray.findKthLargest(k, cur);

        int j=0;
        for(int i=0; i<cur.size(); i++)
          if(cur.get(i)>vk) cur.set(j++,cur.get(i));
        while(j<k) cur.set(j++, vk);
        cur.subList(k,cur.size()).clear();
      }
      cur.add(v);
    }

    return KthLargestInArray.findKthLargest(k, cur);
  }

  public static int findKthLargestUnknownLength1(Iterator<Integer> stream,
                                                int k) {
    // TODO - you fill in here.

    PriorityQueue<Integer> que = new PriorityQueue<>();
    for(Iterator<Integer> it=stream; it.hasNext();){
      int v = it.next();
      que.add(v);
      if(que.size() > k)
        que.poll();
    }
    return que.peek();
  }
  @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
  public static int findKthLargestUnknownLengthWrapper(List<Integer> stream,
                                                       int k) {
    return findKthLargestUnknownLength(stream.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
