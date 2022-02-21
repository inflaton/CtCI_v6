package Q35_Inversions;

import java.util.*;

public class Solution {

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

  public int countInversions(ArrayList<Integer> A) {
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

  // attempt #2
  static class Node {
    HashSet<Integer> indexSet = new HashSet<>();
    Node next;

    @Override
    public String toString() {
      return "Node{" + "indexSet=" + indexSet + ", next=" + next + '}';
    }
  }

  public int countInversionsAttempt2(ArrayList<Integer> A) {
    int count = 0;
    TreeMap<Integer, Node> map = new TreeMap<>();
    for (int i = 0; i < A.size(); i++) {
      int value = A.get(i);
      Node node = map.get(value);
      if (node == null) {
        node = new Node();
        map.put(value, node);
      }
      node.indexSet.add(i);
    }

    Node curr = null;
    for (int key : map.keySet()) {
      Node node = map.get(key);
      if (curr != null) {
        curr.next = node;
      }
      curr = node;
    }

    for (int i = 0; i < A.size(); i++) {
      int value = A.get(i);
      Node node = map.get(value);
      while (node.next != null) {
        for (int index : node.next.indexSet) {
          if (index < i) {
            count++;
          }
        }
        node = node.next;
      }
    }

    return count;
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
