package Q20_KthSmallestElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int kthsmallest(final List<Integer> A, int B) {
    int k = B - 1;
    Integer[] a = A.toArray(new Integer[0]);
    int lo = 0, hi = a.length - 1;
    while (hi > lo) {
      int i = partition(a, lo, hi);
      if (i > k) hi = i - 1;
      else if (i < k) lo = i + 1;
      else return a[i];
    }
    return a[lo];
  }

  // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
  // and return the index j.
  private int partition(Integer[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Integer v = a[lo];
    while (true) {

      // find item on lo to swap
      while (a[++i] < v) {
        if (i == hi) break;
      }

      // find item on hi to swap
      while (v < a[--j]) {
        if (j == lo) break; // redundant since a[lo] acts as sentinel
      }

      // check if pointers cross
      if (i >= j) break;

      swap(a, i, j);
    }

    // put partitioning item v at a[j]
    swap(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private void swap(Integer[] a, int i, int j) {
    Integer temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    runTestCase(new Integer[] {2, 1, 4, 3, 2}, 3, 2);
    runTestCase(new Integer[] {47, 7}, 2, 47);
  }

  private static void runTestCase(Integer[] array, int b, int expected) {
    Solution s = new Solution();
    ArrayList<Integer> a = new ArrayList<>(Arrays.asList(array));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("b: " + b);
    Integer[] sa = new Integer[a.size()];
    a.toArray(sa);
    Arrays.sort(sa);
    System.out.println("sorted: " + Arrays.toString(sa));

    int ans = s.kthsmallest(a, b);
    System.out.println("output: " + ans);
    if (ans != expected) {
      throw new AssertionError();
    }
  }
}
