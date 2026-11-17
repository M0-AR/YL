package Exercise_29._03;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;

import java.util.Arrays;

public class Exercise_29_03 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        double[][] matrix = SampleGraphs.adjacencyMatrix(graph);
        Result result = dijkstra(matrix, 0);
        for (int vertex = 0; vertex < matrix.length; vertex++) {
            System.out.println("Cost to " + vertex + ": " + result.cost[vertex]
                    + " path: " + path(result.parent, vertex));
        }
    }

    public static Result dijkstra(double[][] matrix, int source) {
        int size = matrix.length;
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        cost[source] = 0;
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
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
            for (int v = 0; v < size; v++) {
                if (!isInT[v] && matrix[u][v] != WeightedGraph.INFINITY
                        && cost[u] + matrix[u][v] < cost[v]) {
                    cost[v] = cost[u] + matrix[u][v];
                    parent[v] = u;
                }
            }
        }
        return new Result(cost, parent);
    }

    private static String path(int[] parent, int vertex) {
        if (parent[vertex] == -1 && vertex != 0) {
            return "none";
        }
        StringBuilder text = new StringBuilder();
        path(parent, vertex, text);
        return text.toString().trim();
    }

    private static void path(int[] parent, int vertex, StringBuilder text) {
        if (vertex == -1) {
            return;
        }
        path(parent, parent[vertex], text);
        text.append(vertex).append(' ');
    }

    public static class Result {
        public final double[] cost;
        public final int[] parent;

        public Result(double[] cost, int[] parent) {
            this.cost = cost;
            this.parent = parent;
        }
    }
}
