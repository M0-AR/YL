package atlas.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public final class WeightedGraph {
    public record Edge(int to, int weight) {
    }

    private final List<List<Edge>> adjacency;

    public WeightedGraph(int nodes) {
        adjacency = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) adjacency.add(new ArrayList<>());
    }

    public void addDirectedEdge(int from, int to, int weight) {
        if (weight < 0) throw new IllegalArgumentException("negative weight");
        adjacency.get(from).add(new Edge(to, weight));
    }

    public void addUndirectedEdge(int first, int second, int weight) {
        addDirectedEdge(first, second, weight);
        addDirectedEdge(second, first, weight);
    }

    public List<Edge> edges(int node) {
        return List.copyOf(adjacency.get(node));
    }

    public int[] dijkstra(int source) {
        int[] distance = new int[adjacency.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            Comparator.comparingInt(row -> row[1]));
        queue.add(new int[]{source, 0});

        while (!queue.isEmpty()) {
            int[] row = queue.poll();
            int node = row[0];
            int current = row[1];
            if (current != distance[node]) continue;

            for (Edge edge : adjacency.get(node)) {
                int candidate = current + edge.weight();
                if (candidate < distance[edge.to()]) {
                    distance[edge.to()] = candidate;
                    queue.add(new int[]{edge.to(), candidate});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(6);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 2, 1);
        graph.addUndirectedEdge(2, 1, 2);
        graph.addUndirectedEdge(1, 3, 5);
        graph.addUndirectedEdge(2, 3, 8);
        graph.addUndirectedEdge(3, 4, 3);
        graph.addUndirectedEdge(4, 5, 2);
        System.out.println(Arrays.toString(graph.dijkstra(0)));
    }
}
