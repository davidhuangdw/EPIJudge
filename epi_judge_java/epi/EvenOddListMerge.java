package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.
    if(L==null || L.next==null) return L;

//    ListNode<Integer> even=new ListNode<Integer>(0, null), p = L, ep=even;
//    while(p.next != null){
//      ep.next = p.next;
//      ep = ep.next;
//      p.next = p.next.next;
//      if(p.next != null)
//        p = p.next;
//    }
//    ep.next = null;
//    p.next = even.next;
    ListNode<Integer> even=new ListNode<Integer>(0, null);
    ListNode<Integer> odd=new ListNode<Integer>(0, null);
    List<ListNode<Integer>> tails= Arrays.asList(odd, even);

    int turn = 0;
    for(ListNode<Integer> p=L; p!=null; p=p.next){
      tails.get(turn).next = p;
      tails.set(turn, p);
      turn ^=1;
    }

    tails.get(0).next = even.next;
    tails.get(1).next = null;
    return odd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
