package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {
  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    // TODO - you fill in here.
    index = 0;
    return build(preorderSequence, Integer.MAX_VALUE);
  }

  private static int index;
  private static BstNode<Integer> build(List<Integer> seq, int lessThan){
    if(index < seq.size() && seq.get(index)<lessThan){
      int v = seq.get(index++);
      return new BstNode<Integer>(v, build(seq, v), build(seq, lessThan));
    }
    else return null;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
