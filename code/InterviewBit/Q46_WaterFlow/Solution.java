package Q46_WaterFlow;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

  private static class Digraph {
    private final ArrayList<ArrayList<Integer>> A;
    private final int numOfVertices;
    private final int rows;
    private final int cols;
    private final int blueLake;
    private final int redLake;

    public Digraph(ArrayList<ArrayList<Integer>> A) {
      rows = A.size();
      cols = A.get(0).size();
      numOfVertices = rows * cols + 2;
      blueLake = 0;
      redLake = numOfVertices - 1;
      this.A = A;
    }

    private void validateVertex(int v) {
      if (v < 0 || v > numOfVertices - 1) {
        throw new IllegalArgumentException("wrong vertex: " + v);
      }
    }

    private int vertexOf(int i, int j) {
      return i * cols + j + 1;
    }

    public ArrayList<Integer> adj(int v) {
      validateVertex(v);
      ArrayList<Integer> arrayList = new ArrayList<>();
      if (v == blueLake) {
        for (int i = 0; i < rows; i++) {
          arrayList.add(vertexOf(i, 0));
        }
        for (int i = 0; i < cols; i++) {
          arrayList.add(vertexOf(0, i));
        }
      } else if (v == redLake) {
        for (int i = 0; i < rows; i++) {
          arrayList.add(vertexOf(i, cols - 1));
        }
        for (int i = 0; i < cols; i++) {
          arrayList.add(vertexOf(rows - 1, i));
        }
      } else {
        int i = (v - 1) / cols;
        int j = (v - 1) % cols;

        int height = A.get(i).get(j);
        if (i > 0 && height <= A.get(i - 1).get(j)) {
          arrayList.add(vertexOf(i - 1, j));
        }
        if (i < rows - 1 && height <= A.get(i + 1).get(j)) {
          arrayList.add(vertexOf(i + 1, j));
        }
        if (j > 0 && height <= A.get(i).get(j - 1)) {
          arrayList.add(vertexOf(i, j - 1));
        }
        if (j < cols - 1 && height <= A.get(i).get(j + 1)) {
          arrayList.add(vertexOf(i, j + 1));
        }
      }
      return arrayList;
    }
  }

  public int solve(ArrayList<ArrayList<Integer>> A) {
    int rows = A.size();
    if (rows == 0) {
      return 0;
    }

    Digraph digraph = new Digraph(A);

    int count = 0;
    boolean[] marked = new boolean[digraph.numOfVertices];
    int[] edgeTo = new int[digraph.numOfVertices];
    Arrays.fill(edgeTo, -1);
    dfs(digraph, digraph.blueLake, marked, edgeTo);

    Arrays.fill(marked, false);
    return dfs2(digraph, digraph.redLake, marked, edgeTo);
  }

  private void dfs(Digraph digraph, int v, boolean[] marked, int[] edgeTo) {
    if (marked[v]) {
      return;
    }
    marked[v] = true;
    ArrayList<Integer> adjVertices = digraph.adj(v);
    for (Integer w : adjVertices) {
      edgeTo[w] = v;
      dfs(digraph, w, marked, edgeTo);
    }
  }

  private int dfs2(Digraph digraph, int v, boolean[] marked, int[] edgeTo) {
    if (marked[v]) {
      return 0;
    }
    marked[v] = true;
    boolean isRealCell = v > 0 && v < edgeTo.length - 1;
    int count = isRealCell && edgeTo[v] >= 0 ? 1 : 0;
    ArrayList<Integer> adjVertices = digraph.adj(v);
    for (Integer w : adjVertices) {
      count += dfs2(digraph, w, marked, edgeTo);
    }
    return count;
  }

  public static void main(String[] args) {
    int a = 5;
    Integer[][] A = {{2, 2}, {2, 2}};
    runTestCase(A, 4);

    A =
        new Integer[][] {
          {1, 2, 2, 3, 5},
          {3, 2, 3, 4, 4},
          {2, 4, 5, 3, 1},
          {6, 7, 1, 4, 5},
          {5, 1, 1, 2, 4}
        };
    runTestCase(A, 7);

    A =
        new Integer[][] {
          {15, 1, 10},
          {5, 19, 19},
          {3, 5, 6},
          {6, 2, 8},
          {2, 12, 16},
          {3, 8, 17},
          {12, 5, 3},
          {14, 13, 3},
          {2, 17, 19},
          {16, 8, 7},
          {12, 19, 10},
          {13, 8, 20}
        };
    runTestCase(A, 16);
  }

  private static void runTestCase(Integer[][] array, int expected) {
    Solution solution = new Solution();
    ArrayList<ArrayList<Integer>> A = new ArrayList<>();
    for (Integer[] a : array) {
      A.add(new ArrayList<>(Arrays.asList(a)));
    }
    System.out.println("B: " + A);
    int result = solution.solve(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
