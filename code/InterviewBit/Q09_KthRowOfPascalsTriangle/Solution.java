package Q09_KthRowOfPascalsTriangle;

import java.util.ArrayList;

public class Solution {
  public ArrayList<Integer> getRow(int A) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int row = 0; row <= A; row++) {
      int n = result.size() - 1;
      while (n >= 0) {
        int newValue = result.get(n);
        if (n >= 1) {
          newValue += result.get(n - 1);
        }
        result.set(n, newValue);
        n--;
      }
      result.add(1);
    }
    return result;
  }

  public ArrayList<Integer> getRowRecursive(int A) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i <= A; i++) {
      int value = calcPascalTriagle(A, i);
      result.add(value);
    }
    return result;
  }

  private int calcPascalTriagle(int row, int col) {
    if (row == 0 && col == 0) {
      return 1;
    }
    int result = 0;
    if (row > 0) {
      result += calcPascalTriagle(row - 1, col);
    }
    if (col > 0) {
      result += calcPascalTriagle(row - 1, col - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    final int n = 25;

    for (int i = 0; i <= n; i++) {
      ArrayList<Integer> result = s.getRow(i);
      for (int j : result) {
        System.out.print(j);
        System.out.print("\t");
      }
      System.out.println();
    }
  }
}
