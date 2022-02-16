package Q22_SortLinkedList;

public class Solution {
  static class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }

    @Override
    public String toString() {
      String str = String.valueOf(val);
      if (next != null) {
        str += " -> " + next;
      }
      return str;
    }
  }

  public ListNode sortList(ListNode A) {
    if (A == null) {
      return null;
    }
    ListNode node = A;

    while (node.next != null) {
      node = node.next;
    }
    sort(A, node);
    return A;
  }

  private ListNode partition(ListNode start, ListNode end) {
    if (start == end || start == null || end == null) {
      return start;
    }

    ListNode pivotPrev = null;
    ListNode i = start.next;
    ListNode j = null;

    int v = start.val;
    while (true) {

      // find item on lo to swap
      while (i != null && less(i.val, v)) {
        if (i == end || i == j) {
          break;
        }
        i = i.next;
      }

      // check if pointers cross
      if (i == j) {
        break;
      }

      // find item on hi to swap
      ListNode last = i;
      ListNode curr = i.next;
      while (curr != null && curr != j && less(v, curr.val)) {
        last = curr;
        curr = curr.next;
      }

      // check if pointers cross
      if (curr == j) {
        break;
      }

      pivotPrev = last;
      j = curr;
      swap(i, j);
    }

    swap(start, j);
    return pivotPrev;
  }

  private boolean less(int a, int b) {
    return a < b;
  }

  private void swap(ListNode i, ListNode j) {
    if (i != null && j != null) {
      int temp = i.val;
      i.val = j.val;
      j.val = temp;
    }
  }

  private void sort(ListNode start, ListNode end) {
    if (start == end) {
      return;
    }

    // split list and partition recurse
    ListNode pivotPrev = partition(start, end);
    System.out.println(pivotPrev);
    if (pivotPrev == null) {
      return;
    }

    sort(start, pivotPrev);

    if (pivotPrev.next != null && pivotPrev.next.next != null) {
      sort(pivotPrev.next.next, end);
    }
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(3);
    ListNode c = new ListNode(2);
    a.next = b;
    b.next = c;
    System.out.println("a: " + a);
    ListNode ans = new Solution().sortList(a);
    System.out.println("ans: " + a);

    int[] array = {19, 31, 26, 98, 67, 100, 2, 24, 6, 37, 69, 11, 16, 61, 23};
    runTestCase(array);
    /*
      array =
          new int[] {
            48, 5, 66, 68, 42, 73, 25, 84, 63, 72, 20, 77, 38, 8, 99, 92, 49, 74, 45, 30, 51, 50, 95,
                56,
            19, 31, 26, 98, 67, 100, 2, 24, 6, 37, 69, 11, 16, 61, 23, 78, 27, 64, 87, 3, 85, 55, 22,
                33,
            62
          };
      runTestCase(array);
    */
  }

  private static void runTestCase(int[] array) {
    ListNode a = null;
    ListNode prev = null;
    for (int i : array) {
      ListNode node = new ListNode(i);
      if (a == null) {
        a = node;
      }
      if (prev != null) {
        prev.next = node;
      }
      prev = node;
    }
    System.out.println("a: " + a);
    ListNode ans = new Solution().sortList(a);
    System.out.println("ans: " + a);
  }
}
