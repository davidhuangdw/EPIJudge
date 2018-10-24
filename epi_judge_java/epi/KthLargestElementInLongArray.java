package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KthLargestElementInLongArray {

  public static int findKthLargestUnknownLength(Iterator<Integer> stream,
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
