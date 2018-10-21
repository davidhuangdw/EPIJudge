package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.
    reverse(input, 0, input.length-1);

    for(int i=0,j; i<input.length; i=j+1){
      j = i;
      while(j<input.length && input[j] != ' ') j++; //bug: forget j<input.length
      reverse(input, i, j-1);
    }

    return;
  }

  public static void reverse(char[] a, int i, int j){
    while(i<j){
      char tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
      i++; j--; //bug: forget update i and j
    }
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
