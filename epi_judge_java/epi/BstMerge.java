package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class BstMerge {
  @EpiTest(testDataFile = "bst_merge.tsv")

  public static BstNode<Integer> mergeTwoBsts(BstNode<Integer> A,
                                              BstNode<Integer> B) {
    // TODO - you fill in here.
    A = BstToSortedList.bstToDoublyLinkedList(A);
    B = BstToSortedList.bstToDoublyLinkedList(B);
    hd=new BstNode(0,null,null);
    BstNode ha=A, hb=B, tail=hd;
    while(A != null && B !=null){ // bug: forget to merge_sort(but directly append)
      if(A.data < B.data){
        tail.right = A;
        A=A.right;
      }else{
        tail.right = B;
        B = B.right;
      }
      tail.right.left = tail;
      tail = tail.right;
    }
    tail.right = A==null? B : A;
    if(tail.right != null) tail.right.left=tail;

    hd = hd.right;
    int len;
    for(len=0, tail=hd; tail!=null; tail=tail.right,len++);

    return listToBst(len);
  }

  private static BstNode<Integer> listToBst(int len){
    if(len == 0) return null;
    BstNode<Integer> l, root;
    l = listToBst(len/2);
    root = hd;
    root.left = l;
    hd = hd.right;
    root.right = listToBst(len-1-len/2);
    return root;
  }

  private static BstNode<Integer> hd;

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
