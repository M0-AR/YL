package Exercise_28._commonExtra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphEditorPane extends Pane {
  public static final double RADIUS = 20;

  private List<Vertex> vertices = new ArrayList<>();
  private List<Edge1> edges = new ArrayList<>();
  private List<int[]> highlightedEdges = new ArrayList<>();
  private Map<Integer, Color> vertexColors = new HashMap<>();
  private Vertex startVertex;
  private boolean lineOn;
  private double lineX;
  private double lineY;
  private boolean nonrecursiveDFS;

  public GraphEditorPane() {
    setPrefSize(900, 560);
    setOnMouseClicked(e -> handleClick(e.getButton(), e.getX(), e.getY()));
    setOnMouseMoved(e -> {
      if (e.isControlDown()) {
        Vertex vertex = findVertex(e.getX(), e.getY());
        if (vertex != null) {
          vertex.x = e.getX();
          vertex.y = e.getY();
          lineOn = false;
          startVertex = null;
          clearHighlights();
          repaint();
        }
      }
      else if (lineOn) {
        lineX = e.getX();
        lineY = e.getY();
        repaint();
      }
    });
    repaint();
  }

  public void setNonrecursiveDFS(boolean nonrecursiveDFS) {
    this.nonrecursiveDFS = nonrecursiveDFS;
  }

  public int getVertexCount() {
    return vertices.size();
  }

  public int getEdgeCount() {
    return edges.size();
  }

  public List<Integer> displayDFS(int start) {
    clearHighlights();
    if (start < 0 || start >= vertices.size()) {
      repaint();
      return new ArrayList<>();
    }
    UnweightedGraph<Integer> graph = getGraph();
    UnweightedGraph<Integer>.SearchTree tree = graph.dfs(start);
    for (int i = 0; i < graph.getSize(); i++) {
      int parent = tree.getParent(i);
      if (parent != -1) {
        highlightedEdges.add(new int[] {parent, i});
      }
    }
    repaint();
    return tree.getSearchOrder();
  }

  public List<Integer> displayBFS(int start) {
    clearHighlights();
    if (start < 0 || start >= vertices.size()) {
      repaint();
      return new ArrayList<>();
    }
    UnweightedGraph<Integer> graph = getGraph();
    UnweightedGraph<Integer>.SearchTree tree = graph.bfs(start);
    for (int i = 0; i < graph.getSize(); i++) {
      int parent = tree.getParent(i);
      if (parent != -1) {
        highlightedEdges.add(new int[] {parent, i});
      }
    }
    repaint();
    return tree.getSearchOrder();
  }

  public List<Integer> displayShortestPath(int start, int end) {
    clearHighlights();
    if (start < 0 || start >= vertices.size()
        || end < 0 || end >= vertices.size()) {
      repaint();
      return new ArrayList<>();
    }
    List<Integer> path = getGraph().getShortestPath(start, end);
    highlightPath(path);
    repaint();
    return path;
  }

  public List<Integer> displayCycle() {
    clearHighlights();
    List<Integer> cycle = getGraph().getACycle();
    highlightPath(cycle);
    repaint();
    return cycle;
  }

  public List<List<Integer>> displayConnectedComponents() {
    clearHighlights();
    List<List<Integer>> components = getGraph().getConnectedComponents();
    for (int i = 0; i < components.size(); i++) {
      Color color = Color.hsb((i * 67.0) % 360, 0.35, 1.0);
      for (int vertex : components.get(i)) {
        vertexColors.put(vertex, color);
      }
    }
    repaint();
    return components;
  }

  public List<Set<Integer>> displayBipartiteSets() {
    clearHighlights();
    List<Set<Integer>> sets = getGraph().getBipartiteSets();
    if (sets != null) {
      for (int vertex : sets.get(0)) {
        vertexColors.put(vertex, Color.LIGHTBLUE);
      }
      for (int vertex : sets.get(1)) {
        vertexColors.put(vertex, Color.LIGHTPINK);
      }
    }
    repaint();
    return sets;
  }

  public List<Integer> displayHamiltonianPath(int start) {
    clearHighlights();
    if (start < 0 || start >= vertices.size()) {
      repaint();
      return null;
    }
    UnweightedGraphHamiltonianPath<Integer> graph =
        new UnweightedGraphHamiltonianPath<>(buildGraphEdges(), vertices.size());
    List<Integer> path = graph.getHamiltonianPath(start);
    highlightPath(path);
    repaint();
    return path;
  }

  public List<Integer> displayHamiltonianPath() {
    clearHighlights();
    UnweightedGraphHamiltonianPath<Integer> graph =
        new UnweightedGraphHamiltonianPath<>(buildGraphEdges(), vertices.size());
    List<Integer> path = graph.getHamiltonianPath();
    highlightPath(path);
    repaint();
    return path;
  }

  public List<Integer> displayLongestPath(int start) {
    clearHighlights();
    if (start < 0 || start >= vertices.size()) {
      repaint();
      return null;
    }
    UnweightedGraphLongestPath<Integer> graph =
        new UnweightedGraphLongestPath<>(buildGraphEdges(), vertices.size());
    List<Integer> path = graph.getLongestPath(start);
    highlightPath(path);
    repaint();
    return path;
  }

  public List<Integer> displayLongestPath() {
    clearHighlights();
    UnweightedGraphLongestPath<Integer> graph =
        new UnweightedGraphLongestPath<>(buildGraphEdges(), vertices.size());
    List<Integer> path = graph.getLongestPath();
    highlightPath(path);
    repaint();
    return path;
  }

  public void clearHighlights() {
    highlightedEdges.clear();
    vertexColors.clear();
  }

  private void handleClick(MouseButton button, double x, double y) {
    Vertex vertex = findVertex(x, y);

    if (button == MouseButton.SECONDARY) {
      if (vertex != null) {
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.u == vertex || edge.v == vertex);
        lineOn = false;
        startVertex = null;
        clearHighlights();
        repaint();
      }
      return;
    }

    if (button != MouseButton.PRIMARY) {
      return;
    }

    if (vertex == null) {
      if (isTooCloseToVertex(x, y)) {
        return;
      }
      if (lineOn) {
        lineOn = false;
        startVertex = null;
        repaint();
        return;
      }
      vertices.add(new Vertex(x, y));
      clearHighlights();
      repaint();
      return;
    }

    if (!lineOn) {
      startVertex = vertex;
      lineX = x;
      lineY = y;
      lineOn = true;
      repaint();
    }
    else if (vertex != startVertex) {
      if (findEdge(startVertex, vertex) == null) {
        edges.add(new Edge1(startVertex, vertex));
      }
      lineOn = false;
      startVertex = null;
      clearHighlights();
      repaint();
    }
  }

  private boolean isTooCloseToVertex(double x, double y) {
    for (Vertex vertex : vertices) {
      if (Vertex.distance(vertex.x, vertex.y, x, y) < 50) {
        return true;
      }
    }
    return false;
  }

  private Vertex findVertex(double x, double y) {
    for (Vertex vertex : vertices) {
      if (Vertex.distance(vertex.x, vertex.y, x, y) <= RADIUS) {
        return vertex;
      }
    }
    return null;
  }

  private Edge1 findEdge(Vertex u, Vertex v) {
    for (Edge1 edge : edges) {
      if ((edge.u == u && edge.v == v) || (edge.u == v && edge.v == u)) {
        return edge;
      }
    }
    return null;
  }

  private void repaint() {
    getChildren().clear();
    drawInstructions();
    drawEdges();
    drawHighlights();
    drawVertices();
    if (lineOn && startVertex != null) {
      getChildren().add(new Line(startVertex.x, startVertex.y, lineX, lineY));
    }
  }

  private void drawInstructions() {
    String[][] instructions = {
        {"Add a vertex:", "Left click mouse"},
        {"Remove a vertex:", "Right click mouse"},
        {"Move a vertex:", "Mouse move + Ctrl"},
        {"Connect:", "Click vertex, move, click vertex"}
    };
    getChildren().add(new Text(5, 20, "INSTRUCTIONS"));
    for (int i = 0; i < instructions.length; i++) {
      getChildren().add(new Text(5, 40 + i * 15, instructions[i][0]));
      getChildren().add(new Text(140, 40 + i * 15, instructions[i][1]));
    }
  }

  private void drawEdges() {
    for (Edge1 edge : edges) {
      getChildren().add(new Line(edge.u.x, edge.u.y, edge.v.x, edge.v.y));
    }
  }

  private void drawHighlights() {
    for (int[] edge : highlightedEdges) {
      if (edge[0] >= 0 && edge[0] < vertices.size()
          && edge[1] >= 0 && edge[1] < vertices.size()) {
        Vertex u = vertices.get(edge[0]);
        Vertex v = vertices.get(edge[1]);
        drawArrowLine(u.x, u.y, v.x, v.y);
      }
    }
  }

  private void drawVertices() {
    for (int i = 0; i < vertices.size(); i++) {
      Vertex vertex = vertices.get(i);
      Circle circle = new Circle(vertex.x, vertex.y, RADIUS);
      circle.setFill(vertexColors.getOrDefault(i, Color.WHITE));
      circle.setStroke(Color.BLACK);
      Text text = new Text(vertex.x - 4, vertex.y + 5, "" + i);
      getChildren().addAll(circle, text);
    }
  }

  private void drawArrowLine(double x1, double y1, double x2, double y2) {
    double dx = x2 - x1;
    double dy = y2 - y1;
    double distance = Math.hypot(dx, dy);
    if (distance == 0) {
      return;
    }

    double startX = x1 + RADIUS * dx / distance;
    double startY = y1 + RADIUS * dy / distance;
    double endX = x2 - RADIUS * dx / distance;
    double endY = y2 - RADIUS * dy / distance;

    Line line = new Line(startX, startY, endX, endY);
    line.setStroke(Color.RED);
    getChildren().add(line);

    double angle = Math.atan2(endY - startY, endX - startX);
    double length = 12;
    Line arrow1 = new Line(endX, endY,
        endX - length * Math.cos(angle - Math.PI / 6),
        endY - length * Math.sin(angle - Math.PI / 6));
    Line arrow2 = new Line(endX, endY,
        endX - length * Math.cos(angle + Math.PI / 6),
        endY - length * Math.sin(angle + Math.PI / 6));
    arrow1.setStroke(Color.RED);
    arrow2.setStroke(Color.RED);
    getChildren().addAll(arrow1, arrow2);
  }

  private void highlightPath(List<Integer> path) {
    if (path == null) {
      return;
    }
    for (int i = 0; i < path.size() - 1; i++) {
      highlightedEdges.add(new int[] {path.get(i), path.get(i + 1)});
    }
  }

  private List<UnweightedGraph.Edge> buildGraphEdges() {
    List<UnweightedGraph.Edge> result = new ArrayList<>();
    for (Edge1 edge : edges) {
      int u = vertices.indexOf(edge.u);
      int v = vertices.indexOf(edge.v);
      if (u >= 0 && v >= 0) {
        result.add(new UnweightedGraph.Edge(u, v));
        result.add(new UnweightedGraph.Edge(v, u));
      }
    }
    return result;
  }

  private UnweightedGraph<Integer> getGraph() {
    if (nonrecursiveDFS) {
      return new UnweightedGraphWithNonrecursiveDFS<>(
          buildGraphEdges(), vertices.size());
    }
    return new UnweightedGraph<>(buildGraphEdges(), vertices.size());
  }

  private static class Vertex {
    double x;
    double y;

    Vertex(double x, double y) {
      this.x = x;
      this.y = y;
    }

    static double distance(double x1, double y1, double x2, double y2) {
      return Math.hypot(x1 - x2, y1 - y2);
    }
  }

  private static class Edge1 {
    Vertex u;
    Vertex v;

    Edge1(Vertex u, Vertex v) {
      this.u = u;
      this.v = v;
    }
  }
}
