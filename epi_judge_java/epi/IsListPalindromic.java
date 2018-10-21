package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.

    if(L == null || L.next == null) return true;

    int n = 0;
    ListNode<Integer> p = L, md, right, pr;
    while(p != null){
      n++;
      p = p.next;
    }

    md = L;
    for(int i=0; i<(n-1)/2; i++)
      md = md.next;
    right = reverse(md.next);

    boolean res = true;
    p = L; pr=right;
    for(int i=0; i<n/2 && res; i++, p=p.next, pr=pr.next)
      if(!p.data.equals(pr.data))
        res = false;

    md.next = reverse(right);

    return res;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> L){
    ListNode<Integer> p = L, l=new ListNode<Integer>(0, null), tmp;
    while(p != null){
      tmp = p.next;
      p.next = l.next;
      l.next = p;
      p = tmp;
    }

    return l.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
