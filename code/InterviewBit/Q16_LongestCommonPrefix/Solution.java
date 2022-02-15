package Q16_LongestCommonPrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
  private Node root; // root of trie

  // R-way trie node
  public static class Node {
    private final HashMap<Character, Node> childNodes = new HashMap<>();
    private String word;

    @Override
    public String toString() {
      return "Node{" + "childNodes=" + childNodes.keySet() + ", word=" + word + '}';
    }
  }

  public void add(String key) {
    if (key == null) {
      throw new IllegalArgumentException("argument to add() is null");
    }
    root = add(root, key, 0);
  }

  private Node add(Node x, String key, int d) {
    if (x == null) {
      x = new Node();
    }

    if (d == key.length()) {
      x.word = key;
      return x;
    }

    char c = key.charAt(d);
    Node node = x.childNodes.get(c);
    node = add(node, key, d + 1);
    x.childNodes.put(c, node);
    return x;
  }

  private void collect(Node x, String key, int d, StringBuilder stringBuilder) {
    if (x == null || d == key.length() || x.childNodes.size() != 1 || x.word != null) {
      return;
    }

    char c = key.charAt(d);
    stringBuilder.append(c);

    collect(x.childNodes.get(c), key, d + 1, stringBuilder);
  }

  public String longestCommonPrefix(ArrayList<String> A) {
    root = null;
    for (String s : A) {
      add(s);
    }
    if (root == null) {
      return null;
    }
    String key = A.get(0);
    StringBuilder stringBuilder = new StringBuilder();
    collect(root, key, 0, stringBuilder);
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    ArrayList<String> a = new ArrayList<>(Arrays.asList("abcdefgh", "aefghijk", "abcefgh"));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("output: " + s.longestCommonPrefix(a));

    a = new ArrayList<>(Arrays.asList("abab", "ab", "abcd"));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("output: " + s.longestCommonPrefix(a));

    a = new ArrayList<>(Arrays.asList("abcd", "aze"));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("output: " + s.longestCommonPrefix(a));

    a =
        new ArrayList<>(
            Arrays.asList(
                "aaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("output: " + s.longestCommonPrefix(a));
  }
}
