package Q47_CaptureRegionsOnBoard;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

  private static class Digraph {
    private final ArrayList<ArrayList<Character>> A;
    private final int numOfVertices;
    private final int rows;
    private final int cols;

    public Digraph(ArrayList<ArrayList<Character>> A) {
      rows = A.size();
      cols = A.get(0).size();
      numOfVertices = rows * cols + 1;
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
      if (v == 0) {
        for (int i = 0; i < rows; i++) {
          if (A.get(i).get(0) == 'O') {
            arrayList.add(vertexOf(i, 0));
          }
          if (A.get(i).get(cols - 1) == 'O') {
            arrayList.add(vertexOf(i, cols - 1));
          }
        }
        for (int i = 0; i < cols; i++) {
          if (A.get(0).get(i) == 'O') {
            arrayList.add(vertexOf(0, i));
          }
          if (A.get(rows - 1).get(i) == 'O') {
            arrayList.add(vertexOf(rows - 1, i));
          }
        }
      } else {
        int i = (v - 1) / cols;
        int j = (v - 1) % cols;

        char character = A.get(i).get(j);
        if (character == 'O') {
          if (i > 0 && character == A.get(i - 1).get(j)) {
            arrayList.add(vertexOf(i - 1, j));
          }
          if (i < rows - 1 && character == A.get(i + 1).get(j)) {
            arrayList.add(vertexOf(i + 1, j));
          }
          if (j > 0 && character == A.get(i).get(j - 1)) {
            arrayList.add(vertexOf(i, j - 1));
          }
          if (j < cols - 1 && character == A.get(i).get(j + 1)) {
            arrayList.add(vertexOf(i, j + 1));
          }
        }
      }
      return arrayList;
    }
  }

  public void solve(ArrayList<ArrayList<Character>> A) {
    int rows = A.size();
    if (rows == 0) {
      return;
    }

    Digraph digraph = new Digraph(A);

    boolean[] marked = new boolean[digraph.numOfVertices];
    int[] edgeTo = new int[digraph.numOfVertices];
    Arrays.fill(edgeTo, -1);
    dfs(digraph, 0, marked, edgeTo);

    for (int v = 1; v < digraph.numOfVertices; v++) {
      if (edgeTo[v] < 0) {
        int i = (v - 1) / digraph.cols;
        int j = (v - 1) % digraph.cols;
        A.get(i).set(j, 'X');
      }
    }
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

  public static void main(String[] args) {
    int a = 5;
    Character[][] A = {
      {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}
    };
    Character[][] expected = {
      {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}
    };
    runTestCase(A, expected);
  }

  private static void runTestCase(Character[][] array, Character[][] expected) {
    Solution solution = new Solution();
    ArrayList<ArrayList<Character>> A = new ArrayList<>();
    for (Character[] a : array) {
      A.add(new ArrayList<>(Arrays.asList(a)));
    }
    System.out.println("A: " + A);
    solution.solve(A);
    System.out.println("result: " + A);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[i].length; j++) {
        int result = A.get(i).get(j);
        if (result != expected[i][j]) {
          throw new AssertionError(
              "result[ "
                  + i
                  + ", "
                  + j
                  + "]:"
                  + result
                  + " does not equal to expected: "
                  + expected[i][j]);
        }
      }
    }
  }
}
