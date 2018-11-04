package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class DefectiveJugs {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Jug {
    public int low, high;

    public Jug() {}

    public Jug(int low, int high) {
      this.low = low;
      this.high = high;
    }
  }

  @EpiTest(testDataFile = "defective_jugs.tsv")

  public static boolean checkFeasible(List<Jug> jugs, int L, int H) {
    // TODO - you fill in here.
    checked = new Boolean[L+1][H+1];
    return search(jugs, L, H);
  }

  private static Boolean checked[][];
  private static boolean search(List<Jug> jugs, int L, int H){
    if(L<0 || L>H) return false;
    if(checked[L][H] != null) return checked[L][H];

    boolean found = false;
    for(int i=0; i<jugs.size()&&!found; i++){
      Jug j = jugs.get(i);
      if(L<=j.low && j.high<=H)
        found = true;
      else
        found = found || search(jugs, L-j.low, H-j.high);
    }
    return checked[L][H] = found;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DefectiveJugs.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
