package Exercise_29._01Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_29_01Extra extends Application {
  private GraphPane graphPane = new GraphPane();
  private TextField tfName = new TextField();
  private TextField tfX = new TextField();
  private TextField tfY = new TextField();
  private TextField tfEdgeFrom = new TextField();
  private TextField tfEdgeTo = new TextField();
  private TextField tfSource = new TextField();
  private TextField tfEnd = new TextField();
  private Label lblStatus = new Label("Add vertices, edges, and a shortest path.");

  @Override
  public void start(Stage primaryStage) {
    Button btAddVertex = new Button("Add Vertex");
    Button btAddEdge = new Button("Add Edge");
    Button btShortestPath = new Button("Show Shortest Path");

    VBox controls = new VBox(5,
        new Label("Vertex name:"), tfName,
        new Label("x:"), tfX,
        new Label("y:"), tfY,
        btAddVertex,
        new Label("Edge from:"), tfEdgeFrom,
        new Label("to:"), tfEdgeTo,
        btAddEdge,
        new Label("Source:"), tfSource,
        new Label("Ending:"), tfEnd,
        btShortestPath,
        lblStatus);
    controls.setPadding(new Insets(10));
    controls.setPrefWidth(190);

    BorderPane pane = new BorderPane();
    pane.setLeft(controls);
    pane.setCenter(graphPane);

    btAddVertex.setOnAction(e -> addVertex());
    btAddEdge.setOnAction(e -> addEdge());
    btShortestPath.setOnAction(e -> showShortestPath());

    Scene scene = new Scene(pane, 1000, 650);
    primaryStage.setTitle("Exercise29_01");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void addVertex() {
    try {
      double x = Double.parseDouble(tfX.getText().trim());
      double y = Double.parseDouble(tfY.getText().trim());
      String name = tfName.getText().trim();
      graphPane.addVertex(name, x, y);
      lblStatus.setText("Added vertex " + (graphPane.getVertexCount() - 1));
      tfX.clear();
      tfY.clear();
      tfName.clear();
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter numeric coordinates.");
    }
  }

  private void addEdge() {
    try {
      int u = Integer.parseInt(tfEdgeFrom.getText().trim());
      int v = Integer.parseInt(tfEdgeTo.getText().trim());
      if (graphPane.addEdge(u, v)) {
        lblStatus.setText("Added edge (" + u + ", " + v + ")");
      }
      else {
        lblStatus.setText("Enter two distinct valid vertex indices.");
      }
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter numeric vertex indices.");
    }
  }

  private void showShortestPath() {
    try {
      int source = Integer.parseInt(tfSource.getText().trim());
      int end = Integer.parseInt(tfEnd.getText().trim());
      List<Integer> path = graphPane.showShortestPath(source, end);
      if (path == null || path.isEmpty()) {
        lblStatus.setText("No path exists.");
      }
      else {
        lblStatus.setText("Shortest path: " + path
            + " (cost " + graphPane.getPathCost(source, end) + ")");
      }
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter numeric vertex indices.");
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static class GraphPane extends Pane {
    private static final double RADIUS = 20;
    private List<Vertex> vertices = new ArrayList<>();
    private List<WeightedEdge> edges = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    GraphPane() {
      setPrefSize(760, 600);
    }

    int getVertexCount() {
      return vertices.size();
    }

    void addVertex(String name, double x, double y) {
      String label = name.isEmpty() ? "" + vertices.size() : name;
      vertices.add(new Vertex(label, x, y));
      path.clear();
      repaint();
    }

    boolean addEdge(int u, int v) {
      if (u < 0 || u >= vertices.size() || v < 0 || v >= vertices.size()
          || u == v || findEdge(u, v) != null) {
        return false;
      }
      Vertex a = vertices.get(u);
      Vertex b = vertices.get(v);
      edges.add(new WeightedEdge(u, v, Math.hypot(a.x - b.x, a.y - b.y)));
      path.clear();
      repaint();
      return true;
    }

    List<Integer> showShortestPath(int source, int end) {
      path = shortestPath(source, end);
      repaint();
      return path;
    }

    double getPathCost(int source, int end) {
      List<Integer> result = path == null ? null : path;
      if (result == null || result.isEmpty()) {
        return Double.POSITIVE_INFINITY;
      }
      double cost = 0;
      for (int i = 0; i < result.size() - 1; i++) {
        WeightedEdge edge = findEdge(result.get(i), result.get(i + 1));
        if (edge == null) {
          return Double.POSITIVE_INFINITY;
        }
        cost += edge.weight;
      }
      return cost;
    }

    private List<Integer> shortestPath(int source, int end) {
      if (source < 0 || source >= vertices.size()
          || end < 0 || end >= vertices.size()) {
        return null;
      }
      double[] cost = new double[vertices.size()];
      Arrays.fill(cost, Double.POSITIVE_INFINITY);
      int[] parent = new int[vertices.size()];
      Arrays.fill(parent, -1);
      boolean[] visited = new boolean[vertices.size()];
      cost[source] = 0;

      for (int count = 0; count < vertices.size(); count++) {
        int u = -1;
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < vertices.size(); i++) {
          if (!visited[i] && cost[i] < min) {
            min = cost[i];
            u = i;
          }
        }
        if (u == -1) {
          break;
        }
        visited[u] = true;
        if (u == end) {
          break;
        }
        for (WeightedEdge edge : edges) {
          if (edge.u == u) {
            relax(u, edge.v, edge.weight, cost, parent);
          }
          else if (edge.v == u) {
            relax(u, edge.u, edge.weight, cost, parent);
          }
        }
      }

      if (Double.isInfinite(cost[end])) {
        return new ArrayList<>();
      }
      List<Integer> result = new ArrayList<>();
      for (int v = end; v != -1; v = parent[v]) {
        result.add(v);
      }
      Collections.reverse(result);
      return result;
    }

    private void relax(int u, int v, double weight, double[] cost,
        int[] parent) {
      if (cost[v] > cost[u] + weight) {
        cost[v] = cost[u] + weight;
        parent[v] = u;
      }
    }

    private WeightedEdge findEdge(int u, int v) {
      for (WeightedEdge edge : edges) {
        if ((edge.u == u && edge.v == v) || (edge.u == v && edge.v == u)) {
          return edge;
        }
      }
      return null;
    }

    private void repaint() {
      getChildren().clear();
      for (WeightedEdge edge : edges) {
        Vertex a = vertices.get(edge.u);
        Vertex b = vertices.get(edge.v);
        Line line = new Line(a.x, a.y, b.x, b.y);
        line.setStroke(Color.GRAY);
        Text text = new Text((a.x + b.x) / 2, (a.y + b.y) / 2,
            "" + Math.round(edge.weight));
        getChildren().addAll(line, text);
      }

      for (int i = 0; i < path.size() - 1; i++) {
        WeightedEdge edge = findEdge(path.get(i), path.get(i + 1));
        if (edge != null) {
          Vertex a = vertices.get(edge.u);
          Vertex b = vertices.get(edge.v);
          Line line = new Line(a.x, a.y, b.x, b.y);
          line.setStroke(Color.RED);
          line.setStrokeWidth(3);
          getChildren().add(line);
        }
      }

      for (Vertex vertex : vertices) {
        Circle circle = new Circle(vertex.x, vertex.y, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text text = new Text(vertex.x - 7, vertex.y + 5, vertex.name);
        getChildren().addAll(circle, text);
      }
    }
  }

  private static class Vertex {
    String name;
    double x;
    double y;

    Vertex(String name, double x, double y) {
      this.name = name;
      this.x = x;
      this.y = y;
    }
  }

  private static class WeightedEdge {
    int u;
    int v;
    double weight;

    WeightedEdge(int u, int v, double weight) {
      this.u = u;
      this.v = v;
      this.weight = weight;
    }
  }
}
