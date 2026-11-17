package Exercise_29._commonExtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeightedGraph {
  public static final double INFINITY = Double.POSITIVE_INFINITY;

  private final double[][] weights;

  public WeightedGraph(int size) {
    if (size < 0) {
      throw new IllegalArgumentException("Size cannot be negative");
    }
    weights = new double[size][size];
    for (double[] row : weights) {
      Arrays.fill(row, INFINITY);
    }
    for (int i = 0; i < size; i++) {
      weights[i][i] = 0;
    }
  }

  public int getSize() {
    return weights.length;
  }

  public void addEdge(int u, int v, double weight) {
    if (u < 0 || u >= getSize() || v < 0 || v >= getSize() || u == v) {
      throw new IllegalArgumentException("Invalid edge: (" + u + ", " + v + ")");
    }
    if (weight < 0) {
      throw new IllegalArgumentException("Weight cannot be negative");
    }
    if (weight < weights[u][v]) {
      weights[u][v] = weight;
      weights[v][u] = weight;
    }
  }

  public double getWeight(int u, int v) {
    return weights[u][v];
  }

  public MST getMinimumSpanningTree() {
    int size = getSize();
    double[] cost = new double[size];
    Arrays.fill(cost, INFINITY);
    int[] parent = new int[size];
    Arrays.fill(parent, -1);
    boolean[] inTree = new boolean[size];
    double totalWeight = 0;

    for (int start = 0; start < size; start++) {
      if (inTree[start]) {
        continue;
      }
      cost[start] = 0;
      for (int i = 0; i < size; i++) {
        int u = -1;
        double smallest = INFINITY;
        for (int j = 0; j < size; j++) {
          if (!inTree[j] && cost[j] < smallest) {
            smallest = cost[j];
            u = j;
          }
        }
        if (u == -1) {
          break;
        }
        inTree[u] = true;
        if (parent[u] != -1) {
          totalWeight += cost[u];
        }
        for (int v = 0; v < size; v++) {
          if (!inTree[v] && weights[u][v] < cost[v]) {
            cost[v] = weights[u][v];
            parent[v] = u;
          }
        }
      }
    }

    return new MST(parent, cost, totalWeight);
  }

  public ShortestPathTree getShortestPath(int source) {
    if (source < 0 || source >= getSize()) {
      throw new IllegalArgumentException("Invalid source: " + source);
    }

    int size = getSize();
    double[] cost = new double[size];
    Arrays.fill(cost, INFINITY);
    cost[source] = 0;
    int[] parent = new int[size];
    Arrays.fill(parent, -1);
    boolean[] inTree = new boolean[size];

    for (int i = 0; i < size; i++) {
      int u = -1;
      double smallest = INFINITY;
      for (int j = 0; j < size; j++) {
        if (!inTree[j] && cost[j] < smallest) {
          smallest = cost[j];
          u = j;
        }
      }
      if (u == -1) {
        break;
      }
      inTree[u] = true;
      for (int v = 0; v < size; v++) {
        if (!inTree[v] && weights[u][v] != INFINITY
            && cost[u] + weights[u][v] < cost[v]) {
          cost[v] = cost[u] + weights[u][v];
          parent[v] = u;
        }
      }
    }

    return new ShortestPathTree(source, parent, cost);
  }

  public static class SearchTree {
    private final int source;
    private final int[] parent;
    private final double[] cost;

    private SearchTree(int source, int[] parent, double[] cost) {
      this.source = source;
      this.parent = parent;
      this.cost = cost;
    }

    public int getSize() {
      return parent.length;
    }

    public int getSource() {
      return source;
    }

    public int getParent(int index) {
      return parent[index];
    }

    public double getCost(int index) {
      return cost[index];
    }

    public List<Integer> getPath(int index) {
      List<Integer> path = new ArrayList<>();
      if (index < 0 || index >= parent.length || cost[index] == INFINITY) {
        return path;
      }
      for (int vertex = index; vertex != -1; vertex = parent[vertex]) {
        path.add(vertex);
      }
      Collections.reverse(path);
      return path;
    }
  }

  public static class ShortestPathTree extends SearchTree {
    private ShortestPathTree(int source, int[] parent, double[] cost) {
      super(source, parent, cost);
    }
  }

  public static class MST extends SearchTree {
    private final double totalWeight;

    private MST(int[] parent, double[] cost, double totalWeight) {
      super(-1, parent, cost);
      this.totalWeight = totalWeight;
    }

    public double getTotalWeight() {
      return totalWeight;
    }
  }
}
