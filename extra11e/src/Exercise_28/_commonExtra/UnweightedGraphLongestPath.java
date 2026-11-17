package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.List;

public class UnweightedGraphLongestPath<V> extends UnweightedGraph<V> {
  public UnweightedGraphLongestPath() {
  }

  public UnweightedGraphLongestPath(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  public UnweightedGraphLongestPath(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  public UnweightedGraphLongestPath(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public UnweightedGraphLongestPath(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public List<Integer> getLongestPath() {
    List<Integer> result = null;
    for (int i = 0; i < getSize(); i++) {
      List<Integer> path = getLongestPath(i);
      if (result == null || path.size() > result.size()) {
        result = path;
      }
    }
    return result;
  }

  public List<Integer> getLongestPath(V vertex) {
    return getLongestPath(getIndex(vertex));
  }

  public List<Integer> getLongestPath(int start) {
    if (start < 0 || start >= getSize()) {
      return null;
    }
    return longestPath(start, new boolean[getSize()]);
  }

  private List<Integer> longestPath(int vertex, boolean[] visited) {
    visited[vertex] = true;
    List<Integer> result = new ArrayList<>();
    result.add(vertex);

    for (Edge edge : neighbors.get(vertex)) {
      int u = edge.v;
      if (!visited[u]) {
        List<Integer> candidate = longestPath(u, visited);
        if (candidate.size() + 1 > result.size()) {
          result = new ArrayList<>();
          result.add(vertex);
          result.addAll(candidate);
        }
      }
    }

    visited[vertex] = false;
    return result;
  }
}
