package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph implements GraphADT<Integer> {
  private int V; // number of vertices
  private List<Integer>[] adj; // adjacency list

  @SuppressWarnings("unchecked")
  public Graph(int v) {
    this.V = v;
    adj = new ArrayList[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new ArrayList<>();
    }
  }

  @Override
  public void addVertex(Integer vertex) {
    if (vertex >= V) {
      List<Integer>[] newAdj = new ArrayList[vertex + 1];
      System.arraycopy(adj, 0, newAdj, 0, V);
      for (int i = V; i <= vertex; i++) {
        newAdj[i] = new ArrayList<>();
      }
      adj = newAdj;
      V = vertex + 1;
    }
  }

  @Override
  public void removeVertex(Integer vertex) {
    if (vertex < V) {
      adj[vertex].clear();
      for (List<Integer> edges : adj) {
        if (edges.contains(vertex)) {
          edges.remove(Integer.valueOf(vertex));
        }
      }
    }
  }

  @Override
  public void addEdge(Integer vertex1, Integer vertex2) {
    addVertex(Math.max(vertex1, vertex2));
    adj[vertex1].add(vertex2);
  }

  @Override
  public void removeEdge(Integer vertex1, Integer vertex2) {
    if (vertex1 < V && vertex2 < V) {
      adj[vertex1].remove(Integer.valueOf(vertex2));
    }
  }

  @Override
  public boolean hasEdge(Integer vertex1, Integer vertex2) {
    return vertex1 < V && vertex2 < V && adj[vertex1].contains(vertex2);
  }

  @Override
  public int size() {
    return V;
  }

  @Override
  public boolean isEmpty() {
    return V == 0;
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < V;
      }

      @Override
      public Integer next() {
        return current++;
      }
    };
  }

  public void depthFirstSearch(int v) {
    boolean[] visited = new boolean[V];
    depthFirstSearchUtil(v, visited);
  }

  private void depthFirstSearchUtil(int v, boolean[] visited) {
    visited[v] = true;
    System.out.print(v + " ");

    for (int n : adj[v]) {
      if (!visited[n]) {
        depthFirstSearchUtil(n, visited);
      }
    }
  }

  public void depthFirstSearchAll() {
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        depthFirstSearchUtil(i, visited);
      }
    }
  }

  public void printGraph() {
    for (int i = 0; i < V; i++) {
      System.out.print(i + ": ");
      for (int w : adj[i]) {
        System.out.print(w + " ");
      }
      System.out.println();
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Graph graph = new Graph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 3);

    System.out.println("Graph structure:");
    graph.printGraph();

    // System.out.println("\nDepth First Search (starting from vertex 2):");
    // graph.depthFirstSearch(2);

    System.out.println("\n\nDepth First Search for all vertices:");
    graph.depthFirstSearchAll();

    // System.out.println("\n\nRemoving edge 1-2");
    // graph.removeEdge(1, 2);
    // graph.printGraph();

    // System.out.println("\nGraph size: " + graph.size());
    // System.out.println("Has edge 0-1: " + graph.hasEdge(0, 1));
    // System.out.println("Has edge 1-2: " + graph.hasEdge(1, 2));
  }
}