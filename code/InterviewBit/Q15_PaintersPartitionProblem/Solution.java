package Q15_PaintersPartitionProblem;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
  public int paint(int A, int B, ArrayList<Integer> C) {
    int n = C.size();
    if (A < 1) {
      return -1;
    }
    if (A > n) {
      A = n;
    }

    long r = 0;
    for (int units : C) {
      r += units;
    }

    long l = 0;
    long ans = r;
    while (l <= r) {
      long mid = (l + r) / 2;
      if (canPaint(A, C, mid)) {
        ans = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return (int) (ans * B % 10000003);
  }

  private boolean canPaint(int a, ArrayList<Integer> c, long threshold) {
    int n = c.size();
    int k = 1;
    long units = 0;

    for (int i = 0; i < n; i++) {
      units += c.get(i);
      if (units > threshold) {
        k += 1;
        if (k > a) {
          return false;
        }
        units = 0;
        i -= 1;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int a = 2;
    int b = 5;
    ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1, 10));
    System.out.println("c: " + Arrays.toString(c.toArray()));
    System.out.println("a: " + a + ", b: " + b + ", output: " + s.paint(a, b, c));

    a = 10;
    b = 1;
    c = new ArrayList<>(Arrays.asList(1, 8, 11, 3));
    System.out.println("c: " + Arrays.toString(c.toArray()));
    System.out.println("a: " + a + ", b: " + b + ", output: " + s.paint(a, b, c));

    a = 1;
    b = 1000000;
    c = new ArrayList<>(Arrays.asList(1000000, 1000000));
    System.out.println("c: " + Arrays.toString(c.toArray()));
    System.out.println("a: " + a + ", b: " + b + ", output: " + s.paint(a, b, c));
  }
}
