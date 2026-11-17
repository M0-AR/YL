package Exercise_29._01;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;
import Exercise_29._common.WeightedGraph.WeightedEdge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise_29_01 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        List<WeightedEdge> mst = kruskal(graph);
        double total = 0;
        for (WeightedEdge edge : mst) {
            total += edge.weight;
        }
        System.out.println("Kruskal MST total weight: " + total);
        System.out.println("Edges: " + mst);
    }

    public static List<WeightedEdge> kruskal(WeightedGraph<Integer> graph) {
        List<WeightedEdge> edges = new ArrayList<>();
        for (int u = 0; u < graph.getSize(); u++) {
            for (WeightedEdge edge : graph.getNeighbors(u)) {
                if (u < edge.v) {
                    edges.add(edge);
                }
            }
        }
        edges.sort(Comparator.comparingDouble(edge -> edge.weight));

        int[] parent = new int[graph.getSize()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        List<WeightedEdge> tree = new ArrayList<>();
        for (WeightedEdge edge : edges) {
            int first = find(parent, edge.u);
            int second = find(parent, edge.v);
            if (first != second) {
                parent[first] = second;
                tree.add(edge);
                if (tree.size() == graph.getSize() - 1) {
                    break;
                }
            }
        }
        return tree;
    }

    private static int find(int[] parent, int vertex) {
        while (parent[vertex] != vertex) {
            parent[vertex] = parent[parent[vertex]];
            vertex = parent[vertex];
        }
        return vertex;
    }
}
