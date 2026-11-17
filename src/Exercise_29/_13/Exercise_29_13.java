package Exercise_29._13;

import Exercise_29._common.SampleGraphs;
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
import javafx.stage.Stage;

public class Exercise_29_13 extends Application {
    @Override
    public void start(Stage stage) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        WeightedGraphView view = new WeightedGraphView(
                graph, SampleGraphs.cityPositions(), SampleGraphs.cityNames());

        TextField fromField = new TextField();
        TextField toField = new TextField();
        fromField.setPrefColumnCount(12);
        toField.setPrefColumnCount(12);
        Button show = new Button("Show path");
        Label status = new Label();
        HBox controls = new HBox(6,
                new Label("From:"), fromField,
                new Label("To:"), toField, show, status);
        controls.setAlignment(Pos.CENTER);

        show.setOnAction(event -> {
            int from = cityIndex(fromField.getText().trim());
            int to = cityIndex(toField.getText().trim());
            if (from == -1 || to == -1) {
                status.setText("A city is not in the map");
                view.clearPath();
                return;
            }
            WeightedGraph<Integer>.ShortestPathTree tree = graph.getShortestPath(from);
            if (!tree.isReachable(to)) {
                status.setText("No path");
                view.clearPath();
                return;
            }
            view.setPath(tree.getPath(to));
            status.setText("Cost: " + tree.getCost(to));
        });

        BorderPane root = new BorderPane(view);
        root.setTop(controls);
        stage.setTitle("Exercise 29.13");
        stage.setScene(new Scene(root, 820, 520));
        stage.show();
    }

    private static int cityIndex(String name) {
        String[] cities = SampleGraphs.cityNames();
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
