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

    ListNode end = A;
    while (end.next != null) {
      end = end.next;
    }

    sort(A, end);
    return A;
  }

  private void sort(ListNode start, ListNode end) {
    if (start == end) {
      return;
    }

    // split list and partition recurse
    ListNode pivotPrev = partition(start, end);

    if (pivotPrev == null) {
      return;
    }

    sort(start, pivotPrev);

    ListNode rightStart = pivotPrev.next;
    if (start != pivotPrev && rightStart != null && rightStart != end) {
      rightStart = rightStart.next;
    }

    sort(rightStart, end);
  }

  private ListNode partition(ListNode start, ListNode end) {
    if (start == end || start == null || end == null) {
      return start;
    }

    ListNode pivotPrev = start;
    ListNode curr = start;
    int pivot = end.val;

    while (start != end) {
      if (start.val < pivot) {
        // keep tracks of last modified item
        pivotPrev = curr;
        swap(curr, start);
        curr = curr.next;
      }

      start = start.next;
    }

    swap(end, curr);
    return pivotPrev;
  }

  private void swap(ListNode i, ListNode j) {
    if (i != j) {
      int temp = i.val;
      i.val = j.val;
      j.val = temp;
    }
  }

  public static void main(String[] args) {
    int[] array = {1, 3, 2};
    runTestCase(array);

    array = new int[] {19, 31, 26, 98, 67, 100, 2, 24, 6, 37, 69, 11, 16, 61, 23};
    runTestCase(array);

    array =
        new int[] {
          48, 5, 66, 68, 42, 73, 25, 84, 63, 72, 20, 77, 38, 8, 99, 92, 49, 74, 45, 30, 51, 50, 95,
              56,
          19, 31, 26, 98, 67, 100, 2, 24, 6, 37, 69, 11, 16, 61, 23, 78, 27, 64, 87, 3, 85, 55, 22,
              33,
          62
        };
    runTestCase(array);
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

    prev = ans;
    ListNode curr = ans.next;

    while (curr != null) {
      if (prev.val > curr.val) {
        throw new AssertionError();
      }
      prev = curr;
      curr = curr.next;
    }
  }
}
