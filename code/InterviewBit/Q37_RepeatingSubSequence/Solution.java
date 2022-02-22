package Q37_RepeatingSubSequence;

import java.util.Arrays;

public class Solution {
  public int anytwo(String A) {
    return findLongestRepeatingSubSeq(A) >= 2 ? 1 : 0;
  }

  private int findLongestRepeatingSubSeq(String A) {
    int n = A.length();
    int[][] dp = new int[n + 1][n + 1];
    for (int[] d : dp) {
      Arrays.fill(d, -1);
    }

    return findLongestRepeatingSubSeq(A, n, n, dp);
  }

  private int findLongestRepeatingSubSeq(String a, int m, int n, int[][] dp) {
    if (dp[m][n] < 0) {
      if (m == 0 || n == 0) {
        dp[m][n] = 0;
      } else if (a.charAt(m - 1) == a.charAt(n - 1) && m != n) {
        dp[m][n] = findLongestRepeatingSubSeq(a, m - 1, n - 1, dp) + 1;
      } else {
        dp[m][n] =
            Math.max(
                findLongestRepeatingSubSeq(a, m, n - 1, dp),
                findLongestRepeatingSubSeq(a, m - 1, n, dp));
      }
    }
    return dp[m][n];
  }

  private int findLongestRepeatingSubSeqNonRecursive(String A) {
    int n = A.length();
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (A.charAt(i - 1) == A.charAt(j - 1) && i != j) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][n];
  }

  public static void main(String[] args) {
    String A = "abab";
    int expected = 2;
    runTestCase(A, expected);

    A = "abba";
    expected = 1;
    runTestCase(A, expected);

    A = "aabebcdd";
    expected = 3;
    runTestCase(A, expected);
  }

  private static void runTestCase(String a, int expected) {
    Solution solution = new Solution();
    System.out.println("a: " + a);
    int result = solution.findLongestRepeatingSubSeq(a);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
    result = solution.findLongestRepeatingSubSeqNonRecursive(a);
    System.out.println("non recursive result: " + result);
  }
}
