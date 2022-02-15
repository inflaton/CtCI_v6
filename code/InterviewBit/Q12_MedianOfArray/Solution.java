package Q12_MedianOfArray;

import java.util.Arrays;
import java.util.List;

public class Solution {
  // DO NOT MODIFY BOTH THE LISTS
  public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
    int n = a.size();
    int m = b.size();

    if (n == 0 && m == 0) {
      return 0;
    } else if (n == 0) {
      return m % 2 == 1 ? b.get(m / 2) : (b.get(m / 2) + b.get(m / 2 - 1)) / 2.0;
    } else if (m == 0) {
      return n % 2 == 1 ? a.get(n / 2) : (a.get(n / 2) + a.get(n / 2 - 1)) / 2.0;
    }

    // the following call is to make sure len(A) <= len(B).
    // yes, it calls itself, but at most once, shouldn't be
    // considered a recursive solution
    if (n > m) {
      return findMedianSortedArrays(b, a);
    }

    // now, do binary search
    int k = (n + m - 1) / 2;
    int l = 0;
    int r = Math.min(k, n); // r is n, NOT n-1, this is important!!
    while (l < r) {
      int midA = (l + r) / 2;
      int midB = k - midA;
      if (a.get(midA) < b.get(midB)) {
        l = midA + 1;
      } else {
        r = midA;
      }
    }
    // after binary search, we almost get the median because it must be between
    // these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]
    // if (n+m) is odd, the median is the larger one between A[l-1] and B[k-l].
    // and there are some corner cases we need to take care of.
    int median =
        Math.max(
            l > 0 ? a.get(l - 1) : Integer.MIN_VALUE,
            k - l >= 0 ? b.get(k - l) : Integer.MIN_VALUE);
    if (((n + m) & 1) == 1) {
      return median;
    }

    // if (n+m) is even, the median can be calculated by
    //      median = (max(A[l-1], B[k-l]) + min(A[l], B[k-l+1]) / 2.0
    // also, there are some corner cases to take care of.
    int median2 =
        Math.min(
            l < n ? a.get(l) : Integer.MAX_VALUE,
            k - l + 1 < m ? b.get(k - l + 1) : Integer.MAX_VALUE);
    return (median + median2) / 2.0;
  }

  public static void main(String[] args) {
    List<Integer> a = Arrays.asList(1, 4, 5);
    List<Integer> b = Arrays.asList(2, 3);
    runTest(a, b);

    a = Arrays.asList();
    runTest(a, a);

    b = Arrays.asList(20);
    runTest(a, b);

    b = Arrays.asList(20, 30);
    runTest(a, b);

    b = Arrays.asList(20, 30, 40);
    runTest(a, b);

    a = Arrays.asList(1, 4, 5, 6);
    b = Arrays.asList(2, 3);
    runTest(a, b);

    a = Arrays.asList(-41, -33, -24, -21, -20, 27, 48);
    b = Arrays.asList(-9);
    runTest(a, b);

    a = Arrays.asList(-43, -25, -18, -15, -10, 9, 39, 40);
    b = Arrays.asList(-2);
    runTest(a, b);
  }

  private static void runTest(List<Integer> a, List<Integer> b) {
    Solution s = new Solution();
    System.out.println("a: " + Arrays.toString(a.toArray()));
    System.out.println("b: " + Arrays.toString(b.toArray()));
    System.out.println("median: " + s.findMedianSortedArrays(a, b));
    System.out.println("median: " + s.findMedianSortedArrays(b, a));
  }
}
