package Q42_GasStation;

import java.util.*;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
    int n = A.size();
    ArrayList<Integer> extraGas = new ArrayList<>(n);
    int totalExtra = 0;
    for (int i = 0; i < n; i++) {
      // x is extra gas at each station
      int x = A.get(i) - B.get(i);
      extraGas.add(x);
      totalExtra += x;
    }

    if (totalExtra >= 0) {
      int i = 0;
      while (i < n) {
        totalExtra = 0;
        int j = i;
        for (j = i; j < i + n; j++) {
          int k = j % n;
          totalExtra += extraGas.get(k);
          if (totalExtra < 0) {
            i = j + 1;
            break;
          }
        }
        if (j == (i + n)) {
          return i;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    Integer[] A = {1, 2};
    Integer[] B = {2, 1};
    int expected = 1;
    runTestCase(A, B, expected);
  }

  private static void runTestCase(Integer[] a, Integer[] b, int expected) {
    Solution solution = new Solution();
    ArrayList<Integer> A = new ArrayList<>();
    for (Integer i : a) {
      A.add(i);
    }
    System.out.println("A: " + A);
    ArrayList<Integer> B = new ArrayList<>();
    for (Integer i : b) {
      B.add(i);
    }
    System.out.println("B: " + B);
    int result = solution.canCompleteCircuit(A, B);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
