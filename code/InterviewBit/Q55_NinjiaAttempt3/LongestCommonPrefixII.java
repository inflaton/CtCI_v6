package Q55_NinjiaAttempt3;

import Q34_ShortestUniquePrefix.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LongestCommonPrefixII {

  private static class TrieNode {
    HashMap<Character, TrieNode> next = new HashMap<>();
    String word;

    public boolean isWord() {
      return this.word != null;
    }

    @Override
    public String toString() {
      return "TrieNode{"
          + "next["
          + next.size()
          + "]="
          + next
          + (word == null ? "" : ", word='" + word + '\'')
          + '}';
    }
  }

  private TrieNode addKey(TrieNode node, String s, int i) {
    if (node == null) {
      node = new TrieNode();
    }
    if (i == s.length()) {
      node.word = s;
    } else {
      char ch = s.charAt(i);
      TrieNode nextNode = node.next.get(ch);
      node.next.put(ch, addKey(nextNode, s, i + 1));
    }
    return node;
  }

  public int longestPrefixOf(TrieNode root, List<String> stringList) {
    return longestPrefixOf(root, stringList, 0, -1);
  }

  private int longestPrefixOf(TrieNode x, List<String> stringList, int d, int length) {
    if (x == null) {
      return length;
    }
    if (x.isWord()) {
      length = d;
    }

    Character lastChar = null;
    for (String s : stringList) {
      if (d == s.length()) {
        return d;
      }
      char ch = s.charAt(d);
      if (lastChar != null && ch != lastChar) {
        return d;
      }
      lastChar = ch;
    }

    return longestPrefixOf(x.next.get(lastChar), stringList, d + 1, length);
  }

  public int LCPrefix(ArrayList<String> A, int B) {
    TrieNode root = null;
    for (String s : A) {
      root = addKey(root, s, 0);
    }

    // System.out.println(root);
    int n = A.size();
    Integer[] dp = new Integer[n - 1];
    long ans = 0;
    for (int i = 0; i < n; i++) {
      if (A.get(i).length() >= B) {
        ans++;
      }
      if (i < dp.length) {
        dp[i] = longestPrefixOf(root, A.subList(i, i + 2));
        if (dp[i] >= B) {
          ans++;
        }
      }
    }
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 2; j < A.size(); j++) {
        int length = dp[i];
        for (int k = i + 1; k < j; k++) {
          length = Math.min(length, dp[k]);
        }
        if (length >= B) {
          ans++;
        }
      }
    }
    return (int) (ans % 1000000007);
  }

  public int LCPrefixTimeLimitExceeded(ArrayList<String> A, int B) {
    int ans = 0;
    TrieNode root = null;
    for (String s : A) {
      root = addKey(root, s, 0);
    }
    // System.out.println(root);

    for (int i = 0; i < A.size(); i++) {
      for (int j = i; j < A.size(); j++) {
        if ((i == j && A.get(i).length() >= B)
            || (j > i && longestPrefixOf(root, A.subList(i, j + 1)) >= B)) {
          ans++;
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    String[] a = {"ab", "ac", "bc"};
    int k = 1;
    int expected = 4;
    runTestCase(a, k, expected);
  }

  private static void runTestCase(String[] a, int k, int expected) {
    LongestCommonPrefixII solution = new LongestCommonPrefixII();
    ArrayList<String> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);

    int result = solution.LCPrefix(A, k);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
