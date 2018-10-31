package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MatrixEnclosedRegions {

  public static void fillSurroundedRegions(List<List<Character>> board) {
    // TODO - you fill in here.
    for(int i=0; i<board.size(); i++)
      for(int j=0; j<board.get(i).size(); j++)
        if(board.get(i).get(j) == 'W' &&(i==0 || i==board.size()-1 || j==0 || j==board.get(i).size()-1))
          mark(board, i, j);

    for(int i=0; i<board.size(); i++)
      for(int j=0; j<board.get(i).size(); j++)
        board.get(i).set(j, board.get(i).get(j)=='X' ? 'W':'B');
    return;
  }

  private static int [][]SHIFT = {{0,1}, {0,-1}, {1,0}, {-1,0}};
  private static void mark(List<List<Character>> board, int i, int j){
    board.get(i).set(j, 'X');

    for(int s[]:SHIFT){
      int ni = i+s[0], nj=j+s[1];
      if(0<=ni && ni<board.size() && 0<=nj && nj<board.get(ni).size() && board.get(ni).get(nj)=='W')
        mark(board, ni, nj);
    }
  }

  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
