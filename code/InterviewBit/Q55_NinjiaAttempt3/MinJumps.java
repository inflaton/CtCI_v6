package Q55_NinjiaAttempt3;

import java.util.*;

public class MinJumps {

  public static class DirectedEdge {

    private final int v; // from
    private final int w; // to
    private final double weight;

    /**
     * Initializes an edge from vertex {@code v} to vertex {@code w} with the given {@code weight}
     * and zero flow.
     *
     * @param v the tail vertex
     * @param w the head vertex
     * @param weight the weight of the edge
     * @throws IllegalArgumentException if either {@code v} or {@code w} is a negative integer
     * @throws IllegalArgumentException if {@code weight < 0.0}
     */
    public DirectedEdge(int v, int w, double weight) {
      if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
      if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
      if (!(weight >= 0.0)) throw new IllegalArgumentException("Edge weight must be non-negative");
      this.v = v;
      this.w = w;
      this.weight = weight;
    }

    /**
     * Returns the tail vertex of the edge.
     *
     * @return the tail vertex of the edge
     */
    public int from() {
      return v;
    }

    /**
     * Returns the head vertex of the edge.
     *
     * @return the head vertex of the edge
     */
    public int to() {
      return w;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the weight of the edge
     */
    public double weight() {
      return weight;
    }

    /**
     * Returns the endpoint of the edge that is different from the given vertex (unless the edge
     * represents a self-loop in which case it returns the same vertex).
     *
     * @param vertex one endpoint of the edge
     * @return the endpoint of the edge that is different from the given vertex (unless the edge
     *     represents a self-loop in which case it returns the same vertex)
     * @throws IllegalArgumentException if {@code vertex} is not one of the endpoints of the edge
     */
    public int other(int vertex) {
      if (vertex == v) return w;
      else if (vertex == w) return v;
      else throw new IllegalArgumentException("invalid endpoint");
    }

    /**
     * Returns a string representation of the edge.
     *
     * @return a string representation of the edge
     */
    public String toString() {
      return v + "->" + w + " (" + weight + ")";
    }
  }

  public static class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private final ArrayList<DirectedEdge>[] adj;

    /**
     * Initializes an empty flow network with {@code V} vertices and 0 edges.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
      if (V < 0)
        throw new IllegalArgumentException("Number of vertices in a Graph must be non-negative");
      this.V = V;
      this.E = 0;
      adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new ArrayList<>();
      }
    }

    /**
     * Returns the number of vertices in the edge-weighted graph.
     *
     * @return the number of vertices in the edge-weighted graph
     */
    public int V() {
      return V;
    }

    /**
     * Returns the number of edges in the edge-weighted graph.
     *
     * @return the number of edges in the edge-weighted graph
     */
    public int E() {
      return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
      if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Adds the edge {@code e} to the network.
     *
     * @param e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0} and {@code
     *     V-1}
     */
    public void addEdge(DirectedEdge e) {
      int v = e.from();
      int w = e.to();
      validateVertex(v);
      validateVertex(w);
      adj[v].add(e);
      adj[w].add(e);
      E++;
    }

    /**
     * Returns the edges incident on vertex {@code v} (includes both edges pointing to and from
     * {@code v}).
     *
     * @param v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> adj(int v) {
      validateVertex(v);
      return adj[v];
    }

    // return list of all edges - excludes self loops
    public Iterable<DirectedEdge> edges() {
      ArrayList<DirectedEdge> list = new ArrayList<>();
      for (int v = 0; v < V; v++)
        for (DirectedEdge e : adj(v)) {
          if (e.to() != v) list.add(e);
        }
      return list;
    }
  }

  public static class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private final int maxN; // maximum number of elements on PQ
    private int n; // number of elements on PQ
    private final int[] pq; // binary heap using 1-based indexing
    private final int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private final Key[] keys; // keys[i] = priority of i

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0} and {@code maxN -
     * 1}.
     *
     * @param maxN the keys on this priority queue are index from {@code 0} {@code maxN - 1}
     * @throws IllegalArgumentException if {@code maxN < 0}
     */
    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxN) {
      if (maxN < 0) throw new IllegalArgumentException();
      this.maxN = maxN;
      n = 0;
      keys = (Key[]) new Comparable[maxN + 1]; // make this of length maxN??
      pq = new int[maxN + 1];
      qp = new int[maxN + 1]; // make this of length maxN??
      for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
      return n == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param i an index
     * @return {@code true} if {@code i} is an index on this priority queue; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
      validateIndex(i);
      return qp[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
      return n;
    }

    /**
     * Associates key with index {@code i}.
     *
     * @param i an index
     * @param key the key to associate with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated with index {@code i}
     */
    public void insert(int i, Key key) {
      validateIndex(i);
      if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
      n++;
      qp[i] = n;
      pq[n] = i;
      keys[i] = key;
      swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
      if (n == 0) throw new NoSuchElementException("Priority queue underflow");
      return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key minKey() {
      if (n == 0) throw new NoSuchElementException("Priority queue underflow");
      return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
      if (n == 0) throw new NoSuchElementException("Priority queue underflow");
      int min = pq[1];
      exch(1, n--);
      sink(1);
      assert min == pq[n + 1];
      qp[min] = -1; // delete
      keys[min] = null; // to help with garbage collection
      pq[n + 1] = -1; // not needed
      return min;
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public Key keyOf(int i) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      else return keys[i];
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param i the index of the key to change
     * @param key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void changeKey(int i, Key key) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      keys[i] = key;
      swim(qp[i]);
      sink(qp[i]);
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param i the index of the key to change
     * @param key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @deprecated Replaced by {@code changeKey(int, Key)}.
     */
    @Deprecated
    public void change(int i, Key key) {
      changeKey(i, key);
    }

    /**
     * Decrease the key associated with index {@code i} to the specified value.
     *
     * @param i the index of the key to decrease
     * @param key decrease the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      if (keys[i].compareTo(key) == 0)
        throw new IllegalArgumentException(
            "Calling decreaseKey() with a key equal to the key in the priority queue");
      if (keys[i].compareTo(key) < 0)
        throw new IllegalArgumentException(
            "Calling decreaseKey() with a key strictly greater than the key in the priority queue");
      keys[i] = key;
      swim(qp[i]);
    }

    /**
     * Increase the key associated with index {@code i} to the specified value.
     *
     * @param i the index of the key to increase
     * @param key increase the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void increaseKey(int i, Key key) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      if (keys[i].compareTo(key) == 0)
        throw new IllegalArgumentException(
            "Calling increaseKey() with a key equal to the key in the priority queue");
      if (keys[i].compareTo(key) > 0)
        throw new IllegalArgumentException(
            "Calling increaseKey() with a key strictly less than the key in the priority queue");
      keys[i] = key;
      sink(qp[i]);
    }

    /**
     * Remove the key associated with index {@code i}.
     *
     * @param i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void delete(int i) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      int index = qp[i];
      exch(index, n--);
      swim(index);
      sink(index);
      keys[i] = null;
      qp[i] = -1;
    }

    // throw an IllegalArgumentException if i is an invalid index
    private void validateIndex(int i) {
      if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
      if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }

    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    private boolean greater(int i, int j) {
      return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
      int swap = pq[i];
      pq[i] = pq[j];
      pq[j] = swap;
      qp[pq[i]] = i;
      qp[pq[j]] = j;
    }

    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    private void swim(int k) {
      while (k > 1 && greater(k / 2, k)) {
        exch(k, k / 2);
        k = k / 2;
      }
    }

    private void sink(int k) {
      while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && greater(j, j + 1)) j++;
        if (!greater(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    /**
     * Returns an iterator that iterates over the keys on the priority queue in ascending order. The
     * iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() {
      return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
      // create a new pq
      private final IndexMinPQ<Key> copy;

      // add all elements to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        copy = new IndexMinPQ<>(pq.length - 1);
        for (int i = 1; i <= n; i++) copy.insert(pq[i], keys[pq[i]]);
      }

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMin();
      }
    }
  }

  public static class DijkstraSP {
    private final double[] distTo; // distTo[v] = distance  of shortest s->v path
    private final DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private final IndexMinPQ<Double> pq; // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other vertex in the
     * edge-weighted digraph {@code G}.
     *
     * @param G the edge-weighted digraph
     * @param s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
      for (DirectedEdge e : G.edges()) {
        if (e.weight() < 0) {
          throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
      }

      distTo = new double[G.V()];
      edgeTo = new DirectedEdge[G.V()];

      validateVertex(s);

      for (int v = 0; v < G.V(); v++) {
        distTo[v] = Double.POSITIVE_INFINITY;
      }
      distTo[s] = 0.0;

      // relax vertices in order of distance from s
      pq = new IndexMinPQ<>(G.V());
      pq.insert(s, distTo[s]);
      while (!pq.isEmpty()) {
        int v = pq.delMin();
        for (DirectedEdge e : G.adj(v)) {
          relax(e);
        }
      }
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
      int v = e.from();
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (pq.contains(w)) {
          pq.decreaseKey(w, distTo[w]);
        } else {
          pq.insert(w, distTo[w]);
        }
      }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *     {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
      validateVertex(v);
      return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path from the source vertex {@code s} to vertex {@code v};
     *     {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
      validateVertex(v);
      return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public ArrayList<DirectedEdge> pathTo(int v) {
      validateVertex(v);
      if (!hasPathTo(v)) {
        return null;
      }
      ArrayList<DirectedEdge> path = new ArrayList<>();
      for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
        path.add(e);
      }
      return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
      int V = distTo.length;
      if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  public ArrayList<Integer> minJumps(ArrayList<Integer> A, int B) {
    EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(A.size());
    int endIndex = A.size() - 1;
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) < 0) {
        continue;
      }
      for (int j = i + 1; j <= i + B; j++) {
        if (j > endIndex) {
          break;
        }
        if (A.get(j) < 0) {
          continue;
        }
        DirectedEdge edge = new DirectedEdge(i, j, A.get(j));
        edgeWeightedDigraph.addEdge(edge);
      }
    }

    DijkstraSP sp = new DijkstraSP(edgeWeightedDigraph, 0);

    ArrayList<Integer> arrayList = new ArrayList<>();
    if (sp.hasPathTo(endIndex)) {
      ArrayList<DirectedEdge> path = sp.pathTo(endIndex);
      for (int i = 0; i < path.size(); i++) {
        DirectedEdge edge = path.get(path.size() - 1 - i);
        // System.out.print(edge + "   ");
        if (i == 0) {
          arrayList.add(edge.from() + 1);
        }
        arrayList.add(edge.to() + 1);
      }
      // System.out.println();
    }

    return arrayList;
  }

  public static void main(String[] args) {
    Integer[] a = {1, 2, 4, -1, 2};
    Integer[] expected = {1, 3, 5};
    runTestCase(a, 2, expected);

    expected = new Integer[0];
    runTestCase(a, 1, expected);
  }

  private static void runTestCase(Integer[] a, int b, Integer[] expected) {
    MinJumps solution = new MinJumps();
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);
    System.out.println("B:" + b);

    ArrayList<Integer> result = solution.minJumps(A, b);
    System.out.println("result: " + result);
    ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(expected));
    if (!result.equals(expectedResult)) {
      throw new AssertionError(
          "result: " + result + " does not equal to expected: " + expectedResult);
    }
  }
}
