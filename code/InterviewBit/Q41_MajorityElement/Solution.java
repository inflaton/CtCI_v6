package Q41_MajorityElement;

import java.util.*;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int majorityElement(final List<Integer> A) {
    Hashtable<Integer, Integer> countMap = new Hashtable<>();
    for (Integer i : A) {
      Integer count = countMap.get(i);
      if (count == null) {
        count = 1;
      } else {
        count += 1;
      }
      countMap.put(i, count);
    }

    int ans = Integer.MIN_VALUE;
    int threshold = A.size() / 2;
    for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
      if (entry.getValue() > threshold) {
        ans = entry.getKey();
        break;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    Integer[] A = {2, 1, 2};
    int expected = 2;
    runTestCase(A, expected);

    A = new Integer[] {8, 3, 8, 5, 8, 5, 8, 7, 8, 8, 9, 7, 8};
    expected = 8;
    runTestCase(A, expected);
  }

  private static void runTestCase(Integer[] a, int expected) {
    Solution solution = new Solution();
    ArrayList<Integer> A = new ArrayList<>();
    for (Integer i : a) {
      A.add(i);
    }
    System.out.println("A: " + A);
    int result = solution.majorityElement(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
