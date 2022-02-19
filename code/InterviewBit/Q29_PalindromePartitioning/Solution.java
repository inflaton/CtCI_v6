package Q29_PalindromePartitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  public ArrayList<ArrayList<String>> partition(String a) {
    PriorityQueue<ArrayList<String>> pq =
        new PriorityQueue<>(
            new Comparator<ArrayList<String>>() {

              @Override
              public int compare(ArrayList<String> a1, ArrayList<String> a2) {
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
              }
            });

    int length = a.length();
    String[] path = new String[length];
    findPalindrome(a, 0, length, 0, path, pq);

    ArrayList<ArrayList<String>> ans = new ArrayList<>();
    while (!pq.isEmpty()) {
      ans.add(pq.remove());
    }
    return ans;
  }

  private void findPalindrome(
      String a,
      int start,
      int maxLength,
      int depth,
      String[] path,
      PriorityQueue<ArrayList<String>> priorityQueue) {
    if (start == a.length() && depth > 0) {
      ArrayList<String> arrayList = new ArrayList<>();
      for (int i = 0; i < depth; i++) {
        arrayList.add(path[i]);
      }
      priorityQueue.add(arrayList);
    }

    int length = Math.min(maxLength, a.length() - start);
    while (length > 0) {
      String palindrome = getPalindrome(a, start, length);
      if (palindrome != null) {
        path[depth] = palindrome;
        findPalindrome(a, start + length, maxLength, depth + 1, path, priorityQueue);
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