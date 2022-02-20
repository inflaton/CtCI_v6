package Q33_MergeKSortedLists;

import Q21_InsertionSortList.Solution.ListNode;

import java.util.ArrayList;

public class Solution {
  public ListNode mergeKLists(ArrayList<ListNode> a) {
    ListNode ans = null;
    for (ListNode listNode : a) {
      ans = merge(ans, listNode);
    }
    return ans;
  }

  private ListNode merge(ListNode listNode1, ListNode listNode2) {
    ListNode head = null;
    ListNode tail = null;

    while (listNode1 != null || listNode2 != null) {
      if (listNode1 == null) {
        if (head == null) {
          head = listNode2;
        } else {
          tail.next = listNode2;
        }
        break;
      }
      if (listNode2 == null) {
        if (head == null) {
          head = listNode1;
        } else {
          tail.next = listNode1;
        }
        break;
      }
      if (listNode1.val < listNode2.val) {
        if (head == null) {
          head = listNode1;
        } else {
          tail.next = listNode1;
        }
        tail = listNode1;
        listNode1 = listNode1.next;
      } else {
        if (head == null) {
          head = listNode2;
        } else {
          tail.next = listNode2;
        }
        tail = listNode2;
        listNode2 = listNode2.next;
      }
    }

    return head;
  }

  public static void main(String[] args) {
    Integer[] list1 = {1, 10, 20};
    Integer[] list2 = {4, 11, 13};
    Integer[] list3 = {3, 8, 9};

    runTestCase(list1, list2, list3);
  }

  private static void runTestCase(Integer[]... a) {
    Solution solution = new Solution();
    ArrayList<ListNode> arrayList = new ArrayList<>();
    for (Integer[] array : a) {
      ListNode node = createListNode(array);
      arrayList.add(node);
    }
    System.out.println("input: " + arrayList);
    ListNode result = solution.mergeKLists(arrayList);
    System.out.println("result: " + result);
  }

  private static ListNode createListNode(Integer[] array) {
    ListNode head = null;
    ListNode prev = null;
    for (int i : array) {
      ListNode node = new ListNode(i);
      if (head == null) {
        head = node;
      }
      if (prev != null) {
        prev.next = node;
      }
      prev = node;
    }
    return head;
  }
}
