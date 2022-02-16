package Q21_InsertionSortList;

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

  public ListNode insertionSortList(ListNode A) {
    if (A == null) {
      return null;
    }
    ListNode head = A;
    ListNode current = A.next;
    head.next = null;

    while (current != null) {
      ListNode next = current.next;

      if (current.val < head.val) {
        current.next = head;
        head = current;
      } else {
        ListNode prev = head;
        ListNode iter = head.next;
        while (iter != null) {
          if (iter.val >= current.val) {
            break;
          }
          prev = iter;
          iter = iter.next;
        }
        prev.next = current;
        current.next = iter;
      }

      current = next;
    }
    return head;
  }

  /*
      protected void insertionSort(T[] a, int lo, int hi) {
          for (int i = lo + 1; i <= hi; i++) {
              for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                  swap(a, j, j - 1);
              }
          }
      }
  */

  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(3);
    ListNode c = new ListNode(2);
    a.next = b;
    b.next = c;
    System.out.println("a: " + a);
    ListNode ans = new Solution().insertionSortList(a);
    System.out.println("ans: " + a);
  }
}
