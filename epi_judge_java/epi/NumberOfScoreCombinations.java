package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    // TODO - you fill in here.

    int []comb = new int[finalScore+1];
    comb[0] = 1;
    for(int i=1; i<=finalScore; i++)
      comb[i] = 0;

    for(int sc: individualPlayScores){
      for(int i=0; i+sc<=finalScore; i++)
        if(comb[i] > 0)
          comb[i+sc] += comb[i];
    }

    return comb[finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
