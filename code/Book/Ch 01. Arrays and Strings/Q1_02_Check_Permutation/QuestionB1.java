package Q1_02_Check_Permutation;

import java.util.HashMap;

public class QuestionB1 {
  public static boolean permutation(String s, String t) {
    if (s.length() != t.length()) return false; // Permutations must be same length

    HashMap<Character, Integer> letters = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      int count = letters.getOrDefault(ch, 0);
      letters.put(ch, count + 1);
    }

    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      int count = letters.getOrDefault(ch, 0);
      count--;
      if (count < 0) {
        return false;
      }
      letters.put(ch, count);
    }

    return true; // letters array has no negative values, and therefore no positive values either
  }

  public static void main(String[] args) {
    String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
    for (String[] pair : pairs) {
      String word1 = pair[0];
      String word2 = pair[1];
      boolean anagram = permutation(word1, word2);
      System.out.println(word1 + ", " + word2 + ": " + anagram);
    }
  }
}
