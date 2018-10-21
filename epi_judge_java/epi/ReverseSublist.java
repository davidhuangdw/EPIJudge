package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    // TODO - you fill in here.
    if(start == 0) return L;

    ListNode<Integer> hd = new ListNode<>(0, L);
    ListNode<Integer> pre = hd;

    for(int i=0; i<start-1; i++)
      pre = pre.next;

//    ListNode<Integer> next = pre.next;
//    ListNode<Integer> fir = next;
//    pre.next = null;
//    for(int i=start; i<=finish; i++){
//      ListNode<Integer> cur = next;
//      next = next.next;
//      cur.next = pre.next;
//      pre.next = cur;
//    }
//    fir.next = next;

    ListNode<Integer> last = pre.next;
    for(int i=start+1; i<=finish; i++){
      ListNode<Integer> cur = last.next;
      last.next = cur.next;
      cur.next = pre.next;
      pre.next = cur;
    }

    return hd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
