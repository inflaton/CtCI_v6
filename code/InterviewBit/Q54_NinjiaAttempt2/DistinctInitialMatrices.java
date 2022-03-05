package Q54_NinjiaAttempt2;

import java.util.ArrayList;
import java.util.Arrays;

public class DistinctInitialMatrices {

  public int cntMatrix(ArrayList<Integer> A) {
    int ans = 0;
    int size = A.size();
    if (size > 0) {
      for (int i = 1; i <= size; i++) {
        if (size % i == 0) {
          int rows = i;
          int cols = size / rows;
          ans += cntMatrix(A, rows, cols);
        }
      }
    }
    return ans;
  }

  private int cntMatrix(ArrayList<Integer> a, int rows, int cols) {
    int repeated = 1;
    long total = 1;
    for (int i = 0; i < a.size(); i += rows) {
      long colTotal = factorial(rows);
      int last = a.get(i);
      for (int j = i + 1; j < i + rows; j++) {
        int current = a.get(j);
        if (current < last) {
          return 0;
        }
        if (current == last) {
          repeated++;
          if (j == i + rows - 1) {
            if (repeated > 1) {
              colTotal -= factorial(repeated) - 1;
            }
          }
        } else {
          if (repeated > 1) {
            colTotal -= factorial(repeated) - 1;
          }
          repeated = 1;
        }
        last = current;
      }
      total *= colTotal;
    }

    return (int) (total % 1000000007);
  }

  private long factorial(int n) {
    if (n <= 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  public static void main(String[] args) {
    Integer[] a = {1, 2};
    Integer expected = 3;
    runTestCase(a, expected);

    a = new Integer[] {1, 3, 2, 4};
    expected = 5;
    runTestCase(a, expected);

    a = new Integer[] {1, 2, 3, 4, 5, 6};
    expected = 765;
    runTestCase(a, expected);

    a = new Integer[] {1, 2, 3, 4, 5, 5};
    expected = 754;
    runTestCase(a, expected);

    a = new Integer[] {1, 1, 3, 2, 2, 4};
    expected = 26;
    runTestCase(a, expected);
  }

  private static void runTestCase(Integer[] a, Integer expected) {
    DistinctInitialMatrices solution = new DistinctInitialMatrices();
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);

    int result = solution.cntMatrix(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
