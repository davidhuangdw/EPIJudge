package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    // TODO - you fill in here.
    if(tree == null) return null;
//    BstNode<Integer> l;
//    if(tree.data <= k)
//      return findFirstGreaterThanK(tree.right, k);
//    else {
//       l = findFirstGreaterThanK(tree.left, k);
//       return l!=null ? l:tree;
//    }
    BstNode<Integer> cur=tree, res=null;
    while(cur != null)
      if(cur.data <=k)
        cur = cur.right;
      else{
        res = cur;
        cur = cur.left;
      }
    return res;
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
