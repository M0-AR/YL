package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnweightedGraphTraverseEdges<V> extends UnweightedGraph<V> {
  public UnweightedGraphTraverseEdges() {
  }

  public UnweightedGraphTraverseEdges(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  public UnweightedGraphTraverseEdges(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  public UnweightedGraphTraverseEdges(List<Edge> edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public UnweightedGraphTraverseEdges(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public List<Edge> traverseEdges() {
    int edgeCount = getNumberOfEdges();
    if (edgeCount == 0) {
      return new ArrayList<>();
    }

    List<Integer> oddVertices = new ArrayList<>();
    int start = -1;
    for (int i = 0; i < getSize(); i++) {
      int degree = getDegree(i);
      if (degree % 2 != 0) {
        oddVertices.add(i);
      }
      else if (degree > 0 && start == -1) {
        start = i;
      }
    }

    if (oddVertices.size() != 0 && oddVertices.size() != 2) {
      return null;
    }
    if (oddVertices.size() == 2) {
      start = oddVertices.get(0);
    }
    if (start == -1) {
      return null;
    }
    if (!isConnected(start)) {
      return null;
    }

    boolean[][] used = new boolean[getSize()][];
    for (int i = 0; i < getSize(); i++) {
      used[i] = new boolean[neighbors.get(i).size()];
    }

    LinkedList<Integer> stack = new LinkedList<>();
    List<Integer> circuit = new ArrayList<>();
    stack.push(start);
    while (!stack.isEmpty()) {
      int vertex = stack.peek();
      int edgeIndex = nextUnused(vertex, used);
      if (edgeIndex == -1) {
        circuit.add(stack.pop());
      }
      else {
        Edge edge = neighbors.get(vertex).get(edgeIndex);
        used[vertex][edgeIndex] = true;
        markReverse(edge.v, vertex, used);
        stack.push(edge.v);
      }
    }

    Collections.reverse(circuit);
    if (circuit.size() != edgeCount / 2 + 1) {
      return null;
    }

    List<Edge> result = new ArrayList<>();
    for (int i = 0; i < circuit.size() - 1; i++) {
      result.add(new Edge(circuit.get(i), circuit.get(i + 1)));
    }
    return result;
  }

  private int nextUnused(int vertex, boolean[][] used) {
    for (int i = 0; i < used[vertex].length; i++) {
      if (!used[vertex][i]) {
        return i;
      }
    }
    return -1;
  }

  private void markReverse(int u, int v, boolean[][] used) {
    List<Edge> list = neighbors.get(u);
    for (int i = 0; i < list.size(); i++) {
      if (!used[u][i] && list.get(i).v == v) {
        used[u][i] = true;
        return;
      }
    }
  }

  private boolean isConnected(int start) {
    boolean[] visited = new boolean[getSize()];
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;
    queue.offer(start);
    while (!queue.isEmpty()) {
      int vertex = queue.poll();
      for (Edge edge : neighbors.get(vertex)) {
        if (!visited[edge.v]) {
          visited[edge.v] = true;
          queue.offer(edge.v);
        }
      }
    }
    for (int i = 0; i < getSize(); i++) {
      if (getDegree(i) > 0 && !visited[i]) {
        return false;
      }
    }
    return true;
  }
}
