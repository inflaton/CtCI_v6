package Q29_PalindromePartitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Solution {
  public ArrayList<ArrayList<String>> partition(String a) {
    TreeSet<ArrayList<String>> ans =
        new TreeSet<>(
            (a1, a2) -> {
              int length = Math.min(a1.size(), a2.size());
              for (int i = 0; i < length; i++) {
                if (a1.get(i).length() < a2.get(i).length()) {
                  return -1;
                }
                if (a1.get(i).length() > a2.get(i).length()) {
                  return +1;
                }
              }
              return 0;
            });

    int length = a.length();
    String[] path = new String[length];
    findPalindrome(a, 0, length, 0, path, ans);

    return new ArrayList<>(ans);
  }

  private void findPalindrome(
      String a,
      int start,
      int maxLength,
      int depth,
      String[] path,
      TreeSet<ArrayList<String>> ans) {
    if (start == a.length() && depth > 0) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(path).subList(0, depth));
      ans.add(arrayList);
    }

    int length = Math.min(maxLength, a.length() - start);
    while (length > 0) {
      String palindrome = getPalindrome(a, start, length);
      if (palindrome != null) {
        path[depth] = palindrome;
        findPalindrome(a, start + length, maxLength, depth + 1, path, ans);
      }
      length--;
    }
  }

  private String getPalindrome(String a, int start, int length) {
    for (int i = 0; i < length / 2; i++) {
      if (a.charAt(start + i) != a.charAt(start + length - 1 - i)) {
        return null;
      }
    }
    return a.substring(start, start + length);
  }

  public static void main(String[] args) {
    String s = "aab";
    runTestCase(s);

    s = "redivider";
    runTestCase(s);
  }

  private static void runTestCase(String s) {
    Solution solution = new Solution();
    System.out.println("input: " + s);
    ArrayList<ArrayList<String>> result = solution.partition(s);
    System.out.println("ans: ");
    for (ArrayList<String> p : result) {
      System.out.println(Arrays.toString(p.toArray()));
    }
  }
}
