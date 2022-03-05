package Q54_NinjiaAttempt2;

import java.util.ArrayList;
import java.util.Arrays;

public class IncreasingSubArrays {
  public int cntInc(ArrayList<Integer> A) {
    int ans = 0;
    int size = A.size();
    if (size > 0) {
      ans = size;
      int last = A.get(0);
      int count = 1;
      for (int i = 1; i < size; i++) {
        int val = A.get(i);
        if (val > last) {
          count++;
        } else {
          ans += count * (count - 1) / 2;
          count = 1;
        }
        last = val;
      }
      ans += count * (count - 1) / 2;
    }
    return ans;
  }

  public static void main(String[] args) {
    Integer[] a = {1, 1, 1, 1, 1};
    Integer expected = 5;
    runTestCase(a, expected);

    a = new Integer[] {1, 2, 3, 4, -2, -1};
    expected = 13;
    runTestCase(a, expected);

    a = new Integer[] {4, 5, 1, 2};
    expected = 6;
    runTestCase(a, expected);
  }

  private static void runTestCase(Integer[] a, Integer expected) {
    IncreasingSubArrays solution = new IncreasingSubArrays();
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);

    int result = solution.cntInc(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
