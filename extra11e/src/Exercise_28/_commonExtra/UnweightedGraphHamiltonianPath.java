package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnweightedGraphHamiltonianPath<V> extends UnweightedGraph<V> {
  public UnweightedGraphHamiltonianPath() {
  }

  public UnweightedGraphHamiltonianPath(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  public UnweightedGraphHamiltonianPath(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  public UnweightedGraphHamiltonianPath(List<Edge> edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public UnweightedGraphHamiltonianPath(int[][] edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public List<Integer> getHamiltonianPath() {
    for (int i = 0; i < getSize(); i++) {
      List<Integer> path = getHamiltonianPath(i);
      if (path != null) {
        return path;
      }
    }
    return null;
  }

  public List<Integer> getHamiltonianPath(V vertex) {
    return getHamiltonianPath(getIndex(vertex));
  }

  public List<Integer> getHamiltonianPath(int start) {
    if (start < 0 || start >= getSize()) {
      return null;
    }
    int[] next = new int[getSize()];
    Arrays.fill(next, -1);
    boolean[] visited = new boolean[getSize()];
    if (!findPath(start, next, visited, 1)) {
      return null;
    }
    List<Integer> path = new ArrayList<>();
    for (int vertex = start; vertex != -1; vertex = next[vertex]) {
      path.add(vertex);
    }
    return path;
  }

  private boolean findPath(int vertex, int[] next, boolean[] visited,
      int count) {
    visited[vertex] = true;
    if (count == getSize()) {
      return true;
    }
    for (Edge edge : neighbors.get(vertex)) {
      int u = edge.v;
      if (!visited[u] && findPath(u, next, visited, count + 1)) {
        next[vertex] = u;
        return true;
      }
    }
    visited[vertex] = false;
    return false;
  }
}
