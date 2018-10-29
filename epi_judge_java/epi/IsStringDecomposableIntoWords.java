package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

  public static List<String>
  decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
    // TODO - you fill in here.
    int n = domain.length();
    int [] last = new int [n+1];
    for(int i=1; i<=n; i++)
      last[i] = -1;
    last[0] = 0;

    for(int i=0; i<n; i++){
      if(last[i] < 0) continue;
      for(int j=i+1; j<=n;j++)
        if(last[j]<0 && dictionary.contains(domain.substring(i,j)))
          last[j] = i;
    }
    if(last[n] < 0) return Collections.emptyList();

    List<String> res = new ArrayList<>();
    for(int i=n; i>0; i=last[i])
      res.add(domain.substring(last[i],i));
    Collections.reverse(res);
    return res;
  }
  @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
  public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                         String domain,
                                                         Set<String> dictionary,
                                                         Boolean decomposable)
      throws Exception {
    List<String> result =
        executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

    if (!decomposable) {
      if (!result.isEmpty()) {
        throw new TestFailure("domain is not decomposable");
      }
      return;
    }

    if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
      throw new TestFailure("Result uses words not in dictionary");
    }

    if (!String.join("", result).equals(domain)) {
      throw new TestFailure("Result is not composed into domain");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
