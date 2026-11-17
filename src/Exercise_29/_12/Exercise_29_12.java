package Exercise_29._12;

import Exercise_29._common.SampleGraphs;
import Exercise_29._common.WeightedGraph;
import Exercise_29._common.WeightedGraphView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_29_12 extends Application {
    @Override
    public void start(Stage stage) {
        WeightedGraph<Integer> graph = SampleGraphs.graph1();
        WeightedGraphView view = new WeightedGraphView(
                graph, SampleGraphs.cityPositions(), SampleGraphs.cityNames());
        BorderPane root = new BorderPane(view);
        root.setTop(new Text("Exercise 29.12: Weighted graph"));
        stage.setTitle("Exercise 29.12");
        stage.setScene(new Scene(root, 800, 480));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
