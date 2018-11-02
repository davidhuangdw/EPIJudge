package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ZipList {
  @EpiTest(testDataFile = "zip_list.tsv")

  public static ListNode<Integer> zippingLinkedList(ListNode<Integer> L) {
    // TODO - you fill in here.
    if(L== null || L.next == null) return L;

    ListNode<Integer> s, f, l, r, hd=new ListNode<>(0,null), tail=hd, tmp;
    for(s = f = L; f.next != null && f.next.next !=null; s=s.next, f=f.next.next);
    l = L; r=s.next; s.next = null;
    r = reverse(r);

    while(l != null || r!=null){
      if(l != null){
        tail = tail.next = l;
        l = l.next;
      }
      if(r != null) {
        tail = tail.next = r;
        r = r.next;
      }
    }
    tail.next = null;

    return hd.next;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> L){
    ListNode<Integer> cur = L.next, next, hd=L;
    L.next = null;
    while(cur != null){
      next = cur.next;
      cur.next=hd;
      hd = cur;
      cur = next;
    }
    return hd;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ZipList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
