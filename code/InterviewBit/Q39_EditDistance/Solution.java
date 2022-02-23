package Q39_EditDistance;

public class Solution {
  public int minDistance(String A, String B) {
    int n = A.length();
    int m = B.length();
    if (n == 0) {
      return m;
    }
    if (m == 0) {
      return n;
    }

    Integer[][] dp = new Integer[n + 1][m + 1];
    return solve(A, n, B, m, dp); // - 1;
  }

  private int solve(String a, int aLen, String b, int bLen, Integer[][] dp) {
    if (aLen < 0 || bLen < 0) {
      return 0;
    }

    if (dp[aLen][bLen] == null) {
      if (aLen == 0) {
        dp[aLen][bLen] = bLen;
      } else if (bLen == 0) {
        dp[aLen][bLen] = aLen;
      } else {
        dp[aLen][bLen] =
            Math.min(1 + solve(a, aLen, b, bLen - 1, dp), 1 + solve(a, aLen - 1, b, bLen, dp));

        int temp = solve(a, aLen - 1, b, bLen - 1, dp);
        if (a.charAt(aLen - 1) != b.charAt(bLen - 1)) {
          temp++;
        }

        dp[aLen][bLen] = Math.min(dp[aLen][bLen], temp);
      }
    }
    // System.out.println("dp[" + aLen + "][" + bLen + "]: " + dp[aLen][bLen]);
    return dp[aLen][bLen];
  }

  public static void main(String[] args) {
    String A = "abad";
    String B = "abac";
    int expected = 1;
    runTestCase(A, B, expected);

    A = "Anshuman";
    B = "Antihuman";
    expected = 2;
    runTestCase(A, B, expected);
  }

  private static void runTestCase(String a, String b, int expected) {
    Solution solution = new Solution();
    System.out.println("a: " + a);
    System.out.println("b: " + b);
    int result = solution.minDistance(a, b);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
