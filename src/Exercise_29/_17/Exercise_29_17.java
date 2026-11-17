package Exercise_29._17;

import Exercise_29._common.WeightedGraph;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_29_17 extends Application {
    private static final double RADIUS = 20;
    private final List<double[]> vertices = new ArrayList<>();
    private final List<double[]> edges = new ArrayList<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label("Left-click empty space: add vertex");
    private List<Integer> overlayPath;
    private List<double[]> overlayMST;
    private int pressedVertex = -1;
    private boolean controlDrag;
    private WeightedGraph<Integer> graph = new WeightedGraph<>();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(900, 620);
        canvas.setOnMousePressed(event -> {
            int vertex = vertexAt(event.getX(), event.getY());
            if (event.getButton() == MouseButton.SECONDARY) {
                if (vertex >= 0) {
                    removeVertex(vertex);
                }
                return;
            }
            if (vertex >= 0) {
                pressedVertex = vertex;
                controlDrag = event.isControlDown();
            } else {
                vertices.add(new double[] {event.getX(), event.getY()});
                pressedVertex = -1;
                rebuild();
            }
        });
        canvas.setOnMouseReleased(event -> {
            if (pressedVertex < 0) {
                return;
            }
            int target = vertexAt(event.getX(), event.getY());
            if (controlDrag) {
                vertices.set(pressedVertex, new double[] {event.getX(), event.getY()});
                rebuild();
            } else if (target >= 0 && target != pressedVertex) {
                addEdge(pressedVertex, target);
            }
            pressedVertex = -1;
            controlDrag = false;
        });

        TextField sourceField = new TextField();
        TextField fromField = new TextField();
        TextField toField = new TextField();
        for (TextField field : new TextField[] {sourceField, fromField, toField}) {
            field.setPrefColumnCount(4);
        }
        Button showMST = new Button("Show MST");
        Button showSPT = new Button("Show All SP From the Source");
        Button showPath = new Button("Show Shortest Path");

        showMST.setOnAction(event -> {
            if (vertices.isEmpty()) {
                status.setText("Add vertices first");
                return;
            }
            WeightedGraph<Integer>.MST tree = graph.getMinimumSpanningTree();
            overlayMST = new ArrayList<>();
            for (WeightedGraph.WeightedEdge edge : tree.getEdges()) {
                overlayMST.add(new double[] {edge.u, edge.v});
            }
            overlayPath = null;
            status.setText("MST total weight: " + tree.getTotalWeight());
            draw();
        });

        showSPT.setOnAction(event -> {
            try {
                int source = Integer.parseInt(sourceField.getText().trim());
                var tree = graph.getShortestPath(source);
                overlayMST = new ArrayList<>();
                for (int v = 0; v < graph.getSize(); v++) {
                    if (v != source && tree.isReachable(v)) {
                        overlayMST.add(new double[] {tree.getParent(v), v});
                    }
                }
                overlayPath = null;
                status.setText("Shortest path tree from " + source);
                draw();
            } catch (RuntimeException exception) {
                status.setText("Enter a valid source vertex");
            }
        });

        showPath.setOnAction(event -> {
            try {
                int from = Integer.parseInt(fromField.getText().trim());
                int to = Integer.parseInt(toField.getText().trim());
                var tree = graph.getShortestPath(from);
                overlayMST = null;
                overlayPath = tree.getPath(to);
                status.setText(overlayPath == null
                        ? "No path" : "Cost: " + tree.getCost(to));
                draw();
            } catch (RuntimeException exception) {
                status.setText("Enter valid vertex indices");
            }
        });

        HBox controls = new HBox(6, new Label("Source:"), sourceField, showMST, showSPT,
                new Label("From:"), fromField, new Label("To:"), toField, showPath);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setTop(controls);
        root.setBottom(status);
        stage.setTitle("Exercise 29.17: Weighted graph visualization tool");
        stage.setScene(new Scene(root, 940, 700));
        stage.show();
        rebuild();
    }

    private void addEdge(int u, int v) {
        double dx = vertices.get(u)[0] - vertices.get(v)[0];
        double dy = vertices.get(u)[1] - vertices.get(v)[1];
        edges.add(new double[] {u, v, Math.hypot(dx, dy)});
        rebuild();
    }

    private void removeVertex(int vertex) {
        vertices.remove(vertex);
        edges.removeIf(edge -> (int) edge[0] == vertex || (int) edge[1] == vertex);
        for (double[] edge : edges) {
            if ((int) edge[0] > vertex) {
                edge[0]--;
            }
            if ((int) edge[1] > vertex) {
                edge[1]--;
            }
        }
        overlayPath = null;
        overlayMST = null;
        rebuild();
    }

    private int vertexAt(double x, double y) {
        for (int i = vertices.size() - 1; i >= 0; i--) {
            double[] point = vertices.get(i);
            if (Math.hypot(point[0] - x, point[1] - y) <= RADIUS + 6) {
                return i;
            }
        }
        return -1;
    }

    private void rebuild() {
        graph = new WeightedGraph<>();
        for (int i = 0; i < vertices.size(); i++) {
            graph.addVertex(i);
        }
        for (double[] edge : edges) {
            int u = (int) edge[0];
            int v = (int) edge[1];
            graph.addEdge(u, v, edge[2]);
            graph.addEdge(v, u, edge[2]);
        }
        draw();
    }

    private void draw() {
        canvas.getChildren().clear();
        for (double[] edge : edges) {
            drawLine((int) edge[0], (int) edge[1], Color.LIGHTGRAY, 1);
            double[] first = vertices.get((int) edge[0]);
            double[] second = vertices.get((int) edge[1]);
            Text weight = new Text((first[0] + second[0]) / 2 + 3,
                    (first[1] + second[1]) / 2 - 3, String.format("%.0f", edge[2]));
            weight.setFill(Color.DIMGRAY);
            canvas.getChildren().add(weight);
        }
        if (overlayMST != null) {
            for (double[] edge : overlayMST) {
                drawLine((int) edge[0], (int) edge[1], Color.RED, 3);
            }
        }
        if (overlayPath != null) {
            for (int i = 0; i + 1 < overlayPath.size(); i++) {
                drawLine(overlayPath.get(i), overlayPath.get(i + 1), Color.RED, 3);
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            double[] point = vertices.get(i);
            Circle circle = new Circle(point[0], point[1], RADIUS, Color.LIGHTYELLOW);
            circle.setStroke(Color.DARKSLATEGRAY);
            Text label = new Text(point[0] - 4, point[1] + 4, String.valueOf(i));
            canvas.getChildren().addAll(circle, label);
        }
    }

    private void drawLine(int u, int v, Color color, double width) {
        double[] first = vertices.get(u);
        double[] second = vertices.get(v);
        Line line = new Line(first[0], first[1], second[0], second[1]);
        line.setStroke(color);
        line.setStrokeWidth(width);
        canvas.getChildren().add(line);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
