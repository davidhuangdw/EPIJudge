package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    // TODO - you fill in here.
    if(L==null) return L;

    ListNode<Integer> hd = new ListNode<Integer>(0, L);
    ListNode<Integer> l=hd, r=hd;
    int n=0;
    while(r.next!=null) {
      n++;
      r = r.next;
    }
    if(k >= n) k %= n;
    if(k == 0) return L;
    r = hd;
    for(int i=0; i<k; i++)
      r = r.next;
    while(r.next != null){
      l = l.next;
      r = r.next;
    }

    r.next = hd.next;
    hd.next = l.next;
    l.next = null;

    return hd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
