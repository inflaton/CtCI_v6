package Q35_Inversions;

import java.util.*;

public class Solution {

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
      } else a[k] = aux[i++];
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

  public int countInversions(ArrayList<Integer> A) {
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
