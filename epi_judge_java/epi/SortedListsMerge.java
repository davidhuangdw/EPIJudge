package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.

    if(L1 == null) return L2;
    if(L2 == null) return L1;

    ListNode<Integer> hd = new ListNode<Integer>(0, null);
    ListNode<Integer> last = hd;


    while(L1 != null && L2 != null){
      if(L1.data <= L2.data) {
        last.next = L1;
        L1=L1.next;
      } else{
        last.next = L2;
        L2=L2.next;
      }
      last = last.next;
    }
    last.next = L1!=null ? L1 : L2;

    return hd.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
