package Exercise_28._08Extra;

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

public class Exercise_28_08Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btTest = new Button("Test if Graph Is Bipartite");
    HBox hBox = new HBox(10, btTest, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btTest.setOnAction(e -> {
      java.util.List<Set<Integer>> sets = view.displayBipartiteSets();
      if (sets == null) {
        lblStatus.setText("The graph is not bipartite");
      }
      else {
        lblStatus.setText("The graph is bipartite");
      }
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_08");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
