package Exercise_29._20;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise_29_20 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        List<WeightedGraph.WeightedEdge> mst = getMinimumSpanningTree(graph);
        double total = 0;
        for (WeightedGraph.WeightedEdge edge : mst) {
            total += edge.weight;
        }
        System.out.println("MST total weight: " + total);

        double[] cost = getShortestPathCosts(graph, graph.getIndex(0));
        WeightedGraph<Integer>.ShortestPathTree standard = graph.getShortestPath(0);
        boolean same = true;
        for (int vertex = 0; vertex < graph.getSize(); vertex++) {
            if (cost[vertex] != standard.getCost(vertex)) {
                same = false;
            }
        }
        System.out.println("Shortest path costs match: " + same);
    }

    public static List<WeightedGraph.WeightedEdge> getMinimumSpanningTree(
            WeightedGraph<Integer> graph) {
        int size = graph.getSize();
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        cost[0] = 0;
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
        boolean[] isInT = new boolean[size];
        List<WeightedGraph.WeightedEdge> edges = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int u = -1;
            double smallest = WeightedGraph.INFINITY;
            for (int j = 0; j < size; j++) {
                if (!isInT[j] && cost[j] < smallest) {
                    smallest = cost[j];
                    u = j;
                }
            }
            if (u == -1) {
                break;
            }
            isInT[u] = true;
            if (parent[u] != -1) {
                edges.add(new WeightedGraph.WeightedEdge(parent[u], u, cost[u]));
            }
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                if (!isInT[edge.v] && edge.weight < cost[edge.v]) {
                    cost[edge.v] = edge.weight;
                    parent[edge.v] = u;
                }
            }
        }
        return edges;
    }

    public static double[] getShortestPathCosts(WeightedGraph<Integer> graph, int source) {
        int size = graph.getSize();
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        cost[source] = 0;
        boolean[] isInT = new boolean[size];

        for (int i = 0; i < size; i++) {
            int u = -1;
            double smallest = WeightedGraph.INFINITY;
            for (int j = 0; j < size; j++) {
                if (!isInT[j] && cost[j] < smallest) {
                    smallest = cost[j];
                    u = j;
                }
            }
            if (u == -1) {
                break;
            }
            isInT[u] = true;
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                if (!isInT[edge.v] && cost[u] + edge.weight < cost[edge.v]) {
                    cost[edge.v] = cost[u] + edge.weight;
                }
            }
        }
        return cost;
    }
}
