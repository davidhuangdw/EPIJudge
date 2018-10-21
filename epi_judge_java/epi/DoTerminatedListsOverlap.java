package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.IsListCyclic;
import epi.DoListsOverlap;

public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.

    ListNode<Integer> root0 = IsListCyclic.hasCycle(l0);
    ListNode<Integer> root1 = IsListCyclic.hasCycle(l0);

    if(root0 == null && root1==null)
      return DoListsOverlap.overlappingLists(l0, l1);
    else if(root0 == null || root1 == null)
      return null;

    //both cycle
    int d0=0, d1=0;
    ListNode<Integer> p0, p1;
    p0=root0.next;
    while(p0 != root0 && p0 !=root1)
      p0 = p0.next;
    if(p0 != root1) return null;

    for(p0=l0; p0 != root0; p0=p0.next)
      d0++;
    for(p1=l1; p1 != root1; p1=p1.next)
      d1++;

    p0=l0; p1=l1;
    if(d0 < d1){
      for(int i=0; i<d1-d0; i++)
        p1 = p1.next;
    } else{
      for(int i=0; i<d0-d1; i++)
        p0 = p0.next;
    }

    while(p0 != p1 && p0 != root0 && p1 != root1){
      p0 = p0.next;
      p1 = p1.next;
    }

    return p0==p1? p0 : root0;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
