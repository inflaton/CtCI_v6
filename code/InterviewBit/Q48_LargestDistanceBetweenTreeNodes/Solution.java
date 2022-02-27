package Q48_LargestDistanceBetweenTreeNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

  private int ans;

  private static class Digraph {
    private final int numberOfVertices;
    private final HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();

    public Digraph(int numberOfVertices) {
      this.numberOfVertices = numberOfVertices;
    }

    public void addEdge(int v, int w) {
      validateVertex(v);
      validateVertex(w);
      ArrayList<Integer> vertices = edges.computeIfAbsent(v, k -> new ArrayList<>());
      vertices.add(w);
    }

    private void validateVertex(int v) {
      if (v < 0 || v > numberOfVertices - 1) {
        throw new IllegalArgumentException();
      }
    }

    public ArrayList<Integer> adj(int v) {
      validateVertex(v);
      return edges.get(v);
    }
  }

  public int solve(ArrayList<Integer> A) {
    Digraph digraph = new Digraph(A.size());
    int root = 0;
    for (int i = 0; i < A.size(); i++) {
      int parent = A.get(i);
      if (parent >= 0) {
        digraph.addEdge(parent, i);
      } else {
        root = i;
      }
    }

    this.ans = 0;
    dfs(digraph, root);
    return ans;
  }

  private int dfs(Digraph digraph, int v) {
    ArrayList<Integer> adjVertices = digraph.adj(v);
    if (adjVertices == null) {
      return 1;
    }

    int max1 = 0;
    int max2 = 0;
    for (Integer w : adjVertices) {
      int height = dfs(digraph, w);
      if (height > max1) {
        max2 = max1;
        max1 = height;
      } else if (height > max2) {
        max2 = height;
      }
    }

    ans = Math.max(ans, max1 + max2);

    return max1 + 1;
  }

  // my own attempt - Time Limit Exceeded
  public int solve2(ArrayList<Integer> A) {
    Digraph digraph = new Digraph(A.size() + 1);
    final int start = A.size();
    boolean[] flags = new boolean[digraph.numberOfVertices];
    for (int i = 0; i < A.size(); i++) {
      int parent = A.get(i);
      if (parent >= 0) {
        digraph.addEdge(i, parent);
        digraph.addEdge(parent, i);
        flags[parent] = true;
      }
    }
    for (int i = 0; i < A.size(); i++) {
      if (!flags[i]) {
        digraph.addEdge(start, i);
      }
    }

    Arrays.fill(flags, false);
    return dfs(digraph, start, flags, 0, 0) - 1;
  }

  private int dfs(Digraph digraph, int v, boolean[] onStack, int maxDepth, int depth) {
    if (onStack[v]) {
      return maxDepth;
    }

    if (maxDepth < depth) {
      maxDepth = depth;
    }

    ArrayList<Integer> adjVertices = digraph.adj(v);
    if (adjVertices != null) {
      onStack[v] = true;
      for (Integer w : adjVertices) {
        maxDepth = dfs(digraph, w, onStack, maxDepth, depth + 1);
      }
      onStack[v] = false;
    }
    return maxDepth;
  }

  // By Shivam Tripathi
  public int solve3(ArrayList<Integer> A) {
    int n = A.size();
    int root = 0;
    ans = 0;

    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      if (A.get(i) == -1) {
        root = i;
      } else {
        adj.get(A.get(i)).add(i);
      }
    }

    dfs(root, adj);

    return ans;
  }

  public int dfs(int v, ArrayList<ArrayList<Integer>> adj) {
    if (adj.get(v).size() == 0) {
      return 1;
    }

    int max1 = 0;
    int max2 = 0;

    for (int w : adj.get(v)) {
      int height = dfs(w, adj);

      if (height > max1) {
        max2 = max1;
        max1 = height;
      } else if (height > max2) {
        max2 = height;
      }
    }

    ans = Math.max(ans, max1 + max2);

    return max1 + 1;
  }

  public static void main(String[] args) {
    Integer[] A = {-1, 0, 0, 0, 3};
    runTestCase(A, 3);

    A = new Integer[] {-1, 0, 0, 0, 3, 4};
    runTestCase(A, 4);

    A = new Integer[] {-1, 0, 0, 0, 3, 3};
    runTestCase(A, 3);

    A = new Integer[] {-1, 0, 0, 0, 3, 3, 4};
    runTestCase(A, 4);
  }

  private static void runTestCase(Integer[] a, int expected) {
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A: " + A);
    Solution solution = new Solution();
    int result = solution.solve(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
