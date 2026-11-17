package Exercise_28._15Extra;

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

public class Exercise_28_15Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btDisplay = new Button("Display a Longest Path");
    HBox hBox = new HBox(10, btDisplay, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btDisplay.setOnAction(e -> {
      List<Integer> path = view.displayLongestPath();
      lblStatus.setText(path == null
          ? "No longest path is found"
          : "Longest path: " + path);
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_15");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
