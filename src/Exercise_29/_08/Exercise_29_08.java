package Exercise_29._08;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;

import java.util.List;

public class Exercise_29_08 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.sixVertexGraph();
        List<Integer> cycle = graph.getShortestHamiltonianCycle();
        System.out.println("Shortest round trip: " + cycle);
        System.out.println("Cost: " + cost(graph, cycle));

        WeightedGraph<Integer> graph1 = SampleGraphs.graph1();
        List<Integer> cycle1 = graph1.getShortestHamiltonianCycle();
        System.out.println("Graph1 trip: " + cycle1);
        System.out.println("Graph1 cost: " + cost(graph1, cycle1));
    }

    private static double cost(WeightedGraph<Integer> graph, List<Integer> cycle) {
        if (cycle == null) {
            return Double.POSITIVE_INFINITY;
        }
        double total = 0;
        for (int i = 0; i + 1 < cycle.size(); i++) {
            total += edgeWeight(graph, cycle.get(i), cycle.get(i + 1));
        }
        return total;
    }

    private static double edgeWeight(WeightedGraph<Integer> graph, int u, int v) {
        for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
            if (edge.v == v) {
                return edge.weight;
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
