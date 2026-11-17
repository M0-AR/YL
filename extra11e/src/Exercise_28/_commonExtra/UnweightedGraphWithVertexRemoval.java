package Exercise_28._commonExtra;

import java.util.List;

public class UnweightedGraphWithVertexRemoval<V> extends UnweightedGraph<V> {
  public UnweightedGraphWithVertexRemoval() {
  }

  public UnweightedGraphWithVertexRemoval(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  public UnweightedGraphWithVertexRemoval(List<V> vertices,
      List<Edge> edges) {
    super(vertices, edges);
  }

  public UnweightedGraphWithVertexRemoval(List<Edge> edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  public UnweightedGraphWithVertexRemoval(int[][] edges,
      int numberOfVertices) {
    super(edges, numberOfVertices);
  }
}
