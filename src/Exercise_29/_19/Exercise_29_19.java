package Exercise_29._19;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;

import java.util.Arrays;

public class Exercise_29_19 {
    public static void main(String[] args) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        Result result = shortestPath(graph, 0);
        WeightedGraph<Integer>.ShortestPathTree standard = graph.getShortestPath(0);
        boolean same = true;
        for (int vertex = 0; vertex < graph.getSize(); vertex++) {
            if (result.cost[vertex] != standard.getCost(vertex)) {
                same = false;
            }
        }
        System.out.println("AVL-based Dijkstra matches standard Dijkstra: " + same);
        for (int vertex = 0; vertex < graph.getSize(); vertex++) {
            System.out.println("Cost to " + vertex + ": " + result.cost[vertex]);
        }
    }

    public static Result shortestPath(WeightedGraph<Integer> graph, int source) {
        int size = graph.getSize();
        double[] cost = new double[size];
        Arrays.fill(cost, WeightedGraph.INFINITY);
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
        boolean[] settled = new boolean[size];
        cost[source] = 0;

        Node root = insert(null, 0, source);
        while (root != null) {
            Node smallest = minimum(root);
            double currentCost = smallest.key;
            int u = smallest.vertex;
            root = deleteMin(root);
            if (settled[u] || currentCost > cost[u]) {
                continue;
            }
            settled[u] = true;
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                int v = edge.v;
                double candidate = currentCost + edge.weight;
                if (!settled[v] && candidate < cost[v]) {
                    cost[v] = candidate;
                    parent[v] = u;
                    root = insert(root, candidate, v);
                }
            }
        }
        return new Result(cost, parent);
    }

    private static class Node {
        double key;
        int vertex;
        int height = 1;
        Node left;
        Node right;

        Node(double key, int vertex) {
            this.key = key;
            this.vertex = vertex;
        }
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private static Node insert(Node node, double key, int vertex) {
        if (node == null) {
            return new Node(key, vertex);
        }
        if (key < node.key) {
            node.left = insert(node.left, key, vertex);
        } else {
            node.right = insert(node.right, key, vertex);
        }
        return balance(node);
    }

    private static Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    private static Node balance(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int difference = height(node.left) - height(node.right);
        if (difference > 1) {
            if (height(node.left.left) < height(node.left.right)) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (difference < -1) {
            if (height(node.right.right) < height(node.right.left)) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private static Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    private static Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
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
