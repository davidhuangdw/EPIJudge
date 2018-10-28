package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SudokuSolve {
  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.
    // todo: debug

    for(int i=0; i<9; i++)
      for(int j=0; j<9; j++)
        for(int v=1; v<=9; v++)
          rows[i][v] = cols[j][v] = blocks[i/3][j/3][v] = false;

    for(int i=0; i<9; i++)
      for(int j=0; j<9; j++) {
        int v = partialAssignment.get(i).get(j);
        if (v != 0) {
          rows[i][v] = cols[j][v] = blocks[i/3][j/3][v] = true;
        }else
          remains.push(Arrays.asList(i, j));
      }

    return search(partialAssignment);
  }

  private static boolean [][]rows = new boolean [9][10];
  private static boolean [][]cols = new boolean [9][10];
  private static boolean [][][]blocks = new boolean [3][3][10];
  private static Deque<List<Integer>> remains = new LinkedList<>();

  private static boolean search(List<List<Integer>> partialAssignment){
    if(remains.size() == 0) return true;
    List<Integer> ind = remains.pop();
    int i=ind.get(0), j=ind.get(1);
    for(int v=1; v<=9; v++)
      if(!rows[i][v] && !rows[j][v] && !blocks[i/3][j/3][v]){
        rows[i][v] = cols[j][v] = blocks[i/3][j/3][v] = true;
        partialAssignment.get(i).set(j, v);
        if(search(partialAssignment)) return true;
        partialAssignment.get(i).set(j, 0);
        rows[i][v] = cols[j][v] = blocks[i/3][j/3][v] = false;
      }
    remains.push(ind);
    return false;
  }

  @EpiTest(testDataFile = "sudoku_solve.tsv")
  public static void solveSudokuWrapper(TimedExecutor executor,
                                        List<List<Integer>> partialAssignment)
      throws Exception {
    List<List<Integer>> solved = new ArrayList<>();
    for (List<Integer> row : partialAssignment) {
      solved.add(new ArrayList<>(row));
    }

    executor.run(() -> solveSudoku(solved));

    if (partialAssignment.size() != solved.size()) {
      throw new TestFailure("Initial cell assignment has been changed");
    }

    for (int i = 0; i < partialAssignment.size(); i++) {
      List<Integer> br = partialAssignment.get(i);
      List<Integer> sr = solved.get(i);
      if (br.size() != sr.size()) {
        throw new TestFailure("Initial cell assignment has been changed");
      }
      for (int j = 0; j < br.size(); j++)
        if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
          throw new TestFailure("Initial cell assignment has been changed");
    }

    int blockSize = (int)Math.sqrt(solved.size());
    for (int i = 0; i < solved.size(); i++) {
      assertUniqueSeq(solved.get(i));
      assertUniqueSeq(gatherColumn(solved, i));
      assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
    }
  }

  private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
    Set<Integer> seen = new HashSet<>();
    for (Integer x : seq) {
      if (x == 0) {
        throw new TestFailure("Cell left uninitialized");
      }
      if (x < 0 || x > seq.size()) {
        throw new TestFailure("Cell value out of range");
      }
      if (seen.contains(x)) {
        throw new TestFailure("Duplicate value in section");
      }
      seen.add(x);
    }
  }

  private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
    List<Integer> result = new ArrayList<>();
    for (List<Integer> row : data) {
      result.add(row.get(i));
    }
    return result;
  }

  private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
                                                 int blockSize, int n) {
    List<Integer> result = new ArrayList<>();
    int blockX = n % blockSize;
    int blockY = n / blockSize;
    for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
      for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
        result.add(data.get(i).get(j));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SudokuSolve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
