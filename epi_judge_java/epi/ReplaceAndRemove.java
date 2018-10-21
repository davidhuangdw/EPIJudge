package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    // TODO - you fill in here.
    int off = 0, n=0;
    for(int i=0; i<size; i++)
      if(s[i] != 'b'){
        s[n++] = s[i];
        if(s[i] == 'a') off++;
      }
    size = n+off;
    for(int i=n-1, j=size-1; i>=0; i--) {
      if(s[i] == 'a'){
        s[j--] = s[j--] = 'd';
//        s[i+off] = s[i+off-1] = 'd';
//        off--;
      }
      else
        s[j--] = s[i];
//        s[i+off] = s[i];
    }
    return size;
  }
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
