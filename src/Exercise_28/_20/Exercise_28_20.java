package Exercise_28._20;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_28_20 extends Application {
    private final Label message = new Label();
    private BorderPane root;

    @Override
    public void start(Stage stage) {
        TextField fileField = new TextField();
        fileField.setPromptText("Graph file");
        fileField.setPrefColumnCount(24);
        Button load = new Button("Load");
        load.setOnAction(event -> load(fileField.getText().trim()));

        root = new BorderPane();
        root.setTop(new HBox(8, new Label("File:"), fileField, load));
        root.setBottom(message);
        BorderPane.setAlignment(message, Pos.CENTER);

        stage.setTitle("Exercise 28.20: Display a graph");
        stage.setScene(new Scene(root, 800, 560));
        stage.show();
    }

    private void load(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));
            int count = Integer.parseInt(lines.get(0).trim());
            Map<Integer, double[]> positions = new HashMap<>();
            List<int[]> edgeList = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                String[] tokens = lines.get(i).trim().split("\\s+");
                int u = Integer.parseInt(tokens[0]);
                positions.put(u, new double[] {
                    Double.parseDouble(tokens[1]) + 40,
                    Double.parseDouble(tokens[2]) + 40
                });
                for (int j = 3; j < tokens.length; j++) {
                    int v = Integer.parseInt(tokens[j]);
                    edgeList.add(new int[] {u, v});
                    edgeList.add(new int[] {v, u});
                }
            }
            UnweightedGraph<Integer> graph =
                    new UnweightedGraph<>(edgeList.toArray(new int[0][]), count);
            root.setCenter(new GraphView(graph, positions));
            message.setText("Loaded " + count + " vertices");
        } catch (IOException | RuntimeException exception) {
            message.setText("Could not read the graph: " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
