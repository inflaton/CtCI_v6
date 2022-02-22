package Q37_RepeatingSubSequence;

public class Solution {
  public int anytwo(String A) {
    return findLongestRepeatingSubSeq(A) >= 2 ? 1 : 0;
  }

  int findLongestRepeatingSubSeqNonRecursive(String A) {
    int n = A.length();
    int dp[][] = new int[n + 1][n + 1];
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

  int findLongestRepeatingSubSeq(String A) {
    int n = A.length();
    int dp[][] = new int[n + 1][n + 1];
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
  }
}
