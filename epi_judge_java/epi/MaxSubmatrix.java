package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class MaxSubmatrix {

  @EpiTest(testDataFile = "max_submatrix.tsv")

  public static int maxRectangleSubmatrix(List<List<Boolean>> A) {
    // TODO - you fill in here.

    int m=A.get(0).size();
    int res = 0;
    int height[] = new int [m];
    for(int i=0; i<A.size(); i++) {
      for (int j = 0; j < m; j++) {
        if(A.get(i).get(j)) height[j] += 1;
        else height[j] = 0;
      }

      Deque<Integer> left = new LinkedList<>();
      for(int j=0; j<m; j++){
        while(!left.isEmpty() && height[left.peek()]>=height[j]){
          int md = left.pop();
          if(height[md] == height[j]) continue;
          res = Math.max(res, height[md]*(j-1-(left.isEmpty()? -1:left.peek())));
        }
        left.push(j);
      }
      while(!left.isEmpty()){
        int md = left.pop();
        res = Math.max(res, height[md]*(m-1-(left.isEmpty()? -1:left.peek())));
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSubmatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
