package Exercise_28._19;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Exercise_28_19 extends Application {
    private final String[] cities = {
        "Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City",
        "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"
    };
    private final Map<Integer, double[]> positions = new HashMap<>();
    private GraphView graphView;
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        positions.put(0, new double[] {90, 60});
        positions.put(1, new double[] {65, 190});
        positions.put(2, new double[] {85, 260});
        positions.put(3, new double[] {250, 190});
        positions.put(4, new double[] {400, 210});
        positions.put(5, new double[] {520, 150});
        positions.put(6, new double[] {680, 100});
        positions.put(7, new double[] {650, 160});
        positions.put(8, new double[] {560, 280});
        positions.put(9, new double[] {640, 360});
        positions.put(10, new double[] {370, 330});
        positions.put(11, new double[] {390, 390});

        int[][] edges = {
            {0, 1}, {0, 5}, {1, 0}, {1, 2}, {1, 3},
            {2, 1}, {2, 3}, {2, 10}, {3, 1}, {3, 2}, {3, 4}, {3, 10},
            {4, 3}, {4, 5}, {4, 10}, {5, 0}, {5, 4}, {5, 6}, {5, 7},
            {6, 5}, {6, 7}, {7, 5}, {7, 6}, {7, 8}, {8, 7}, {8, 9},
            {8, 10}, {9, 8}, {9, 11}, {10, 2}, {10, 3}, {10, 4},
            {10, 8}, {10, 11}, {11, 9}, {11, 10}
        };
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, cities.length);
        graphView = new GraphView(graph, positions, cities);

        TextField cityField = new TextField("Seattle");
        cityField.setPrefColumnCount(12);
        Button dfsButton = new Button("DFS tree");
        Button bfsButton = new Button("BFS tree");
        dfsButton.setOnAction(event -> showTree(graph, cityField.getText(), true));
        bfsButton.setOnAction(event -> showTree(graph, cityField.getText(), false));

        BorderPane root = new BorderPane(graphView);
        root.setTop(new HBox(8, new Label("Starting city:"), cityField, dfsButton, bfsButton));
        root.setBottom(message);
        BorderPane.setAlignment(message, Pos.CENTER);

        stage.setTitle("Exercise 28.19: Display a DFS/BFS tree");
        stage.setScene(new Scene(root, 780, 480));
        stage.show();
    }

    private void showTree(UnweightedGraph<Integer> graph, String city, boolean depthFirst) {
        int index = findCity(city.trim());
        if (index == -1) {
            message.setText("City not found: " + city);
            return;
        }
        graphView.setTree(depthFirst ? graph.dfs(index) : graph.bfs(index));
        message.setText((depthFirst ? "DFS" : "BFS") + " tree rooted at " + cities[index]);
    }

    private int findCity(String city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(city)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
