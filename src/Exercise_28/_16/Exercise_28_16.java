package Exercise_28._16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_28_16 {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Graph maxInducedSubgraph(Graph graph, int k) {
        int size = graph.getSize();
        boolean[] removed = new boolean[size];
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int u = 0; u < size; u++) {
                if (removed[u]) {
                    continue;
                }
                int degree = 0;
                for (int v : (List<Integer>) graph.getNeighbors(u)) {
                    if (!removed[v]) {
                        degree++;
                    }
                }
                if (degree < k) {
                    removed[u] = true;
                    changed = true;
                }
            }
        }

        List<Integer> kept = new ArrayList<>();
        Map<Integer, Integer> newIndex = new HashMap<>();
        for (int u = 0; u < size; u++) {
            if (!removed[u]) {
                newIndex.put(u, kept.size());
                kept.add(u);
            }
        }
        if (kept.isEmpty()) {
            return null;
        }

        List<AbstractGraph.Edge> edges = new ArrayList<>();
        for (int u : kept) {
            for (int v : (List<Integer>) graph.getNeighbors(u)) {
                if (!removed[v]) {
                    edges.add(new AbstractGraph.Edge(newIndex.get(u), newIndex.get(v)));
                }
            }
        }
        return new UnweightedGraph<>(edges, kept.size());
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1}, {0, 2}, {0, 3},
            {1, 0}, {1, 2},
            {2, 0}, {2, 1},
            {3, 0}
        };
        Graph<Integer> graph = new UnweightedGraph<>(edges, 4);
        Graph<Integer> result = maxInducedSubgraph(graph, 2);
        System.out.println("Vertices with degree at least 2: " + result.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            System.out.println("Vertex " + i + ": " + result.getNeighbors(i));
        }
        System.out.println("No subgraph for k = 3: " + (maxInducedSubgraph(graph, 3) == null));
    }
}
