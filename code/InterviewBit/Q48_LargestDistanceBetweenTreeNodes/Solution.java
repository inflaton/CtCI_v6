package Q48_LargestDistanceBetweenTreeNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
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
    Digraph digraph = new Digraph(A.size() + 1);
    final int start = A.size();
    for (int i = 0; i < A.size(); i++) {
      digraph.addEdge(start, i);
      int parent = A.get(i);
      if (parent >= 0) {
        digraph.addEdge(i, parent);
        digraph.addEdge(parent, i);
      }
    }

    boolean[] onStack = new boolean[digraph.numberOfVertices];
    int[] maxDepth = new int[digraph.numberOfVertices];
    dfs(digraph, start, onStack, maxDepth, 0);
    return Arrays.stream(maxDepth).max().orElse(0) - 1;
  }

  private void dfs(Digraph digraph, int v, boolean[] onStack, int[] maxDepth, int depth) {
    if (onStack[v]) {
      return;
    }

    if (maxDepth[v] < depth) {
      maxDepth[v] = depth;
    }

    ArrayList<Integer> adjVertices = digraph.adj(v);
    if (adjVertices != null) {
      onStack[v] = true;
      for (Integer w : adjVertices) {
        dfs(digraph, w, onStack, maxDepth, depth + 1);
      }
      onStack[v] = false;
    }
  }

  public static void main(String[] args) {
    Integer[] A = {-1, 0, 0, 0, 3};
    runTestCase(A, 3);

    A = new Integer[] {-1, 0, 0, 0, 3, 4};
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
