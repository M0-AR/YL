package Exercise_28._16Extra;

import Exercise_28._commonExtra.UnweightedGraphTraverseEdges;

public class Exercise_28_16Extra {
  public static void main(String[] args) {
    String[] vertices = {"A", "B", "C", "D", "E"};
    int[][] edges = {
        {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0},
        {1, 0}, {2, 1}, {3, 2}, {4, 3}, {0, 4}
    };

    UnweightedGraphTraverseEdges<String> graph =
        new UnweightedGraphTraverseEdges<>(vertices, edges);
    System.out.println("Traverse edges: " + graph.traverseEdges());
  }
}
