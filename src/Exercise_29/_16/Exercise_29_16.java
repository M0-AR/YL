package Exercise_29._16;

import Exercise_29._common.WeightedGraph;
import Exercise_29._common.WeightedGraphView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class Exercise_29_16 extends Application {
    private static final String TOO_FEW_VERTICES = "Add at least two vertices first";
    private final WeightedGraph<Integer> graph = new WeightedGraph<>();
    private final Map<Integer, double[]> positions = new LinkedHashMap<>();
    private final Label status = new Label();
    private WeightedGraphView view;

    @Override
    public void start(Stage stage) {
        view = new WeightedGraphView(graph, positions, null);

        TextField xField = new TextField();
        TextField yField = new TextField();
        xField.setPrefColumnCount(4);
        yField.setPrefColumnCount(4);
        Button addVertex = new Button("Add vertex");
        HBox vertexRow = new HBox(6, new Label("x:"), xField,
                new Label("y:"), yField, addVertex);
        vertexRow.setAlignment(Pos.CENTER);

        TextField uField = new TextField();
        TextField vField = new TextField();
        TextField wField = new TextField();
        for (TextField field : new TextField[] {uField, vField, wField}) {
            field.setPrefColumnCount(4);
        }
        Button addEdge = new Button("Add edge");
        HBox edgeRow = new HBox(6, new Label("u:"), uField, new Label("v:"), vField,
                new Label("w:"), wField, addEdge);
        edgeRow.setAlignment(Pos.CENTER);

        addVertex.setOnAction(event -> {
            try {
                double x = Double.parseDouble(xField.getText().trim());
                double y = Double.parseDouble(yField.getText().trim());
                int index = graph.getSize();
                graph.addVertex(index);
                positions.put(index, new double[] {x, y});
                view.refresh();
                status.setText("Added vertex " + index);
            } catch (NumberFormatException exception) {
                status.setText("Enter numeric coordinates");
            }
        });

        addEdge.setOnAction(event -> {
            try {
                int u = Integer.parseInt(uField.getText().trim());
                int v = Integer.parseInt(vField.getText().trim());
                double w = Double.parseDouble(wField.getText().trim());
                graph.addEdge(u, v, w);
                graph.addEdge(v, u, w);
                if (graph.getSize() < 2) {
                    status.setText(TOO_FEW_VERTICES);
                } else {
                    WeightedGraph<Integer>.MST tree = graph.getMinimumSpanningTree();
                    view.setMST(tree.getEdges());
                    status.setText("MST total weight: " + tree.getTotalWeight());
                }
            } catch (RuntimeException exception) {
                status.setText("Check the vertex indices and weight");
            }
        });

        BorderPane root = new BorderPane(view);
        root.setTop(new VBox(6, vertexRow, edgeRow, status));
        stage.setTitle("Exercise 29.16: Dynamic MST");
        stage.setScene(new Scene(root, 820, 540));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
