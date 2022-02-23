package Q38_LongestPalindromicSubsequence;

public class Solution {
  public int solve(String A) {
    int n = A.length();
    if (n < 2) {
      return n;
    }

    Integer[][] dp = new Integer[n][n];
    return findLongestPalindromicSubsequence(A, 0, n - 1, dp);
  }

  private int findLongestPalindromicSubsequence(String a, int start, int end, Integer[][] dp) {
    if (start < 0 || end < 0) {
      return 0;
    }
    if (dp[start][end] == null) {
      if (start > end) {
        dp[start][end] = 0;
      } else if (start == end) {
        dp[start][end] = 1;
      } else if (a.charAt(start) == a.charAt(end)) {
        dp[start][end] = 2 + findLongestPalindromicSubsequence(a, start + 1, end - 1, dp);
      } else {
        dp[start][end] =
            Math.max(
                findLongestPalindromicSubsequence(a, start, end - 1, dp),
                findLongestPalindromicSubsequence(a, start + 1, end, dp));
      }
    }

    return dp[start][end];
  }

  private int findLongestPalindromicSubsequenceNonRecursive(String A) {
    int n = A.length();
    int[][] dp = new int[n][n];
    for (int start = n - 1; start >= 0; start--) {
      for (int end = start; end < n; end++) {
        if (start == end) {
          dp[start][end] = 1;
        } else if (A.charAt(start) == A.charAt(end)) {
          dp[start][end] = 2 + dp[start + 1][end - 1];
        } else {
          dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
        }
      }
    }

    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    String A = "bebeeed";
    int expected = 4;
    runTestCase(A, expected);

    A = "aaxbwrecbeaiwer";
    expected = 5;
    runTestCase(A, expected);
  }

  private static void runTestCase(String a, int expected) {
    Solution solution = new Solution();
    System.out.println("a: " + a);
    int result = solution.solve(a);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }

    result = solution.findLongestPalindromicSubsequenceNonRecursive(a);
    System.out.println("non recursive result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
