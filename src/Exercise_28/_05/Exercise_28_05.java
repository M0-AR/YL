package Exercise_28._05;

import java.util.List;

public class Exercise_28_05 {
    public static void main(String[] args) {
        int[][] edges = {
            {0, 1}, {0, 2}, {1, 0}, {1, 3}, {2, 0}, {2, 3}, {2, 4},
            {3, 1}, {3, 2}, {3, 5}, {4, 2}, {4, 5}, {5, 3}, {5, 4}
        };
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, 6);
        List<Integer> path = graph.getPath(0, 5);
        System.out.println("Shortest path from 0 to 5: " + path);
        System.out.println("Path from 0 to 0: " + graph.getPath(0, 0));
        System.out.println("Path to a missing vertex: " + graph.getPath(0, 20));
        System.out.println("Path in disconnected graph: "
                + new UnweightedGraph<Integer>(new int[][] {{0, 1}, {1, 0}}, 3).getPath(0, 2));
    }
}
