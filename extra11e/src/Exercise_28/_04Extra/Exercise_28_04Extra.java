package Exercise_28._04Extra;

import Exercise_28._commonExtra.GraphEditorPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_28_04Extra extends Application {
  private GraphEditorPane view = new GraphEditorPane();
  private TextField tfStart = new TextField();
  private TextField tfPathStart = new TextField();
  private TextField tfPathEnd = new TextField();
  private Label lblStatus = new Label("Add vertices and edges to begin.");

  @Override
  public void start(Stage primaryStage) {
    view.setNonrecursiveDFS(true);

    Button btDFS = new Button("Display DFS");
    Button btBFS = new Button("Display BFS");
    Button btShortestPath = new Button("Display Shortest Path");

    HBox hBox1 = new HBox(5,
        new Label("Starting vertex: "), tfStart, btDFS, btBFS);
    hBox1.setAlignment(Pos.CENTER);
    hBox1.setStyle("-fx-border-color: green");

    HBox hBox2 = new HBox(5,
        new Label("Starting vertex: "), tfPathStart,
        new Label("Ending vertex: "), tfPathEnd, btShortestPath);
    hBox2.setAlignment(Pos.CENTER);
    hBox2.setStyle("-fx-border-color: green");

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(new VBox(5, hBox1, hBox2, lblStatus));

    btDFS.setOnAction(e -> {
      Integer start = readIndex(tfStart);
      if (start != null) {
        lblStatus.setText("Nonrecursive DFS: " + view.displayDFS(start));
      }
    });
    btBFS.setOnAction(e -> {
      Integer start = readIndex(tfStart);
      if (start != null) {
        lblStatus.setText("BFS: " + view.displayBFS(start));
      }
    });
    btShortestPath.setOnAction(e -> {
      Integer start = readIndex(tfPathStart);
      Integer end = readIndex(tfPathEnd);
      if (start != null && end != null) {
        lblStatus.setText("Shortest path: "
            + view.displayShortestPath(start, end));
      }
    });

    Scene scene = new Scene(pane, 1000, 650);
    primaryStage.setTitle("Exercise28_04");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Integer readIndex(TextField field) {
    try {
      int index = Integer.parseInt(field.getText().trim());
      if (index < 0 || index >= view.getVertexCount()) {
        lblStatus.setText("Enter a vertex index from 0 to "
            + (view.getVertexCount() - 1));
        return null;
      }
      return index;
    }
    catch (NumberFormatException ex) {
      lblStatus.setText("Enter a numeric vertex index.");
      return null;
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
