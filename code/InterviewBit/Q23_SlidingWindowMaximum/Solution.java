package Q23_SlidingWindowMaximum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
    ArrayList<Integer> arrayList = new ArrayList<>();
    int count = 1;
    if (B < A.size()) {
      count = A.size() - B + 1;
    }

    int i = 0;
    int lastMax = Integer.MIN_VALUE;
    while (count > 0) {
      int max;
      int end = Math.min(i + B, A.size());
      if (i == 0 || lastMax == A.get(i - 1)) {
        max = A.get(i);
        for (int j = i + 1; j < end; j++) {
          if (A.get(j) > max) {
            max = A.get(j);
          }
        }
      } else {
        max = Math.max(lastMax, A.get(end - 1));
      }
      arrayList.add(max);
      lastMax = max;
      count--;
      i++;
    }

    return arrayList;
  }

  public ArrayList<Integer> slidingMaximumBruteForce(final List<Integer> A, int B) {
    ArrayList<Integer> arrayList = new ArrayList<>();
    int count = 1;
    if (B < A.size()) {
      count = A.size() - B + 1;
    }

    int i = 0;
    while (count > 0) {
      int max = A.get(i);
      int end = Math.min(i + B, A.size());
      for (int j = i + 1; j < end; j++) {
        if (A.get(j) > max) {
          max = A.get(j);
        }
      }
      arrayList.add(max);
      count--;
      i++;
    }

    return arrayList;
  }

  public static void runTestCase(Integer[] array, int b) {
    Solution solution = new Solution();
    ArrayList<Integer> a = new ArrayList<>(Arrays.asList(array));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("b: " + b);
    ArrayList<Integer> c = solution.slidingMaximum(a, b);
    System.out.println("c: " + Arrays.toString(c.toArray()));
  }

  public static void main(String[] args) {
    Integer[] array = {1, 3, -1, -3, 5, 3, 6, 7};
    runTestCase(array, 9);
    runTestCase(array, 8);
    runTestCase(array, 7);
    runTestCase(array, 3);

    array = new Integer[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    runTestCase(array, 2);
  }
}
