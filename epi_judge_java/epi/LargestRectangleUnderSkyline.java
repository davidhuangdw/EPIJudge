package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class LargestRectangleUnderSkyline {
  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> heights) {
    // TODO - you fill in here.
    Deque<Integer> st = new LinkedList<>();
    int n=heights.size(), max=0;
    for(int i=0; i<=n; i++){
      while(!st.isEmpty() && (i==n || heights.get(st.peek()) >= heights.get(i))){
        int h = heights.get(st.pop());
        int w = i-(st.isEmpty()? -1: st.peek())-1;
        max = Math.max(max, w*h);
      }
      st.push(i);
    }
    return max;
  }

  public static int calculateLargestRectangle1(List<Integer> heights) {
    // TODO - you fill in here.
    Deque<Integer> st = new LinkedList<>();
    int n=heights.size();
    int left[]=new int[n];

    for(int i=0; i<n; i++){
      while(!st.isEmpty() && heights.get(st.peek())>=heights.get(i))
        st.pop();
      left[i] = st.isEmpty() ? -1:st.peek();
      st.push(i);
    }

    int max = 0;
    st.clear();
    for(int i=n-1; i>=0; i--){
      while(!st.isEmpty() && heights.get(st.peek())>=heights.get(i))
        st.pop();
      int right = st.isEmpty() ? n:st.peek();
      st.push(i);
      max = Math.max(max, (right-left[i]-1)*heights.get(i));
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
