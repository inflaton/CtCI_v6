package Q52_NinjaBuildIdenticalTrees;

import Q43_UniqueBinarySearchTrees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  public int cntMatrix(TreeNode A, TreeNode B) {
    int ans = 0;
    return preOrder(A, B, ans);
  }

  private int preOrder(TreeNode a, TreeNode b, int ans) {
    if (ans < 0) {
      return ans;
    }

    if (a != null && b != null) {
      ans = preOrder(a.left, b.left, ans);
      ans = preOrder(a.right, b.right, ans);
    } else if (a != null) {
      ans = -1;
    } else if (b != null) {
      final Deque<TreeNode> stack = new ArrayDeque<>();
      stack.push(b);

      while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        ans++;
        if (node.right != null) {
          stack.push(node.right);
        }
        if (node.left != null) {
          stack.push(node.left);
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(10);
    a.left = new TreeNode(9);
    a.right = new TreeNode(20);

    TreeNode b = new TreeNode(5);
    b.right = new TreeNode(7);

    int expected = -1;
    runTestCase(a, b, expected);

    b.left = new TreeNode(2);
    b.left.left = new TreeNode(1);

    expected = 1;
    runTestCase(a, b, expected);
  }

  private static void runTestCase(TreeNode a, TreeNode b, int expected) {
    Solution solution = new Solution();
    System.out.println("a:");
    a.print();
    System.out.println("b: ");
    b.print();
    int result = solution.cntMatrix(a, b);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
