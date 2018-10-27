package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class DescendantAndAncestorInBst {

  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                       BstNode<Integer> possibleAncOrDesc1,
                                       BstNode<Integer> middle) {
    // TODO - you fill in here.
    // todo: debug

    if(possibleAncOrDesc0 == middle || possibleAncOrDesc1 == middle) return false;
    BstNode<Integer> desc = null, i, j, anc;

    for(i=j=middle; desc==null && (i!=null || j!=null);){
      if(i==possibleAncOrDesc0)
        desc = possibleAncOrDesc0;
      else if(j==possibleAncOrDesc1)
        desc = possibleAncOrDesc1;
      else{
        if(i != null)
          i = possibleAncOrDesc0.data < i.data ? i.left : i.right;
        if(j != null)
          j = possibleAncOrDesc1.data < j.data ? j.left : j.right;
      }
    }
    if(desc == null) return false;
    anc = desc==possibleAncOrDesc0 ? possibleAncOrDesc1 : possibleAncOrDesc0;
    while(anc != null && anc != middle)
      anc = middle.data < anc.data ? anc.left : anc.right;

    return anc == middle;
  }
  @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode =
        BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(()
                            -> pairIncludesAncestorAndDescendantOfM(
                                candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
