package Q51_CloneGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
  static class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
      return "UndirectedGraphNode{"
          + "label="
          + label
          + ", neighbors="
          + neighbors.stream().map(x -> x.label).collect(Collectors.toList())
          + '}';
    }
  }

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    HashMap<UndirectedGraphNode, UndirectedGraphNode> visitedNodes = new HashMap<>();
    dfs(node, visitedNodes);

    for (UndirectedGraphNode v : visitedNodes.keySet()) {
      UndirectedGraphNode vClone = visitedNodes.get(v);
      for (UndirectedGraphNode w : v.neighbors) {
        vClone.neighbors.add(visitedNodes.get(w));
      }
    }
    return visitedNodes.get(node);
  }

  private void dfs(
      UndirectedGraphNode v, HashMap<UndirectedGraphNode, UndirectedGraphNode> visitedNodes) {
    UndirectedGraphNode node = visitedNodes.get(v);
    if (node == null) {
      node = new UndirectedGraphNode(v.label);
      visitedNodes.put(v, node);
      for (UndirectedGraphNode w : v.neighbors) {
        dfs(w, visitedNodes);
      }
    }
  }

  public static void main(String[] args) {
    int[] labels = {703, 279, 43};
    int[][] adjMatrix = {{1, 0, 0}, {1, 1, 1}, {1, 1, 0}};
    runTestCase(labels, adjMatrix);
  }

  private static void runTestCase(int[] labels, int[][] adjMatrix) {
    ArrayList<UndirectedGraphNode> arrayList = new ArrayList<>();
    for (int label : labels) {
      UndirectedGraphNode node = new UndirectedGraphNode(label);
      arrayList.add(node);
    }
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < adjMatrix[i].length; j++) {
        if (adjMatrix[i][j] == 1 || adjMatrix[j][i] == 1) {
          arrayList.get(i).neighbors.add(arrayList.get(j));
        }
      }
    }
    Solution solution = new Solution();
    UndirectedGraphNode root = arrayList.get(0);
    System.out.println("root: " + root);

    UndirectedGraphNode clonedRoot = solution.cloneGraph(root);
    System.out.println("clonedRoot: " + clonedRoot);
  }
}
