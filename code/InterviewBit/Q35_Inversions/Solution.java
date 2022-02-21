package Q35_Inversions;

import java.util.*;

public class Solution {
  // Using Trie
  static class Node {
    int count = 1;
    Node left;
    Node right;
  }

  // Insert element in trie
  private int insertElement(Node root, int num, int ans) {
    // Converting the number into binary form
    for (int i = 63; i >= 0; i--) {
      // Checking if the i-th  bit ios set or not
      int a = (num & (1 << i));

      // If the bit is 1
      if (a != 0) {
        // if the bit is 1 that means
        // we have to go to the right
        // but we also checks if left
        // pointer exists i.e there is
        // at least a number smaller than
        // the current number already in
        // the trie we add that count
        // to ans
        if (root.left != null) {
          ans += root.left.count;
        }

        // If right pointer is not null
        // we just iterate to that
        // position and increment the count
        if (root.right != null) {
          root = root.right;
          root.count += 1;
        }

        // If right is null we add a new
        // node over there and initialize
        // the count with 1
        else {
          root.right = new Node();
          root = root.right;
        }
      }

      // if the bit is 0
      else {
        // We have to iterate to left,
        // we first check if left
        // exists? if yes then change
        // the root and the count
        if (root.left != null) {
          root = root.left;
          root.count++;
        }
        // otherwise we create
        // the left node
        else {
          root.left = new Node();
          root = root.left;
        }
      }
    }

    return ans;
  }

  public int countInversions(ArrayList<Integer> A) {
    int ans = 0;
    Node head = new Node();
    for (int i = A.size() - 1; i >= 0; i--) {
      ans = insertElement(head, A.get(i), ans);
    }
    return ans;
  }

  // Heapsort + Bisection
  static class Pair {
    int index;
    int element;

    Pair(int i, int e) {
      index = i;
      element = e;
    }

    public int getIndex() {
      return index;
    }

    public int getElement() {
      return element;
    }

    @Override
    public String toString() {
      return "Pair{" + "index=" + index + ", element=" + element + '}';
    }
  }

  public int countInversionsHeapsortBisection(ArrayList<Integer> A) {
    PriorityQueue<Pair> pq =
        new PriorityQueue<>(Comparator.comparing(Pair::getElement).thenComparing(Pair::getIndex));

    int i;
    for (i = 0; i < A.size(); i++) {
      pq.add(new Pair(i, A.get(i)));
    }

    int count = 0;
    int[] sortedIndices = new int[A.size()];
    int length = 0;

    while (!pq.isEmpty()) {
      Pair currentMin = pq.remove();
      // System.out.println(currentMin);
      // find the current minimum's index
      i = currentMin.index;

      // the index y can represent how many minimums on the left
      int y = bisect(sortedIndices, length, i); // O(log(N))
      // System.out.println("y: " + y + ", length: " + length);

      // i can represent how many elements on the left
      // i - y can find how many bigger numbers on the left
      count += i - y;

      if (y < length) {
        System.arraycopy(sortedIndices, y, sortedIndices, y + 1, length - y);
      }
      sortedIndices[y] = i;
      length++;
    }

    return count;
  }

  private int bisect(int[] sortedArray, int length, int v) {
    int lo = 0;
    int hi = length;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (sortedArray[mid] >= v) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  // merge and count
  private static int merge(int[] a, int[] aux, int lo, int mid, int hi) {
    int inversions = 0;

    // copy to aux[]
    if (hi + 1 - lo >= 0) {
      System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
    }

    // merge back to a[]
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (aux[j] < aux[i]) {
        a[k] = aux[j++];
        inversions += (mid - i + 1);
      } else {
        a[k] = aux[i++];
      }
    }
    return inversions;
  }

  // return the number of inversions in the subarray b[lo..hi]
  // side effect b[lo..hi] is rearranged in ascending order
  private static int count(int[] a, int[] b, int[] aux, int lo, int hi) {
    int inversions = 0;
    if (hi <= lo) {
      return 0;
    }
    int mid = lo + (hi - lo) / 2;
    inversions += count(a, b, aux, lo, mid);
    inversions += count(a, b, aux, mid + 1, hi);
    inversions += merge(b, aux, lo, mid, hi);
    return inversions;
  }

  public int countInversionsMergeSort(ArrayList<Integer> A) {
    int[] aux = new int[A.size()];
    int[] a = new int[A.size()];
    for (int i = 0; i < A.size(); i++) {
      a[i] = A.get(i);
    }
    int[] b = Arrays.copyOf(a, a.length);
    return count(a, b, aux, 0, a.length - 1);
  }

  public int countInversionsBruteForce(ArrayList<Integer> A) {
    int count = 0;
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        if (A.get(i) > A.get(j)) {
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Integer[] s = {2, 4, 1, 3, 5};
    runTestCase(s, 3);

    Integer[] s1 = {1, 2, 3, 4, 5};
    runTestCase(s1, 0);

    Integer[] s2 = {
      84, 2, 37, 3, 67, 82, 19, 97, 91, 63, 27, 6, 13, 90, 63, 89, 100, 60, 47, 96, 54, 26, 64, 50,
      71, 16, 6, 40, 84, 93, 67, 85, 16, 22, 60
    };
    runTestCase(s2, 290);
  }

  private static void runTestCase(Integer[] s, int expected) {
    Solution solution = new Solution();
    System.out.println("input: " + Arrays.toString(s));
    int result = solution.countInversions(new ArrayList<>(Arrays.asList(s)));
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
