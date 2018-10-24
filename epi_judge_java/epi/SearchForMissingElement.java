package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    // TODO - you fill in here.

    int xy=0, x=0, y=0;
    for(int i=0; i<A.size(); i++)
      xy = xy ^ i ^ A.get(i);

    int bit = xy & ~(xy-1);
    for(int i=0; i<A.size(); i++){
      if((i & bit) != 0)
        x ^= i;
//      else
//        y ^= i;
      if((A.get(i) & bit) != 0)
        x ^= A.get(i);
//      else
//        y ^= A.get(i);
    }

    for(int i=0; i<A.size(); i++)
      if(A.get(i) == x)
        return new DuplicateAndMissing(x, xy ^ x);


    return new DuplicateAndMissing(xy ^ x, x);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
