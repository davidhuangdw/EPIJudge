package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortList {
  @EpiTest(testDataFile = "sort_list.tsv")

  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    // TODO - you fill in here.
    if(L==null || L.next==null) return L;

    ListNode<Integer> l=L,r=L.next;
    for(; r!=null && r.next != null; l=l.next,r=r.next.next);
    r = l.next;
    l.next = null;

    return merge(stableSortList(L),stableSortList(r));
  }

  private static ListNode<Integer> merge(ListNode<Integer> l, ListNode<Integer> r){
    if(l==null) return r;
    if(r==null) return l;

    ListNode<Integer> hd = new ListNode<Integer>(0,null), tail=hd;
    while(l != null && r!=null){
      if(l.data <= r.data){
        tail.next = l;
        l = l.next;
      } else{
        tail.next = r;
        r = r.next;
      }
      tail = tail.next;
    }
    tail.next = l==null ? r : l;
    return hd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
