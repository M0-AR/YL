package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V> {
  public UnweightedGraphWithNonrecursiveDFS() {
  }

  public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  public UnweightedGraphWithNonrecursiveDFS(List<V> vertices,
      List<Edge> edges) {
    super(vertices, edges);
  }

  public UnweightedGraphWithNonrecursiveDFS(List<Edge> edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public UnweightedGraphWithNonrecursiveDFS(int[][] edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  @Override
  public SearchTree dfs(int start) {
    List<Integer> order = new ArrayList<>();
    int[] parent = new int[getSize()];
    Arrays.fill(parent, -1);
    if (start < 0 || start >= getSize()) {
      return new SearchTree(order, parent);
    }

    boolean[] visited = new boolean[getSize()];
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[] {start, -1});

    while (!stack.isEmpty()) {
      int[] entry = stack.pop();
      int vertex = entry[0];
      if (visited[vertex]) {
        continue;
      }
      visited[vertex] = true;
      parent[vertex] = entry[1];
      order.add(vertex);

      List<Edge> edges = neighbors.get(vertex);
      for (int i = edges.size() - 1; i >= 0; i--) {
        int next = edges.get(i).v;
        if (!visited[next]) {
          stack.push(new int[] {next, vertex});
        }
      }
    }

    return new SearchTree(order, parent);
  }
}
