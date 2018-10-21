package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    // TODO - you fill in here.
    int res = 0, b = 26, off = 1;
    for(int i=0; i<col.length()-1; i++, b*=26)
      off += b;

    b = 1;
    for(int i=col.length()-1; i>=0; i--, b*=26)
      res += (col.charAt(i) - 'A')*b;
    return res+off;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
