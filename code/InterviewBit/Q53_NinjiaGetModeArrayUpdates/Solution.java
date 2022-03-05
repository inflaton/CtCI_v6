package Q53_NinjiaGetModeArrayUpdates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
  public ArrayList<Integer> getMode(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
    ArrayList<Integer> ans = new ArrayList<>();
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (Integer a : A) {
      int count = counts.getOrDefault(a, 0);
      counts.put(a, count + 1);
    }

    int mode = Integer.MAX_VALUE;

    for (ArrayList<Integer> arrayList : B) {
      int pos = arrayList.get(0) - 1;
      int key = A.get(pos);
      int count = counts.getOrDefault(key, 0);
      counts.put(key, count - 1);

      key = arrayList.get(1);
      count = counts.getOrDefault(key, 0);
      counts.put(key, count + 1);
      A.set(pos, key);

      if (key != mode) {
        int maxCount = 0;
        mode = Integer.MAX_VALUE;
        for (Integer k : counts.keySet()) {
          count = counts.get(k);
          if (count >= maxCount) {
            mode = count > maxCount ? k : Math.min(mode, k);
            maxCount = count;
          }
        }
      }
      ans.add(mode);
    }
    return ans;
  }

  public static void main(String[] args) {
    Integer[] a = {2, 2, 2, 3, 3};
    Integer[][] b = {{1, 3}, {5, 4}, {2, 4}};
    Integer[] expected = {3, 2, 3};
    runTestCase(a, b, expected);
  }

  private static void runTestCase(Integer[] a, Integer[][] b, Integer[] expected) {
    Solution solution = new Solution();
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);

    ArrayList<ArrayList<Integer>> B = new ArrayList<>();
    for (Integer[] array : b) {
      B.add(new ArrayList<>(Arrays.asList(array)));
    }
    System.out.println("B: " + B);

    ArrayList<Integer> result = solution.getMode(A, B);
    System.out.println("result: " + result);
    ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(expected));
    if (!result.equals(expectedResult)) {
      throw new AssertionError(
          "result: " + result + " does not equal to expected: " + expectedResult);
    }
  }
}
