package Q36_DistinctSubsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
  private static final int NIL = -1;

  public int numDistinct(String A, String B) {
    if (A.length() < B.length()) {
      return 0;
    }
    int[][] dp = new int[A.length() + 1][B.length() + 1];
    for (int[] d : dp) {
      Arrays.fill(d, NIL);
    }
    return solve(A, B, A.length(), B.length(), dp);
  }

  public static int solve(String s, String t, int sLen, int tLen, int[][] dp) {
    // we have traversed t and found all char so count 1 subsequence for that
    if (tLen == 0) {
      return 1;
    }
    // we have traversed whole s and still not found all t chars so 0
    if (sLen == 0) {
      return 0;
    }

    if (dp[sLen][tLen] == NIL) {
      if (s.charAt(sLen - 1) != t.charAt(tLen - 1)) {
        // if last char not equal then exclude that char from s and look again
        dp[sLen][tLen] = solve(s, t, sLen - 1, tLen, dp);
      } else {
        // if both char matches then add selection and not selection
        dp[sLen][tLen] = solve(s, t, sLen - 1, tLen - 1, dp) + solve(s, t, sLen - 1, tLen, dp);
      }
    }
    return dp[sLen][tLen];
  }

  // my own failed attempt
  static class Path {
    int[] indexPath;

    Path(int[] indexPath) {
      this.indexPath = indexPath;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Path path = (Path) o;
      return Arrays.equals(indexPath, path.indexPath);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(indexPath);
    }

    @Override
    public String toString() {
      return "Path{" + "indexPath=" + Arrays.toString(indexPath) + '}';
    }
  }

  public int numDistinctOwnAttempt(String A, String B) {
    int ans = 0;
    int i = 0;
    int j = 0;

    HashSet<Path> indexPathSet = new HashSet<>();
    int[] indexPath = new int[B.length()];

    A = new StringBuilder(A).reverse().toString();
    B = new StringBuilder(B).reverse().toString();

    while (i < A.length() && j < B.length()) {
      int lastIndex = -1;

      while (A.length() - i >= B.length() - j) {
        if (findDistinctSubsequence(A, i, B, j, indexPath)) {
          Path path = new Path(indexPath);
          if (!indexPathSet.contains(path)) {
            ans++;
            indexPathSet.add(path);
            // debug(A, path);
          }
          // System.out.println("ans: " + ans + ", i: " + i + ", j: " + j);
          if (lastIndex < 0) {
            lastIndex = indexPath[j];
          }
          i = indexPath[j] + 1;
        } else {
          break;
        }
      }

      indexPath[j] = lastIndex;
      i = lastIndex + 1;
      j++;
    }

    return ans;
  }

  private boolean findDistinctSubsequence(
      String a, int aFromIndex, String b, int bFromIndex, int[] indexPath) {
    if (a.length() - aFromIndex >= b.length() - bFromIndex && b.length() > bFromIndex) {
      char ch = b.charAt(bFromIndex);
      int index = a.indexOf(ch, aFromIndex);

      if (index >= 0) {
        indexPath[bFromIndex++] = index;
        if (b.length() == bFromIndex) {
          return true;
        }
        return findDistinctSubsequence(a, index + 1, b, bFromIndex, indexPath);
      }
    }
    return false;
  }

  private void debug(String A, Path path) {
    System.out.println("path: " + path);
    int[] indexPath = path.indexPath;
    int p = 0;
    for (int index : indexPath) {
      while (p++ < index) {
        System.out.print("_");
      }
      System.out.print(A.charAt(index));
    }
    while (p++ < A.length()) {
      System.out.print("_");
    }
    // aaaababbababbaabbaaababaaabbbaaabbb
    // ____b_b_abab_a_____________________
    System.out.println();
  }

  public static void main(String[] args) {
    String A = "abc";
    String B = "abc";
    int expected = 1;
    runTestCase(A, B, expected);

    A = "rabbbit";
    B = "rabbit";
    expected = 3;
    runTestCase(A, B, expected);

    A = "aaaababbababbaabbaaababaaabbbaaabbb";
    B = "bbababa";
    expected = 22113;
    runTestCase(A, B, expected);
  }

  private static void runTestCase(String a, String b, int expected) {
    Solution solution = new Solution();
    System.out.println("a: " + a + ", b: " + b);
    int result = solution.numDistinct(a, b);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
    result = solution.numDistinctOwnAttempt(a, b);
    System.out.println("my result: " + result);
  }
}
