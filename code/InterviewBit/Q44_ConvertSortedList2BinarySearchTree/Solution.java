package Q44_ConvertSortedList2BinarySearchTree;

import Q21_InsertionSortList.Solution.ListNode;
import Q43_UniqueBinarySearchTrees.TreeNode;

import java.util.ArrayList;

public class Solution {

  public TreeNode sortedListToBST(ListNode a) {
    int length = 0;
    ListNode curr = a;
    while (curr != null) {
      length++;
      curr = curr.next;
    }
    return sortedListToBST(a, length);
  }

  private TreeNode sortedListToBST(ListNode a, int length) {
    if (a == null || length < 0) {
      return null;
    }
    if (length < 1) {
      return new TreeNode(a.val);
    }
    int half = length / 2;
    ListNode curr = a;
    for (int i = 0; i < half; i++) {
      if (curr.next == null) {
        half = i;
        break;
      }
      curr = curr.next;
    }
    TreeNode node = new TreeNode(curr.val);
    node.left = sortedListToBST(a, half - 1);
    node.right = sortedListToBST(curr.next, length - half - 1);
    return node;
  }

  public static void main(String[] args) {
    Integer[] A = {1, 2, 3};
    runTestCase(A);

    A = new Integer[] {1, 2, 3, 4, 5, 6};
    runTestCase(A);

    A = new Integer[] {1, 2, 3, 4, 5, 6, 7};
    runTestCase(A);

    A = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    runTestCase(A);

    A = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    runTestCase(A);
  }

  private static void runTestCase(Integer[] array) {
    Solution solution = new Solution();

    ListNode node = createListNode(array);
    System.out.println("input: " + node);
    TreeNode result = solution.sortedListToBST(node);
    System.out.println("result: " + result);
    result.print();
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
