package Q50_WordLadderII;

import java.util.*;

public class Solution {

  private boolean[] marked; // marked[v] = is there an s->v path?
  private int[] distTo; // distTo[v] = length of shortest s->v path
  private ArrayList<String> dictionary;

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

  public ArrayList<ArrayList<String>> findLadders(
      String start, String end, ArrayList<String> dict) {
    ArrayList<ArrayList<String>> ans = new ArrayList<>();
    int diff = diff(start, end);
    if (diff <= 1) {
      ArrayList<String> arrayList = new ArrayList<>();
      arrayList.add(start);
      if (diff > 0) {
        arrayList.add(end);
      }
      ans.add(arrayList);
      return ans;
    }

    HashSet<String> uniqueWords = new HashSet<>();
    uniqueWords.add(start);
    uniqueWords.add(end);

    dictionary = new ArrayList<>();
    int size = dict.size();
    for (int i = 0; i < size; i++) {
      String word = dict.get(i);
      if (!uniqueWords.contains(word)) {
        dictionary.add(word);
        uniqueWords.add(word);
      }
    }

    size = dictionary.size();
    int n = size + 2;
    Digraph digraph = new Digraph(n);
    marked = new boolean[n];
    distTo = new int[n];
    Arrays.fill(distTo, -1);

    final int source = size;
    final int target = size + 1;

    for (int i = 0; i < size; i++) {
      addEdges(digraph, i, source, target, start, end, dictionary);
    }

    bfs(digraph, source);

    int maxDepth = distTo[target];

    if (maxDepth > 0) {
      Arrays.fill(marked, false);
      Deque<Integer> stack = new ArrayDeque<>();
      stack.push(source);

      TreeSet<ArrayList<String>> set =
          new TreeSet<>(
              (o1, o2) -> {
                for (int i = 0; i < o1.size(); i++) {
                  String s1 = o1.get(i);
                  String s2 = o2.get(i);
                  for (int j = 0; j < s1.length(); j++) {
                    int c = Character.compare(s1.charAt(j), s2.charAt(j));
                    if (c != 0) {
                      return c;
                    }
                  }
                }
                return 0;
              });
      dfs(digraph, source, target, maxDepth, start, end, stack, set);

      ans = new ArrayList<>(set);
    }
    return ans;
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
    return diff(s1, s2) == 1;
  }

  private int diff(String s1, String s2) {
    int diff = Integer.MAX_VALUE;
    if (s1.length() == s2.length()) {
      diff = 0;
      for (int j = 0; j < s1.length(); j++) {
        if (s1.charAt(j) != s2.charAt(j)) {
          diff++;
        }
        if (diff > 1) {
          break;
        }
      }
    }
    return diff;
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
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
          q.add(w);
        }
      }
    }
  }

  private void dfs(
      Digraph digraph,
      int v,
      int target,
      int maxDepth,
      String start,
      String end,
      Deque<Integer> stack,
      TreeSet<ArrayList<String>> ans) {

    if (marked[v] || stack.size() > maxDepth + 1) {
      return;
    }

    if (v == target) {
      ArrayList<String> arrayList = new ArrayList<>();
      arrayList.add(start);
      for (Iterator<Integer> it = stack.descendingIterator(); it.hasNext(); ) {
        int i = it.next();
        if (i < dictionary.size()) {
          arrayList.add(dictionary.get(i));
        }
      }
      arrayList.add(end);
      ans.add(arrayList);
      return;
    }

    ArrayList<Integer> adjVertices = digraph.adj(v);
    if (adjVertices != null) {
      marked[v] = true;
      stack.push(v);

      for (Integer w : adjVertices) {
        dfs(digraph, w, target, maxDepth, start, end, stack, ans);
      }

      stack.pop();
      marked[v] = false;
    }
  }

  public static void main(String[] args) {
    String A = "hit";
    String B = "cog";
    String[] C = {"hot", "dot", "dog", "lot", "log"};
    runTestCase(A, B, C, 2);

    A = "bb";
    B = "ab";
    String[] C2 = {"bb", "ab"};
    runTestCase(A, B, C2, 1);

    A = "bb";
    B = "bb";
    String[] C3 = {"bb", "bb"};
    runTestCase(A, B, C3, 1);

    A = "bbaa";
    B = "babb";
    String[] C4 = {
      "baba", "abba", "aaba", "bbbb", "abaa", "abab", "aaab", "abba", "abba", "abba", "bbba",
      "aaab", "abaa", "baba", "baaa", "bbaa", "babb"
    };
    runTestCase(A, B, C4, 3);
  }

  private static void runTestCase(String a, String b, String[] c, int expected) {
    ArrayList<String> C = new ArrayList<>(Arrays.asList(c));
    System.out.println("A: " + a);
    System.out.println("B: " + b);
    System.out.println("C: " + C);
    Solution solution = new Solution();
    ArrayList<ArrayList<String>> result = solution.findLadders(a, b, C);
    System.out.println("result: " + result);
    if (result.size() != expected) {
      throw new AssertionError(
          "result: " + result + " length does not equal to expected: " + expected);
    }
  }
}
