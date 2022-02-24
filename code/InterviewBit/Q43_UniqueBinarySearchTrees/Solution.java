package Q43_UniqueBinarySearchTrees;

import Q7_04_Parking_Lot.Level;

import java.util.ArrayList;

public class Solution {

  public ArrayList<TreeNode> generateTrees(int a) {
    ArrayList<TreeNode> ans = new ArrayList<>();
    for (int i = 0; i < a; i++) {
      int val = i + 1;
      if (i == 0) {
        TreeNode node = new TreeNode(val);
        ans.add(node);
      } else {
        int size = ans.size();
        for (int j = 0; j < size; j++) {
          TreeNode node = ans.get(j);
          cloneAndAdd(node, val, ans);

          TreeNode newNode = new TreeNode(val);
          newNode.left = node;
          ans.set(j, newNode);
        }
      }
    }

    return ans;
  }

  private TreeNode cloneAndAdd(TreeNode node, int val, ArrayList<TreeNode> ans) {
    TreeNode newNode = cloneTree(node);
    TreeNode curr = newNode;
    int level = 0;
    while (curr.right != null) {
      level++;
      curr = curr.right;
    }
    curr.right = new TreeNode(val);
    ans.add(newNode);

    while (level > 0) {
      newNode = cloneTree(node);
      curr = newNode;
      TreeNode prev = null;
      for (int i = 0; i < level; i++) {
        prev = curr;
        curr = curr.right;
      }
      TreeNode newRightNode = new TreeNode(val);
      prev.right = newRightNode;
      newRightNode.left = curr;
      ans.add(newNode);

      level--;
    }
    return newNode;
  }

  private TreeNode cloneTree(TreeNode node) {
    if (node == null) {
      return null;
    }
    TreeNode newNode = new TreeNode(node.val);
    newNode.left = cloneTree(node.left);
    newNode.right = cloneTree(node.right);
    return newNode;
  }

  public static void main(String[] args) {
    int A = 1;
    int expected = 1;
    runTestCase(A, expected);

    A = 2;
    expected = 2;
    runTestCase(A, expected);

    A = 3;
    expected = 5;
    runTestCase(A, expected);

    A = 5;
    expected = 16;
    //    runTestCase(A, expected);
  }

  private static void runTestCase(int a, int expected) {
    Solution solution = new Solution();
    System.out.println("a: " + a);
    ArrayList<TreeNode> result = solution.generateTrees(a);
    System.out.println("result: " + result);
    for (TreeNode node : result) {
      node.print();
    }
    if (result.size() != expected) {
      throw new AssertionError(
          "result: " + result.size() + " does not equal to expected: " + expected);
    }
  }
}
