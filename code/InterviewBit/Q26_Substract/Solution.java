package Q26_Substract;

import Q21_InsertionSortList.Solution.ListNode;

public class Solution {
  public ListNode subtract(ListNode A) {
    int length = 0;
    ListNode curr = A;
    while (curr != null) {
      length++;
      curr = curr.next;
    }

    ListNode next;
    ListNode prev = null;
    curr = A;
    for (int i = 0; i < length / 2; i++) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    next = curr;
    if (length % 2 == 1) {
      curr = curr.next;
    }

    while (curr != null) {
      prev.val = curr.val - prev.val;
      ListNode temp = prev.next;
      prev.next = next;
      next = prev;
      prev = temp;

      curr = curr.next;
    }
    return A;
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5};
    runTestCase(array);

    array = new int[] {1, 2, 3, 4};
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

    System.out.println("a = " + array.length + (array.length > 1000 ? "" : ": " + a));

    long start = System.nanoTime();
    ListNode ans = new Solution().subtract(a);
    long end = System.nanoTime();

    System.out.println("ans: " + ans);
  }
}
