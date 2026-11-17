package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class UnweightedGraph<V> {
  public static class Edge {
    public int u;
    public int v;

    public Edge(int u, int v) {
      this.u = u;
      this.v = v;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Edge)) {
        return false;
      }
      Edge e = (Edge)o;
      return u == e.u && v == e.v;
    }

    @Override
    public int hashCode() {
      return 31 * u + v;
    }

    @Override
    public String toString() {
      return "(" + u + ", " + v + ")";
    }
  }

  protected List<V> vertices = new ArrayList<>();
  protected List<List<Edge>> neighbors = new ArrayList<>();

  public UnweightedGraph() {
  }

  public UnweightedGraph(V[] vertices, int[][] edges) {
    for (V vertex : vertices) {
      addVertex(vertex);
    }
    createAdjacencyLists(edges);
  }

  public UnweightedGraph(List<V> vertices, List<Edge> edges) {
    for (V vertex : vertices) {
      addVertex(vertex);
    }
    createAdjacencyLists(edges);
  }

  @SuppressWarnings("unchecked")
  public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) {
      addVertex((V)Integer.valueOf(i));
    }
    createAdjacencyLists(edges);
  }

  @SuppressWarnings("unchecked")
  public UnweightedGraph(int[][] edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) {
      addVertex((V)Integer.valueOf(i));
    }
    for (int[] edge : edges) {
      addEdge(edge[0], edge[1]);
    }
  }

  private void createAdjacencyLists(int[][] edges) {
    for (int[] edge : edges) {
      addEdge(edge[0], edge[1]);
    }
  }

  private void createAdjacencyLists(List<Edge> edges) {
    for (Edge edge : edges) {
      addEdge(edge);
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

  public List<Integer> getNeighbors(int index) {
    List<Integer> result = new ArrayList<>();
    for (Edge edge : neighbors.get(index)) {
      result.add(edge.v);
    }
    return result;
  }

  public List<Integer> getNeighbors(V vertex) {
    return getNeighbors(getIndex(vertex));
  }

  public int getDegree(int index) {
    return neighbors.get(index).size();
  }

  public List<Edge> getEdges() {
    List<Edge> result = new ArrayList<>();
    for (List<Edge> list : neighbors) {
      result.addAll(list);
    }
    return result;
  }

  public int getNumberOfEdges() {
    int count = 0;
    for (List<Edge> list : neighbors) {
      count += list.size();
    }
    return count;
  }

  public void printEdges() {
    for (int u = 0; u < neighbors.size(); u++) {
      System.out.print(getVertex(u) + " (" + u + "): ");
      for (Edge edge : neighbors.get(u)) {
        System.out.print("(" + getVertex(edge.u) + ", "
            + getVertex(edge.v) + ") ");
      }
      System.out.println();
    }
  }

  public void clear() {
    vertices.clear();
    neighbors.clear();
  }

  public boolean addVertex(V vertex) {
    if (vertices.contains(vertex)) {
      return false;
    }
    vertices.add(vertex);
    neighbors.add(new ArrayList<>());
    return true;
  }

  public boolean addEdge(int u, int v) {
    return addEdge(new Edge(u, v));
  }

  public boolean addEdge(Edge edge) {
    if (edge == null || edge.u < 0 || edge.v < 0
        || edge.u >= getSize() || edge.v >= getSize()) {
      throw new IllegalArgumentException("Invalid edge: " + edge);
    }
    if (neighbors.get(edge.u).contains(edge)) {
      return false;
    }
    neighbors.get(edge.u).add(edge);
    return true;
  }

  public boolean removeEdge(int u, int v) {
    return neighbors.get(u).remove(new Edge(u, v));
  }

  public boolean removeVertex(V vertex) {
    int index = vertices.indexOf(vertex);
    if (index < 0) {
      return false;
    }
    vertices.remove(index);
    neighbors.remove(index);
    for (int i = 0; i < neighbors.size(); i++) {
      List<Edge> list = neighbors.get(i);
      for (int j = 0; j < list.size();) {
        Edge edge = list.get(j);
        if (edge.v == index) {
          list.remove(j);
        }
        else {
          edge.u = i;
          if (edge.v > index) {
            edge.v--;
          }
          j++;
        }
      }
    }
    return true;
  }

  public SearchTree dfs(int start) {
    List<Integer> order = new ArrayList<>();
    int[] parent = new int[getSize()];
    Arrays.fill(parent, -1);
    if (start >= 0 && start < getSize()) {
      boolean[] visited = new boolean[getSize()];
      dfs(start, visited, parent, order);
    }
    return new SearchTree(order, parent);
  }

  public SearchTree dfs(V start) {
    return dfs(getIndex(start));
  }

  protected void dfs(int vertex, boolean[] visited, int[] parent,
      List<Integer> order) {
    visited[vertex] = true;
    order.add(vertex);
    for (Edge edge : neighbors.get(vertex)) {
      if (!visited[edge.v]) {
        parent[edge.v] = vertex;
        dfs(edge.v, visited, parent, order);
      }
    }
  }

  public SearchTree bfs(int start) {
    List<Integer> order = new ArrayList<>();
    int[] parent = new int[getSize()];
    Arrays.fill(parent, -1);
    if (start < 0 || start >= getSize()) {
      return new SearchTree(order, parent);
    }
    boolean[] visited = new boolean[getSize()];
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;
    queue.offer(start);
    while (!queue.isEmpty()) {
      int vertex = queue.poll();
      order.add(vertex);
      for (Edge edge : neighbors.get(vertex)) {
        if (!visited[edge.v]) {
          visited[edge.v] = true;
          parent[edge.v] = vertex;
          queue.offer(edge.v);
        }
      }
    }
    return new SearchTree(order, parent);
  }

  public SearchTree bfs(V start) {
    return bfs(getIndex(start));
  }

  public List<Integer> getShortestPath(int start, int end) {
    return bfs(start).getPath(end);
  }

  public List<List<Integer>> getConnectedComponents() {
    List<List<Integer>> components = new ArrayList<>();
    boolean[] visited = new boolean[getSize()];
    for (int i = 0; i < getSize(); i++) {
      if (!visited[i]) {
        SearchTree tree = bfs(i);
        components.add(tree.getSearchOrder());
        for (int vertex : tree.getSearchOrder()) {
          visited[vertex] = true;
        }
      }
    }
    return components;
  }

  public boolean isCyclic() {
    return getACycle() != null;
  }

  public List<Integer> getACycle() {
    boolean[] visited = new boolean[getSize()];
    boolean[] inStack = new boolean[getSize()];
    int[] parent = new int[getSize()];
    Arrays.fill(parent, -1);
    for (int i = 0; i < getSize(); i++) {
      if (!visited[i]) {
        List<Integer> cycle = new ArrayList<>();
        if (findCycle(i, parent, visited, inStack, cycle)) {
          return cycle;
        }
      }
    }
    return null;
  }

  private boolean findCycle(int vertex, int[] parent, boolean[] visited,
      boolean[] inStack, List<Integer> cycle) {
    visited[vertex] = true;
    inStack[vertex] = true;
    for (Edge edge : neighbors.get(vertex)) {
      int next = edge.v;
      if (!visited[next]) {
        parent[next] = vertex;
        if (findCycle(next, parent, visited, inStack, cycle)) {
          return true;
        }
      }
      else if (inStack[next] && next != parent[vertex]) {
        List<Integer> result = new ArrayList<>();
        int current = vertex;
        while (current != next && current != -1) {
          result.add(current);
          current = parent[current];
        }
        if (current == -1) {
          continue;
        }
        result.add(next);
        Collections.reverse(result);
        result.add(next);
        cycle.addAll(result);
        return true;
      }
    }
    inStack[vertex] = false;
    return false;
  }

  public boolean isBipartite() {
    return getBipartiteSets() != null;
  }

  public List<Set<Integer>> getBipartiteSets() {
    int[] color = new int[getSize()];
    Arrays.fill(color, -1);
    Queue<Integer> queue = new LinkedList<>();
    for (int start = 0; start < getSize(); start++) {
      if (color[start] != -1) {
        continue;
      }
      color[start] = 0;
      queue.offer(start);
      while (!queue.isEmpty()) {
        int vertex = queue.poll();
        for (Edge edge : neighbors.get(vertex)) {
          if (color[edge.v] == -1) {
            color[edge.v] = 1 - color[vertex];
            queue.offer(edge.v);
          }
          else if (color[edge.v] == color[vertex]) {
            return null;
          }
        }
      }
    }
    List<Set<Integer>> sets = new ArrayList<>();
    sets.add(new LinkedHashSet<>());
    sets.add(new LinkedHashSet<>());
    for (int i = 0; i < getSize(); i++) {
      sets.get(color[i]).add(i);
    }
    return sets;
  }

  public class SearchTree {
    protected List<Integer> searchOrder;
    protected int[] parent;
    protected boolean[] discovered;

    protected SearchTree(List<Integer> searchOrder, int[] parent) {
      this.searchOrder = searchOrder;
      this.parent = parent;
      this.discovered = new boolean[parent.length];
      for (int vertex : searchOrder) {
        discovered[vertex] = true;
      }
    }

    public List<Integer> getSearchOrder() {
      return new ArrayList<>(searchOrder);
    }

    public int getNumberOfVerticesFound() {
      return searchOrder.size();
    }

    public int getParent(int index) {
      return parent[index];
    }

    public List<Integer> getPath(int index) {
      List<Integer> path = new ArrayList<>();
      if (index < 0 || index >= parent.length || !discovered[index]) {
        return path;
      }
      for (int vertex = index; vertex != -1; vertex = parent[vertex]) {
        path.add(vertex);
      }
      Collections.reverse(path);
      return path;
    }

    public List<Integer> getPath(V vertex) {
      return getPath(getIndex(vertex));
    }
  }
}
