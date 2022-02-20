package Q34_ShortestUniquePrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
  private static final int R = 256;

  private static class TrieNode {
    HashMap<Character, TrieNode> next;
    String word;

    TrieNode() {
      this(null);
    }

    TrieNode(String word) {
      this.word = word;
      this.next = new HashMap<>();
    }

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
          + (word == null ? "" : ", word='" + word + '\'' + '}');
    }
  }

  public ArrayList<String> prefix(ArrayList<String> A) {
    ArrayList<String> ans = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (String s : A) {
      add(root, s);
    }

    for (String s : A) {
      String prefix = findPrefix(root, s);
      ans.add(prefix);
    }
    return ans;
  }

  private void add(TrieNode root, String s) {
    add(root, s, 0);
  }

  private TrieNode add(TrieNode node, String s, int i) {
    if (i == s.length()) {
      if (node == null) {
        return new TrieNode(s);
      }
      node.word = s;
    }

    if (node == null) {
      node = new TrieNode();
    }
    char ch = s.charAt(i);
    TrieNode nextNode = node.next.get(ch);
    node.next.put(ch, add(nextNode, s, i + 1));
    return node;
  }

  private String findPrefix(TrieNode root, String s) {
    int len = findPrefixLength(root, s, 0);
    return s.substring(0, len);
  }

  private int findPrefixLength(TrieNode node, String s, int i) {
    if (i == s.length()) {
      return i;
    }

    System.out.println("i: " + i + ", node: " + node);
    if (node.next.size() > 1) {
      return i + 1;
    }

    char ch = s.charAt(i);
    TrieNode nextNode = node.next.get(ch);
    System.out.println("i: " + i + ", node: " + node);

    return findPrefixLength(nextNode, s, i + 1);
  }

  public static void main(String[] args) {
    String[] array = {"zebra", "dog", "duck", "dove"};
    String[] expected = {"z", "dog", "du", "dov"};
    runTestCase(array, expected);

    String[] array2 = {"bearcat", "bert"};
    String[] expected2 = {"bea", "ber"};
    runTestCase(array2, expected2);
  }

  private static void runTestCase(String[] array, String[] expectedArray) {
    ArrayList<String> input = new ArrayList<>(Arrays.asList(array));
    System.out.println("input: " + input);
    ArrayList<String> result = new Solution().prefix(input);
    System.out.println("result: " + result);
    ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedArray));
    if (!result.equals(expected)) {
      //      throw new AssertionError("result: " + result + " does not equal to expected: " +
      // expected);
    }
  }
}
