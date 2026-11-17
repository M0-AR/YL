package Exercise_29._02Extra;

import Exercise_29._commonExtra.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Exercise_29_02Extra extends Application {
  private GraphPane graphPane = new GraphPane();
  private TextField tfName = new TextField();
  private TextField tfX = new TextField();
  private TextField tfY = new TextField();
  private TextField tfEdgeFrom = new TextField();
  private TextField tfEdgeTo = new TextField();
  private Label lblStatus = new Label("Add vertices and edges to build an MST.");
  private Label lblMST = new Label("MST weight: 0.00");

  @Override
  public void start(Stage primaryStage) {
    Button btAddVertex = new Button("Add Vertex");
    Button btAddEdge = new Button("Add Edge");

    VBox controls = new VBox(5,
        new Label("Vertex name:"), tfName,
        new Label("x:"), tfX,
        new Label("y:"), tfY,
        btAddVertex,
        new Label("Edge from:"), tfEdgeFrom,
        new Label("to:"), tfEdgeTo,
        btAddEdge,
        lblMST,
        new Label("MST edges are shown in red."),
        lblStatus);
    controls.setPadding(new Insets(10));
    controls.setPrefWidth(190);

    BorderPane pane = new BorderPane();
    pane.setLeft(controls);
    pane.setCenter(graphPane);

    btAddVertex.setOnAction(e -> addVertex());
    btAddEdge.setOnAction(e -> addEdge());

    Scene scene = new Scene(pane, 1000, 650);
    primaryStage.setTitle("Exercise29_02");
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
      updateMST();
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
        updateMST();
      }
      else {
        lblStatus.setText("Enter two distinct valid vertex indices.");
      }
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter numeric vertex indices.");
    }
  }

  private void updateMST() {
    double total = graphPane.updateMST();
    lblMST.setText(String.format("MST weight: %.2f", total));
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static class GraphPane extends Pane {
    private static final double RADIUS = 20;
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<Edge> mstEdges = new ArrayList<>();

    GraphPane() {
      setPrefSize(760, 600);
    }

    int getVertexCount() {
      return vertices.size();
    }

    void addVertex(String name, double x, double y) {
      String label = name.isEmpty() ? "" + vertices.size() : name;
      vertices.add(new Vertex(label, x, y));
      repaint();
    }

    boolean addEdge(int u, int v) {
      if (u < 0 || u >= vertices.size() || v < 0 || v >= vertices.size()
          || u == v || findEdge(u, v) != null) {
        return false;
      }
      Vertex a = vertices.get(u);
      Vertex b = vertices.get(v);
      edges.add(new Edge(u, v, Math.hypot(a.x - b.x, a.y - b.y)));
      return true;
    }

    double updateMST() {
      mstEdges.clear();
      if (vertices.isEmpty()) {
        repaint();
        return 0;
      }

      WeightedGraph graph = new WeightedGraph(vertices.size());
      for (Edge edge : edges) {
        graph.addEdge(edge.u, edge.v, edge.weight);
      }
      WeightedGraph.MST mst = graph.getMinimumSpanningTree();
      for (int v = 0; v < vertices.size(); v++) {
        int parent = mst.getParent(v);
        if (parent != -1) {
          mstEdges.add(new Edge(parent, v, mst.getCost(v)));
        }
      }
      repaint();
      return mst.getTotalWeight();
    }

    private Edge findEdge(int u, int v) {
      for (Edge edge : edges) {
        if ((edge.u == u && edge.v == v) || (edge.u == v && edge.v == u)) {
          return edge;
        }
      }
      return null;
    }

    private void repaint() {
      getChildren().clear();
      for (Edge edge : edges) {
        drawEdge(edge, Color.GRAY, 1, true);
      }
      for (Edge edge : mstEdges) {
        drawEdge(edge, Color.RED, 3, false);
      }

      for (Vertex vertex : vertices) {
        Circle circle = new Circle(vertex.x, vertex.y, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text text = new Text(vertex.x - 7, vertex.y + 5, vertex.name);
        getChildren().addAll(circle, text);
      }
    }

    private void drawEdge(Edge edge, Color color, double width,
        boolean showWeight) {
      Vertex a = vertices.get(edge.u);
      Vertex b = vertices.get(edge.v);
      Line line = new Line(a.x, a.y, b.x, b.y);
      line.setStroke(color);
      line.setStrokeWidth(width);
      getChildren().add(line);
      if (showWeight) {
        Text text = new Text((a.x + b.x) / 2, (a.y + b.y) / 2,
            "" + Math.round(edge.weight));
        getChildren().add(text);
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

  private static class Edge {
    int u;
    int v;
    double weight;

    Edge(int u, int v, double weight) {
      this.u = u;
      this.v = v;
      this.weight = weight;
    }
  }
}
