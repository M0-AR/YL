package Exercise_28._06Extra;

import java.util.List;

import Exercise_28._commonExtra.GraphEditorPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_28_06Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btTest = new Button("Test if Graph Is Cyclic");
    HBox hBox = new HBox(10, btTest, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btTest.setOnAction(e -> {
      List<Integer> cycle = view.displayCycle();
      if (cycle == null || cycle.isEmpty()) {
        lblStatus.setText("The graph is not cyclic");
      }
      else {
        lblStatus.setText("The graph is cyclic: " + cycle);
      }
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_06");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
