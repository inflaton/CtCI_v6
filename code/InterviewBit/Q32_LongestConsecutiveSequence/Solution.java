package Q32_LongestConsecutiveSequence;

import java.util.*;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int longestConsecutive(final List<Integer> A) {
    int ans = 0;
    HashSet<Integer> hashSet = new HashSet<>(A);
    for (Integer i : A) {
      if (!hashSet.contains(i - 1)) {
        int count = 0;
        while (hashSet.contains(i)) {
          count++;
          i++;
        }
        if (ans < count) {
          ans = count;
        }
      }
    }
    return ans;
  }

  public int longestConsecutiveBruteForce(final List<Integer> A) {
    TreeSet<Integer> treeSet = new TreeSet<>(A);
    int size = treeSet.size();
    if (size < 2) {
      return size;
    }

    int maxLen = 1;
    int seqLen = 0;
    int lastInt = 0;
    int counter = 0;

    for (Integer i : treeSet) {
      counter++;
      boolean inSeq = counter > 1 && i == lastInt + 1;
      lastInt = i;

      if (inSeq) {
        seqLen += 1;
      }
      if (!inSeq || counter == treeSet.size()) {
        if (seqLen > maxLen) {
          maxLen = seqLen;
        }

        seqLen = 1;
      }
    }

    return maxLen;
  }

  public static void main(String[] args) {
    Integer[] s = {100, 4, 200, 1, 3, 2};
    int expected = 4;
    runTestCase(s, expected);

    s = new Integer[] {6, 4, 5, 2, 3};
    expected = 5;
    runTestCase(s, expected);

    s = new Integer[] {-1};
    expected = 1;
    runTestCase(s, expected);

    s = new Integer[] {-1, -1};
    runTestCase(s, expected);

    s =
        new Integer[] {
          97, 86, 67, 19, 107, 9, 8, 49, 23, 46, -4, 22, 72, 4, 57, 111, 20, 52, 99, 2, 113, 81, 7,
          5, 21, 0, 47, 54, 76, 117, -2, 102, 34, 12, 103, 69, 36, 51, 105, -3, 33, 91, 37, 11, 48,
          106, 109, 45, 58, 77, 104, 60, 75, 90, 3, 62, 16, 119, 85, 63, 87, 43, 74, 13, 95, 94, 56,
          28, 55, 66, 92, 79, 27, 42, 70
        };
    expected = 6;
    runTestCase(s, expected);
  }

  private static void runTestCase(Integer[] s, int expected) {
    Solution solution = new Solution();
    System.out.println("input: " + s.length + "->" + Arrays.toString(s));
    int result = solution.longestConsecutive(Arrays.asList(s));
    System.out.println("result: " + result);
    if (result != expected)
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
  }
}
