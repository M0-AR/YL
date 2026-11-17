package Exercise_29._02;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;
import Exercise_29._common.WeightedGraph.WeightedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise_29_02 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        double[][] matrix = SampleGraphs.adjacencyMatrix(graph);
        List<WeightedEdge> mst = prim(matrix);
        double total = 0;
        for (WeightedEdge edge : mst) {
            total += edge.weight;
        }
        System.out.println("Prim (adjacency matrix) total weight: " + total);
        System.out.println("Edges: " + mst);
    }

    public static List<WeightedEdge> prim(double[][] matrix) {
        int size = matrix.length;
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        cost[0] = 0;
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
        boolean[] isInT = new boolean[size];
        List<WeightedEdge> edges = new ArrayList<>();

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
                edges.add(new WeightedEdge(parent[u], u, cost[u]));
            }
            for (int v = 0; v < size; v++) {
                if (!isInT[v] && matrix[u][v] < cost[v]) {
                    cost[v] = matrix[u][v];
                    parent[v] = u;
                }
            }
        }
        return edges;
    }
}
