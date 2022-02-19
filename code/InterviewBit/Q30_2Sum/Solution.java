package Q30_2Sum;

import java.util.*;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
    ArrayList<Integer> ans = new ArrayList<>();
    Hashtable<Integer, Integer> hashtable = new Hashtable<>();
    HashSet<Integer> itemsSeen = new HashSet<>();
    for (int i = 0; i < A.size(); i++) {
      int item = A.get(i);
      Integer index1 = hashtable.get(item);
      if (index1 == null) {
        if (!itemsSeen.contains(item)) {
          hashtable.put(B - item, i + 1);
          itemsSeen.add(item);
        }
      } else {
        ans.add(index1);
        ans.add(i + 1);
        break;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    Integer[] s = {2, 7, 11, 15};
    int[] expected = {1, 2};
    runTestCase(s, 9, expected);

    s =
        new Integer[] {
          4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4, -7,
          7, 9, -4, 4, -8
        };
    expected = new int[] {4, 8};
    runTestCase(s, -3, expected);

    s = new Integer[] {1, 1, 1};
    expected = new int[] {1, 2};
    runTestCase(s, 2, expected);
  }

  private static void runTestCase(Integer[] s, int target, int[] expected) {
    Solution solution = new Solution();
    ArrayList<Integer> result = solution.twoSum(Arrays.asList(s), target);
    System.out.println(result);
    if (result.size() != 2) throw new AssertionError();
    for (int i = 0; i < expected.length; i++) {
      if (result.get(i) != expected[i]) throw new AssertionError();
    }
  }
}
