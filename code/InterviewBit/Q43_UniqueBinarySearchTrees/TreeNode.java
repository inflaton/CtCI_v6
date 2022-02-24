package Q43_UniqueBinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
  private static int MAX_WIDTH = 180;

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }

  @Override
  public String toString() {
    return "TreeNode{" + "val=" + val + '}';
  }

  public int getVal() {
    return val;
  }

  public TreeNode getLeft() {
    return left;
  }

  public TreeNode getRight() {
    return right;
  }

  // Get text to be printed
  public String getText() {
    return String.valueOf(val);
  }

  public void print() {
    printTree(this);
  }

  public static void printTree(TreeNode root) {
    if (root == null) {
      System.out.println("<empty tree>");
      return;
    }

    String display = getTreeDisplay(root);
    String[] lines = display.split("\n");
    int width = 0;
    for (String line : lines) {
      int len = line.length();
      if (len > width) {
        width = len;
      }
    }

    if (width <= MAX_WIDTH) {
      System.out.println(display);
      return;
    }

    TreeNode right = root.getRight();
    if (right != null) {
      printTree(right, true, "");
    }

    printNodeValue(root);

    TreeNode left = root.getLeft();
    if (left != null) {
      printTree(left, false, "");
    }

    System.out.println();
  }

  private static void printNodeValue(TreeNode node) {
    if (node.getText() == null) {
      System.out.print("<null>");
    } else {
      System.out.print(node.getText());
    }
    System.out.print('\n');
  }

  // use string and not StringBuffer on purpose as we need to change the indent at
  // each recursion
  private static void printTree(TreeNode node, boolean isRight, String indent) {
    TreeNode right = node.getRight();
    if (right != null) {
      printTree(right, true, indent + (isRight ? "        " : " |      "));
    }
    System.out.print(indent);
    if (isRight) {
      System.out.print(" /");
    } else {
      System.out.print(" \\");
    }
    System.out.print("----- ");

    printNodeValue(node);

    TreeNode left = node.getLeft();
    if (left != null) {
      printTree(left, false, indent + (isRight ? " |      " : "        "));
    }
  }

  // Print a binary tree.
  public static String getTreeDisplay(TreeNode root) {

    StringBuilder sb = new StringBuilder();
    List<List<String>> lines = new ArrayList<List<String>>();
    List<TreeNode> level = new ArrayList<TreeNode>();
    List<TreeNode> next = new ArrayList<TreeNode>();

    level.add(root);
    int nn = 1;
    int widest = 0;

    while (nn != 0) {
      nn = 0;
      List<String> line = new ArrayList<String>();
      for (TreeNode n : level) {
        if (n == null) {
          line.add(null);
          next.add(null);
          next.add(null);
        } else {
          String aa = n.getText();
          line.add(aa);
          if (aa.length() > widest) widest = aa.length();

          next.add(n.getLeft());
          next.add(n.getRight());

          if (n.getLeft() != null) nn++;
          if (n.getRight() != null) nn++;
        }
      }

      if (widest % 2 == 1) widest++;

      lines.add(line);

      List<TreeNode> tmp = level;
      level = next;
      next = tmp;
      next.clear();
    }

    int perPiece = lines.get(lines.size() - 1).size() * (widest + 4);
    for (int i = 0; i < lines.size(); i++) {
      List<String> line = lines.get(i);
      int hpw = (int) Math.floor(perPiece / 2f) - 1;
      if (i > 0) {
        for (int j = 0; j < line.size(); j++) {

          // split node
          char c = ' ';
          if (j % 2 == 1) {
            if (line.get(j - 1) != null) {
              c = (line.get(j) != null) ? '#' : '#';
            } else {
              if (j < line.size() && line.get(j) != null) c = '#';
            }
          }
          sb.append(c);

          // lines and spaces
          if (line.get(j) == null) {
            for (int k = 0; k < perPiece - 1; k++) {
              sb.append(' ');
            }
          } else {
            for (int k = 0; k < hpw; k++) {
              sb.append(j % 2 == 0 ? " " : "#");
            }
            sb.append(j % 2 == 0 ? "#" : "#");
            for (int k = 0; k < hpw; k++) {
              sb.append(j % 2 == 0 ? "#" : " ");
            }
          }
        }
        sb.append('\n');
      }
      for (int j = 0; j < line.size(); j++) {
        String f = line.get(j);
        if (f == null) f = "";
        int gap1 = (int) Math.ceil(perPiece / 2f - f.length() / 2f);
        int gap2 = (int) Math.floor(perPiece / 2f - f.length() / 2f);

        for (int k = 0; k < gap1; k++) {
          sb.append(' ');
        }
        sb.append(f);
        for (int k = 0; k < gap2; k++) {
          sb.append(' ');
        }
      }
      sb.append('\n');

      perPiece /= 2;
    }

    return sb.toString();
  }
}