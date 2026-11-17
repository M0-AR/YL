package Exercise_29._common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WeightedGraph<V> {
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    protected List<V> vertices = new ArrayList<>();
    protected List<List<WeightedEdge>> neighbors = new ArrayList<>();

    public WeightedGraph() {
    }

    public WeightedGraph(V[] vertices, int[][] edges) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }

    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
        for (WeightedEdge edge : edges) {
            addEdge(edge.u, edge.v, edge.weight);
        }
    }

    @SuppressWarnings("unchecked")
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) Integer.valueOf(i));
        }
        for (WeightedEdge edge : edges) {
            addEdge(edge.u, edge.v, edge.weight);
        }
    }

    @SuppressWarnings("unchecked")
    public WeightedGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) Integer.valueOf(i));
        }
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }

    public int getSize() {
        return vertices.size();
    }

    public List<V> getVertices() {
        return vertices;
    }

    public V getVertex(int index) {
        return vertices.get(index);
    }

    public int getIndex(V vertex) {
        return vertices.indexOf(vertex);
    }

    public List<WeightedEdge> getNeighbors(int index) {
        return neighbors.get(index);
    }

    public int getDegree(int index) {
        return neighbors.get(index).size();
    }

    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (WeightedEdge edge : neighbors.get(u)) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u, v, weight));
    }

    public boolean addEdge(WeightedEdge edge) {
        if (edge.u < 0 || edge.u >= getSize() || edge.v < 0 || edge.v >= getSize()) {
            throw new IllegalArgumentException("No such index: " + edge.u + " or " + edge.v);
        }
        if (!neighbors.get(edge.u).contains(edge)) {
            neighbors.get(edge.u).add(edge);
            return true;
        }
        return false;
    }

    public MST getMinimumSpanningTree() {
        return getMinimumSpanningTree(0);
    }

    public MST getMinimumSpanningTree(int startingVertex) {
        int numberOfVertices = getSize();
        double[] cost = new double[numberOfVertices];
        Arrays.fill(cost, INFINITY);
        cost[startingVertex] = 0;
        int[] parent = new int[numberOfVertices];
        Arrays.fill(parent, -1);
        boolean[] isInT = new boolean[numberOfVertices];
        List<WeightedEdge> edges = new ArrayList<>();
        double totalWeight = 0;

        for (int i = 0; i < numberOfVertices; i++) {
            int u = -1;
            double smallest = INFINITY;
            for (int j = 0; j < numberOfVertices; j++) {
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
                totalWeight += cost[u];
            }
            for (WeightedEdge edge : neighbors.get(u)) {
                if (!isInT[edge.v] && edge.weight < cost[edge.v]) {
                    cost[edge.v] = edge.weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new MST(edges, totalWeight);
    }

    public ShortestPathTree getShortestPath(int sourceVertex) {
        int numberOfVertices = getSize();
        double[] cost = new double[numberOfVertices];
        Arrays.fill(cost, INFINITY);
        cost[sourceVertex] = 0;
        int[] parent = new int[numberOfVertices];
        Arrays.fill(parent, -1);
        boolean[] isInT = new boolean[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            int u = -1;
            double smallest = INFINITY;
            for (int j = 0; j < numberOfVertices; j++) {
                if (!isInT[j] && cost[j] < smallest) {
                    smallest = cost[j];
                    u = j;
                }
            }
            if (u == -1) {
                break;
            }
            isInT[u] = true;
            for (WeightedEdge edge : neighbors.get(u)) {
                if (!isInT[edge.v] && cost[u] + edge.weight < cost[edge.v]) {
                    cost[edge.v] = cost[u] + edge.weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(sourceVertex, parent, cost);
    }

    public List<Integer> getShortestHamiltonianCycle() {
        int size = getSize();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return new ArrayList<>(List.of(0, 0));
        }
        double[][] weight = new double[size][size];
        for (double[] row : weight) {
            Arrays.fill(row, INFINITY);
        }
        for (int u = 0; u < size; u++) {
            for (WeightedEdge edge : neighbors.get(u)) {
                weight[u][edge.v] = Math.min(weight[u][edge.v], edge.weight);
            }
        }
        int states = 1 << size;
        double[][] cost = new double[size][states];
        int[][] parent = new int[size][states];
        for (double[] row : cost) {
            Arrays.fill(row, INFINITY);
        }
        for (int[] row : parent) {
            Arrays.fill(row, -1);
        }
        cost[0][1] = 0;
        for (int mask = 1; mask < states; mask++) {
            for (int last = 0; last < size; last++) {
                if ((mask & (1 << last)) == 0 || cost[last][mask] == INFINITY) {
                    continue;
                }
                for (int next = 0; next < size; next++) {
                    if ((mask & (1 << next)) != 0 || weight[last][next] == INFINITY) {
                        continue;
                    }
                    int nextMask = mask | (1 << next);
                    double candidate = cost[last][mask] + weight[last][next];
                    if (candidate < cost[next][nextMask]) {
                        cost[next][nextMask] = candidate;
                        parent[next][nextMask] = last;
                    }
                }
            }
        }
        int full = states - 1;
        int bestLast = -1;
        double best = INFINITY;
        for (int last = 1; last < size; last++) {
            if (cost[last][full] != INFINITY && weight[last][0] != INFINITY
                    && cost[last][full] + weight[last][0] < best) {
                best = cost[last][full] + weight[last][0];
                bestLast = last;
            }
        }
        if (bestLast == -1) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int mask = full;
        int last = bestLast;
        while (last != -1) {
            path.addFirst(last);
            int previous = parent[last][mask];
            mask ^= 1 << last;
            last = previous;
        }
        path.addLast(0);
        return path;
    }

    public List<Integer> getHamiltonianCycle() {
        List<Integer> path = new ArrayList<>();
        boolean[] isVisited = new boolean[getSize()];
        if (getSize() == 0) {
            return null;
        }
        path.add(0);
        isVisited[0] = true;
        if (hasHamiltonianCycle(0, path, isVisited)) {
            return path;
        }
        return null;
    }

    private boolean hasHamiltonianCycle(int current, List<Integer> path, boolean[] isVisited) {
        if (path.size() == getSize()) {
            for (WeightedEdge edge : neighbors.get(current)) {
                if (edge.v == path.get(0)) {
                    return true;
                }
            }
            return false;
        }
        for (WeightedEdge edge : neighbors.get(current)) {
            if (!isVisited[edge.v]) {
                isVisited[edge.v] = true;
                path.add(edge.v);
                if (hasHamiltonianCycle(edge.v, path, isVisited)) {
                    return true;
                }
                path.remove(path.size() - 1);
                isVisited[edge.v] = false;
            }
        }
        return false;
    }

    public static class WeightedEdge {
        public int u;
        public int v;
        public double weight;

        public WeightedEdge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof WeightedEdge other)) {
                return false;
            }
            return u == other.u && v == other.v;
        }

        @Override
        public int hashCode() {
            return 31 * u + v;
        }

        @Override
        public String toString() {
            return "(" + u + ", " + v + ", " + weight + ")";
        }
    }

    public class MST {
        private final List<WeightedEdge> edges;
        private final double totalWeight;

        public MST(List<WeightedEdge> edges, double totalWeight) {
            this.edges = edges;
            this.totalWeight = totalWeight;
        }

        public List<WeightedEdge> getEdges() {
            return edges;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public void printTree() {
            System.out.print("Edges: ");
            for (WeightedEdge edge : edges) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public class ShortestPathTree {
        private final int root;
        private final int[] parent;
        private final double[] cost;

        public ShortestPathTree(int root, int[] parent, double[] cost) {
            this.root = root;
            this.parent = parent;
            this.cost = cost;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int vertex) {
            return parent[vertex];
        }

        public double getCost(int vertex) {
            return cost[vertex];
        }

        public boolean isReachable(int vertex) {
            return cost[vertex] != INFINITY;
        }

        public List<Integer> getPath(int vertex) {
            if (cost[vertex] == INFINITY) {
                return null;
            }
            LinkedList<Integer> path = new LinkedList<>();
            for (int current = vertex; current != -1; current = parent[current]) {
                path.addFirst(current);
            }
            return path;
        }

        public void printAllPaths() {
            for (int i = 0; i < getSize(); i++) {
                List<Integer> path = getPath(i);
                if (path != null) {
                    System.out.println("Path to " + i + ": " + path + " cost = " + cost[i]);
                }
            }
        }
    }
}
