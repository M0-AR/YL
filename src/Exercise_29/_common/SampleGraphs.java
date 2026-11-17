package Exercise_29._common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class SampleGraphs {
    private SampleGraphs() {
    }

    public static WeightedGraph<Integer> graph1() {
        int[][] edges = {
            {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097}, {1, 2, 381},
            {1, 3, 1267}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
            {3, 4, 599}, {3, 5, 1003}, {4, 5, 533}, {4, 7, 1260},
            {4, 8, 864}, {4, 10, 496}, {5, 6, 983}, {5, 7, 787},
            {6, 7, 214}, {7, 8, 888}, {8, 9, 661}, {8, 10, 781},
            {8, 11, 810}, {9, 11, 1187}, {10, 11, 239}
        };
        List<int[]> both = new ArrayList<>();
        for (int[] edge : edges) {
            both.add(edge);
            both.add(new int[] {edge[1], edge[0], edge[2]});
        }
        return new WeightedGraph<>(both.toArray(new int[0][]), 12);
    }

    public static WeightedGraph<Integer> sixVertexGraph() {
        int[][] edges = {
            {0, 1, 100}, {0, 2, 3}, {1, 3, 20}, {2, 3, 40},
            {2, 4, 2}, {3, 4, 5}, {3, 5, 5}, {4, 5, 9}
        };
        List<int[]> both = new ArrayList<>();
        for (int[] edge : edges) {
            both.add(edge);
            both.add(new int[] {edge[1], edge[0], edge[2]});
        }
        return new WeightedGraph<>(both.toArray(new int[0][]), 6);
    }

    public static double[][] adjacencyMatrix(WeightedGraph<Integer> graph) {
        int size = graph.getSize();
        double[][] matrix = new double[size][size];
        for (double[] row : matrix) {
            java.util.Arrays.fill(row, WeightedGraph.INFINITY);
        }
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 0;
        }
        for (int u = 0; u < size; u++) {
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                matrix[edge.u][edge.v] = edge.weight;
            }
        }
        return matrix;
    }

    public static Map<Integer, double[]> cityPositions() {
        Map<Integer, double[]> positions = new LinkedHashMap<>();
        double[][] points = {
            {70, 90}, {65, 240}, {115, 330}, {270, 240}, {390, 255},
            {485, 170}, {660, 120}, {625, 185}, {555, 305}, {625, 400},
            {380, 360}, {380, 410}
        };
        for (int i = 0; i < points.length; i++) {
            positions.put(i, points[i]);
        }
        return positions;
    }

    public static String[] cityNames() {
        return new String[] {
            "Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City",
            "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"
        };
    }
}
