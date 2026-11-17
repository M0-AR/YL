package Exercise_28._05Extra;

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

public class Exercise_28_05Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btFindCycle = new Button("Find a Cycle");
    HBox hBox = new HBox(10, btFindCycle, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btFindCycle.setOnAction(e -> {
      List<Integer> cycle = view.displayCycle();
      if (cycle == null || cycle.isEmpty()) {
        lblStatus.setText("No cycle is found");
      }
      else {
        lblStatus.setText("Cycle: " + cycle);
      }
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_05");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
