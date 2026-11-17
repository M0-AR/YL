package Exercise_28._09Extra;

import java.util.List;
import java.util.Set;

import Exercise_28._commonExtra.GraphEditorPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_28_09Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btDisplay = new Button("Display Bipartite Sets");
    HBox hBox = new HBox(10, btDisplay, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btDisplay.setOnAction(e -> {
      List<Set<Integer>> sets = view.displayBipartiteSets();
      if (sets == null) {
        lblStatus.setText("The graph is not bipartite");
      }
      else {
        lblStatus.setText("Bipartite sets: " + sets);
      }
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_09");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
