package Exercise_29._18;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;

import java.util.Arrays;

public class Exercise_29_18 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        Result alternative = shortestPath(graph, 0);
        WeightedGraph<Integer>.ShortestPathTree standard = graph.getShortestPath(0);
        boolean same = true;
        for (int vertex = 0; vertex < graph.getSize(); vertex++) {
            if (alternative.cost[vertex] != standard.getCost(vertex)) {
                same = false;
                System.out.println("Mismatch at " + vertex + ": "
                        + alternative.cost[vertex] + " vs " + standard.getCost(vertex));
            }
        }
        System.out.println("Alternative Dijkstra costs match: " + same);
        for (int vertex = 0; vertex < graph.getSize(); vertex++) {
            System.out.println("Cost to " + vertex + ": " + alternative.cost[vertex]);
        }
    }

    public static Result shortestPath(WeightedGraph<Integer> graph, int source) {
        int size = graph.getSize();
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
        boolean[] isInT = new boolean[size];
        cost[source] = 0;
        isInT[source] = true;
        int inT = 1;

        while (inT < size) {
            int bestU = -1;
            int bestV = -1;
            double best = WeightedGraph.INFINITY;
            for (int u = 0; u < size; u++) {
                if (!isInT[u]) {
                    continue;
                }
                for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                    int v = edge.v;
                    if (!isInT[v] && cost[u] + edge.weight < best) {
                        best = cost[u] + edge.weight;
                        bestU = u;
                        bestV = v;
                    }
                }
            }
            if (bestV == -1) {
                break;
            }
            isInT[bestV] = true;
            cost[bestV] = best;
            parent[bestV] = bestU;
            inT++;
        }
        return new Result(cost, parent);
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
