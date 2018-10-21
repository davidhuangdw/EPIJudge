package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    // TODO - you fill in here.
    ListNode<Integer> p1=L1, p2=L2, sum= new ListNode<Integer>(0, null), tail;
    int ca=0;
    tail = sum;
    while(p1 != null || p2 != null){
      if(p1 != null) {
        ca += p1.data;
        p1 = p1.next;
      }
      if(p2 != null) {
        ca += p2.data;
        p2 = p2.next;
      }
      tail.next = new ListNode<Integer>(ca%10, tail.next);
      tail = tail.next;
      ca = ca/10;
    }
//    while(ca >0){
//      tail.next = new ListNode<Integer>(ca%10, tail.next);
//      tail = tail.next;
//      ca/=10;
//    }
    if(ca > 0)
      tail.next = new ListNode<Integer>(ca, tail.next);
    return sum.next;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> L1){
    ListNode<Integer> hd = new ListNode<Integer>(0, null), tmp;
    while(L1 != null){
      tmp = L1.next;
      L1.next = hd.next;
      hd.next = L1;
      L1 = tmp;
    }

    return hd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
