package Exercise_28._14Extra;

import java.util.List;

import Exercise_28._commonExtra.GraphEditorPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_28_14Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private TextField tfStart = new TextField();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    Button btDisplay = new Button("Display Longest Path");
    HBox hBox = new HBox(5,
        new Label("Starting vertex: "), tfStart, btDisplay, lblStatus);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);

    btDisplay.setOnAction(e -> {
      try {
        int start = Integer.parseInt(tfStart.getText().trim());
        if (start < 0 || start >= view.getVertexCount()) {
          lblStatus.setText("Enter a valid vertex index");
          return;
        }
        List<Integer> path = view.displayLongestPath(start);
        lblStatus.setText(path == null
            ? "No longest path is found"
            : "Longest path: " + path);
      }
      catch (NumberFormatException ex) {
        lblStatus.setText("Enter a numeric vertex index");
      }
    });

    Scene scene = new Scene(pane, 950, 620);
    primaryStage.setTitle("Exercise28_14");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
