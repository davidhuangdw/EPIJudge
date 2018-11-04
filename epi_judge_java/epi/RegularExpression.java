package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RegularExpression {
  @EpiTest(testDataFile = "regular_expression.tsv")

  public static boolean isMatch(String regex, String s) {
    // TODO - you fill in here.
    int n,m;
    n = regex.length(); m = s.length();
    record = new Boolean[n][m];

    initZero(regex);
    return find(regex, s, n-1, m-1);
  }


  private static boolean find(String regex, String s, int i, int j){
    if(i < 0) return true;
    if(j < 0) return i<=zeroPos;
    if(record[i][j] != null) return record[i][j];

    char cr = regex.charAt(i), cs=s.charAt(j);
    boolean found =false;

    if(cr == '$')
      found = (j==s.length()-1 && find(regex, s, i-1, j));
    else if(cr == '^')
      found = false;
    else if (cr == '*') {
      found = find(regex, s, i - 2, j);
      if (regex.charAt(i - 1) == '.' || regex.charAt(i - 1) == cs || i==regex.length()-1)
        found = found || find(regex, s, i, j - 1);
    } else {
      found = ((cr == '.' || cr == cs) && find(regex, s, i - 1, j - 1))
          || (i==regex.length()-1 && find(regex, s, i, j-1));
    }
    return record[i][j] = found;
  }

  private static int initZero(String regex){
    int i=0;
    if(regex.charAt(i) == '^') i++;
    while(i+1 < regex.length() && regex.charAt(i+1) == '*')
      i += 2;
    if(i<regex.length() && regex.charAt(i) == '$')
      i++;
    return zeroPos = i-1;
  }

  private static int zeroPos;
  private static Boolean record[][];

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RegularExpression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
