package Q49_WordLadderI;

import java.util.*;

public class Solution {

  private boolean[] marked; // marked[v] = is there an s->v path?
  private int[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
  private int[] distTo; // distTo[v] = length of shortest s->v path

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
      return edges.getOrDefault(v, new ArrayList<>());
    }
  }

  public int solve(String A, String B, ArrayList<String> C) {
    int size = C.size();
    int n = size + 2;
    Digraph digraph = new Digraph(n);
    marked = new boolean[n];
    distTo = new int[n];
    edgeTo = new int[n];
    Arrays.fill(distTo, -1);

    final int source = size;
    final int target = size + 1;

    for (int i = 0; i < size; i++) {
      addEdges(digraph, i, source, target, A, B, C);
    }

    bfs(digraph, source);

    return distTo[target] + 1;
  }

  private void addEdges(
      Digraph digraph, int v, int source, int target, String a, String b, ArrayList<String> c) {
    String s1 = c.get(v);
    for (int i = 0; i < c.size(); i++) {
      if (canTransform(s1, a)) {
        digraph.addEdge(source, v);
      }
      if (canTransform(s1, b)) {
        digraph.addEdge(v, target);
      }
      if (i != v) {
        String s2 = c.get(i);
        if (canTransform(s1, s2)) {
          digraph.addEdge(v, i);
        }
      }
    }
  }

  private boolean canTransform(String s1, String s2) {
    int diff = 0;
    if (s1.length() == s2.length()) {
      for (int j = 0; j < s1.length(); j++) {
        if (s1.charAt(j) != s2.charAt(j)) {
          diff++;
        }
        if (diff > 1) {
          break;
        }
      }
    }
    return diff == 1;
  }
  // BFS from single source
  private void bfs(Digraph digraph, int s) {
    Queue<Integer> q = new ArrayDeque<>();
    marked[s] = true;
    distTo[s] = 0;
    q.add(s);
    while (!q.isEmpty()) {
      int v = q.remove();
      for (int w : digraph.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
          q.add(w);
        }
      }
    }
  }

  public static void main(String[] args) {
    String A = "hit";
    String B = "cog";
    String[] C = {"hot", "dot", "dog", "lot", "log"};
    runTestCase(A, B, C, 5);
  }

  private static void runTestCase(String a, String b, String[] c, int expected) {
    ArrayList<String> C = new ArrayList<>(Arrays.asList(c));
    System.out.println("A: " + a);
    System.out.println("B: " + b);
    System.out.println("C: " + C);
    Solution solution = new Solution();
    int result = solution.solve(a, b, C);
    System.out.println("result: " + result);
    Deque<String> stack = new ArrayDeque<>();
    int v = solution.edgeTo[c.length + 1];
    while (v >= 0 && v < c.length) {
      stack.push(c[v]);
      v = solution.edgeTo[v];
    }
    System.out.println(stack);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
