package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.LinkedList;
import java.util.Deque;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.

    String [] words = expression.split(",");
    Deque<Integer> st = new LinkedList<Integer>();

    for(String word:words){
      if(word.length() > 1 || !"+-*/".contains(word)){
        st.push(Integer.parseInt(word));
      } else {
        int v1 = st.pop();
        int v2 = st.pop();
        if(word.equals("+")){
          st.push(v1+v2);
        } else if(word.equals("-")){
          st.push(v2-v1);
        } else if(word.equals("*")){
          st.push(v1*v2);
        } else
          st.push(v2/v1);
      }
    }
    return st.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
