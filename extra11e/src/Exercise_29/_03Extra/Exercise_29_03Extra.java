package Exercise_29._03Extra;

import Exercise_29._commonExtra.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_29_03Extra extends Application {
  private final GraphView view = new GraphView();
  private final TextField tfSource = new TextField();
  private final TextField tfPathSource = new TextField();
  private final TextField tfPathEnd = new TextField();
  private final Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btShowMST = new Button("Show MST");
    Button btShowAllSP = new Button("Show All SP from Source");
    Button btShowSP = new Button("Show Shortest Path");

    tfSource.setPrefColumnCount(2);
    tfPathSource.setPrefColumnCount(2);
    tfPathEnd.setPrefColumnCount(2);
    tfSource.setAlignment(Pos.BASELINE_RIGHT);
    tfPathSource.setAlignment(Pos.BASELINE_RIGHT);
    tfPathEnd.setAlignment(Pos.BASELINE_RIGHT);

    HBox mstBox = new HBox(5, btShowMST);
    mstBox.setStyle("-fx-border-color: green; -fx-padding: 4;");
    mstBox.setAlignment(Pos.CENTER);

    HBox allSPBox = new HBox(5,
        new Label("Starting vertex: "), tfSource, btShowAllSP);
    allSPBox.setStyle("-fx-border-color: green; -fx-padding: 4;");
    allSPBox.setAlignment(Pos.CENTER);

    HBox pathBox = new HBox(5,
        new Label("Source vertex: "), tfPathSource,
        new Label("Ending vertex: "), tfPathEnd, btShowSP);
    pathBox.setStyle("-fx-border-color: green; -fx-padding: 4;");
    pathBox.setAlignment(Pos.CENTER);

    HBox controls = new HBox(15, mstBox, allSPBox, pathBox);
    controls.setAlignment(Pos.CENTER);
    controls.setStyle("-fx-padding: 6;");

    BorderPane root = new BorderPane();
    root.setCenter(view);
    root.setBottom(new VBox(5, controls, lblStatus));
    VBox.setMargin(lblStatus, new javafx.geometry.Insets(0, 0, 6, 0));
    lblStatus.setMaxWidth(Double.MAX_VALUE);
    lblStatus.setAlignment(Pos.CENTER);

    btShowMST.setOnAction(e -> showMST());
    btShowAllSP.setOnAction(e -> showAllShortestPaths());
    btShowSP.setOnAction(e -> showShortestPath());

    Scene scene = new Scene(root, 1000, 650);
    primaryStage.setTitle("Exercise29_03");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void showMST() {
    double weight = view.displayMST();
    if (weight < 0) {
      lblStatus.setText("Add at least one vertex first.");
    }
    else {
      lblStatus.setText(String.format("MST weight: %.2f", weight));
    }
  }

  private void showAllShortestPaths() {
    Integer source = readIndex(tfSource);
    if (source == null) {
      return;
    }
    if (view.displayAllShortestPaths(source)) {
      lblStatus.setText("Shortest path tree from vertex " + source
          + " is displayed.");
    }
    else {
      lblStatus.setText("Enter a valid vertex index.");
    }
  }

  private void showShortestPath() {
    Integer source = readIndex(tfPathSource);
    Integer end = readIndex(tfPathEnd);
    if (source == null || end == null) {
      return;
    }
    List<Integer> path = view.displayShortestPath(source, end);
    if (path == null) {
      lblStatus.setText("Enter valid vertex indices.");
    }
    else if (path.isEmpty()) {
      lblStatus.setText("No path exists from vertex " + source
          + " to vertex " + end + ".");
    }
    else {
      lblStatus.setText("Shortest path: " + path);
    }
  }

  private Integer readIndex(TextField field) {
    try {
      int index = Integer.parseInt(field.getText().trim());
      if (index < 0 || index >= view.getVertexCount()) {
        lblStatus.setText("Enter a vertex index from 0 to "
            + (view.getVertexCount() - 1) + ".");
        return null;
      }
      return index;
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter a numeric vertex index.");
      return null;
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static class GraphView extends Pane {
    private static final double RADIUS = 20;
    private static final double MIN_DISTANCE = 50;
    private static final double ARROW_LENGTH = 12;

    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final List<int[]> highlights = new ArrayList<>();
    private Vertex startVertex;
    private boolean lineOn;
    private double lineX;
    private double lineY;

    private GraphView() {
      setPrefSize(900, 540);
      setOnMouseClicked(e -> handleClick(e.getButton(), e.getX(), e.getY()));
      setOnMouseMoved(e -> handleMove(e.getX(), e.getY(), e.isControlDown()));
      repaint();
    }

    private int getVertexCount() {
      return vertices.size();
    }

    private double displayMST() {
      highlights.clear();
      if (vertices.isEmpty()) {
        repaint();
        return -1;
      }

      WeightedGraph.MST mst = buildGraph().getMinimumSpanningTree();
      for (int i = 0; i < mst.getSize(); i++) {
        if (mst.getParent(i) != -1) {
          highlights.add(new int[] {mst.getParent(i), i});
        }
      }
      repaint();
      return mst.getTotalWeight();
    }

    private boolean displayAllShortestPaths(int source) {
      highlights.clear();
      if (vertices.isEmpty() || source < 0 || source >= vertices.size()) {
        repaint();
        return false;
      }

      WeightedGraph.ShortestPathTree tree =
          buildGraph().getShortestPath(source);
      for (int i = 0; i < tree.getSize(); i++) {
        if (tree.getParent(i) != -1) {
          highlights.add(new int[] {tree.getParent(i), i});
        }
      }
      repaint();
      return true;
    }

    private List<Integer> displayShortestPath(int source, int end) {
      highlights.clear();
      if (vertices.isEmpty() || source < 0 || source >= vertices.size()
          || end < 0 || end >= vertices.size()) {
        repaint();
        return null;
      }

      WeightedGraph.ShortestPathTree tree =
          buildGraph().getShortestPath(source);
      List<Integer> path = tree.getPath(end);
      for (int i = 0; i < path.size() - 1; i++) {
        highlights.add(new int[] {path.get(i), path.get(i + 1)});
      }
      repaint();
      return path;
    }

    private WeightedGraph buildGraph() {
      WeightedGraph graph = new WeightedGraph(vertices.size());
      for (Edge edge : edges) {
        int u = vertices.indexOf(edge.u);
        int v = vertices.indexOf(edge.v);
        if (u >= 0 && v >= 0) {
          graph.addEdge(u, v, edge.weight());
        }
      }
      return graph;
    }

    private void handleClick(MouseButton button, double x, double y) {
      if (button == MouseButton.SECONDARY) {
        Vertex vertex = findVertex(x, y);
        if (vertex != null) {
          vertices.remove(vertex);
          edges.removeIf(edge -> edge.u == vertex || edge.v == vertex);
          startVertex = null;
          lineOn = false;
          highlights.clear();
          repaint();
        }
        return;
      }

      if (button != MouseButton.PRIMARY) {
        return;
      }

      Vertex vertex = findVertex(x, y);

      if (lineOn) {
        if (vertex != null && vertex != startVertex) {
          addEdge(startVertex, vertex);
        }
        startVertex = null;
        lineOn = false;
        highlights.clear();
        repaint();
        return;
      }

      if (vertex != null) {
        startVertex = vertex;
        lineX = x;
        lineY = y;
        lineOn = true;
        repaint();
        return;
      }

      if (isTooCloseToVertex(x, y)) {
        return;
      }

      vertices.add(new Vertex(x, y));
      highlights.clear();
      repaint();
    }

    private void handleMove(double x, double y, boolean controlDown) {
      if (controlDown) {
        Vertex vertex = findVertex(x, y);
        if (vertex != null) {
          vertex.x = x;
          vertex.y = y;
          startVertex = null;
          lineOn = false;
          highlights.clear();
          repaint();
        }
      }
      else if (lineOn) {
        lineX = x;
        lineY = y;
        repaint();
      }
    }

    private void addEdge(Vertex u, Vertex v) {
      if (u == null || v == null || u == v || findEdge(u, v) != null) {
        return;
      }
      edges.add(new Edge(u, v));
    }

    private Edge findEdge(Vertex u, Vertex v) {
      for (Edge edge : edges) {
        if ((edge.u == u && edge.v == v) || (edge.u == v && edge.v == u)) {
          return edge;
        }
      }
      return null;
    }

    private boolean isTooCloseToVertex(double x, double y) {
      for (Vertex vertex : vertices) {
        if (vertex.distanceTo(x, y) < MIN_DISTANCE) {
          return true;
        }
      }
      return false;
    }

    private Vertex findVertex(double x, double y) {
      for (Vertex vertex : vertices) {
        if (vertex.distanceTo(x, y) <= RADIUS) {
          return vertex;
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
        getChildren().add(new Text(145, 40 + i * 15, instructions[i][1]));
      }
    }

    private void drawEdges() {
      for (Edge edge : edges) {
        getChildren().add(new Line(edge.u.x, edge.u.y, edge.v.x, edge.v.y));
        String label = "" + Math.round(edge.weight());
        getChildren().add(new Text((edge.u.x + edge.v.x) / 2,
            (edge.u.y + edge.v.y) / 2, label));
      }
    }

    private void drawHighlights() {
      for (int[] edge : highlights) {
        if (edge[0] >= 0 && edge[0] < vertices.size()
            && edge[1] >= 0 && edge[1] < vertices.size()) {
          drawArrow(vertices.get(edge[0]), vertices.get(edge[1]));
        }
      }
    }

    private void drawArrow(Vertex from, Vertex to) {
      double dx = to.x - from.x;
      double dy = to.y - from.y;
      double distance = Math.hypot(dx, dy);
      if (distance == 0) {
        return;
      }

      double startX = from.x + RADIUS * dx / distance;
      double startY = from.y + RADIUS * dy / distance;
      double endX = to.x - RADIUS * dx / distance;
      double endY = to.y - RADIUS * dy / distance;

      Line line = new Line(startX, startY, endX, endY);
      line.setStroke(Color.RED);
      line.setStrokeWidth(2);
      getChildren().add(line);

      double angle = Math.atan2(endY - startY, endX - startX);
      Line arrow1 = new Line(endX, endY,
          endX - ARROW_LENGTH * Math.cos(angle - Math.PI / 6),
          endY - ARROW_LENGTH * Math.sin(angle - Math.PI / 6));
      Line arrow2 = new Line(endX, endY,
          endX - ARROW_LENGTH * Math.cos(angle + Math.PI / 6),
          endY - ARROW_LENGTH * Math.sin(angle + Math.PI / 6));
      arrow1.setStroke(Color.RED);
      arrow2.setStroke(Color.RED);
      getChildren().addAll(arrow1, arrow2);
    }

    private void drawVertices() {
      for (int i = 0; i < vertices.size(); i++) {
        Vertex vertex = vertices.get(i);
        Circle circle = new Circle(vertex.x, vertex.y, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        String label = "" + i;
        Text text = new Text(vertex.x - 4 * (label.length() - 1) / 2.0,
            vertex.y + 5, label);
        getChildren().addAll(circle, text);
      }
    }
  }

  private static class Vertex {
    private double x;
    private double y;

    private Vertex(double x, double y) {
      this.x = x;
      this.y = y;
    }

    private double distanceTo(double otherX, double otherY) {
      return Math.hypot(x - otherX, y - otherY);
    }
  }

  private static class Edge {
    private final Vertex u;
    private final Vertex v;

    private Edge(Vertex u, Vertex v) {
      this.u = u;
      this.v = v;
    }

    private double weight() {
      return u.distanceTo(v.x, v.y);
    }
  }
}
