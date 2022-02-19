package Q28_NQueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
  public ArrayList<ArrayList<String>> solveNQueens(int a) {
    Integer[] path = new Integer[a];
    ArrayList<ArrayList<String>> ans = new ArrayList<>();
    findSolutions(a, 0, path, ans);
    return ans;
  }

  private void findSolutions(int a, int row, Integer[] path, ArrayList<ArrayList<String>> ans) {
    if (row == a) {
      ArrayList<String> solution = new ArrayList<>();
      for (int i = 0; i < a; i++) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < a; j++) {
          if (j == path[i]) {
            sb.append("Q");
          } else {
            sb.append(".");
          }
        }
        solution.add(sb.toString());
      }

      ans.add(solution);
      return;
    }

    for (int col = 0; col < a; col++) {
      boolean allowed = true;
      for (int i = 0; i < row; i++) {
        if (col == path[i] || Math.abs(i - row) == Math.abs(path[i] - col)) {
          allowed = false;
          break;
        }
      }
      if (allowed) {
        path[row] = col;
        findSolutions(a, row + 1, path, ans);
      }
    }
  }

  public static void main(String[] args) {
    runTestCase(2);
    runTestCase(3);
    runTestCase(4);
  }

  private static void runTestCase(int a) {
    Solution solution = new Solution();
    System.out.println("input: " + a);
    ArrayList<ArrayList<String>> ans = solution.solveNQueens(a);
    System.out.println("ans: " + ans.size());
    for (ArrayList<String> p : ans) {
      System.out.println(Arrays.toString(p.toArray()));
    }
  }
}
