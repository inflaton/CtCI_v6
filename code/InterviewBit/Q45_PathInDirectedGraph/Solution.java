package Q45_PathInDirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

  private static class Digraph {
    private final int size;
    private final HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();

    public Digraph(int size) {
      this.size = size;
    }

    public void addEdge(int v, int w) {
      validateVertex(v);
      validateVertex(w);
      ArrayList<Integer> vertices = edges.computeIfAbsent(v, k -> new ArrayList<>());
      vertices.add(w);
    }

    private void validateVertex(int v) {
      if (v < 1 || v > size) {
        throw new IllegalArgumentException();
      }
    }

    public ArrayList<Integer> adj(int v) {
      validateVertex(v);
      return edges.get(v);
    }
  }

  public int solve(int A, ArrayList<ArrayList<Integer>> B) {
    Digraph digraph = new Digraph(A);
    for (ArrayList<Integer> edges : B) {
      digraph.addEdge(edges.get(0), edges.get(1));
    }
    boolean[] marked = new boolean[A];
    dfs(digraph, 1, marked);
    return marked[A - 1] ? 1 : 0;
  }

  private void dfs(Digraph digraph, int v, boolean[] marked) {
    if (marked[v - 1]) {
      return;
    }
    marked[v - 1] = true;
    ArrayList<Integer> adjVertices = digraph.adj(v);
    if (adjVertices != null) {
      for (Integer w : adjVertices) {
        dfs(digraph, w, marked);
      }
    }
  }

  public static void main(String[] args) {
    int A = 5;
    Integer[][] B = {{1, 2}, {4, 1}, {2, 4}, {3, 4}, {5, 2}, {1, 3}};
    runTestCase(A, B, 0);

    B = new Integer[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
    runTestCase(A, B, 1);
  }

  private static void runTestCase(int A, Integer[][] array, int expected) {
    Solution solution = new Solution();
    ArrayList<ArrayList<Integer>> B = new ArrayList<>();
    for (Integer[] a : array) {
      B.add(new ArrayList<>(Arrays.asList(a)));
    }
    System.out.println("A: " + A);
    System.out.println("B: " + B);
    int result = solution.solve(A, B);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
