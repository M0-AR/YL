package Exercise_28._02Extra;

import java.util.ArrayList;
import java.util.List;

import Exercise_28._commonExtra.UnweightedGraph;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_28_02Extra extends Application {
  private static final int SIZE = 8;
  private static final double CELL_SIZE = 50;

  private Cell[][] board = new Cell[SIZE][SIZE];
  private Label lblStatus = new Label("Click cells to mark or unmark them.");
  private Button btFindPath = new Button("Find Path");
  private Button btClearPath = new Button("Clear Path");

  @Override
  public void start(Stage primaryStage) {
    GridPane gridPane = new GridPane();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        gridPane.add(board[i][j] = new Cell(i, j), j, i);
      }
    }

    HBox hBox = new HBox(5, btFindPath, btClearPath);
    hBox.setAlignment(Pos.CENTER);

    BorderPane pane = new BorderPane();
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    pane.setCenter(gridPane);
    pane.setBottom(hBox);

    btFindPath.setOnAction(e -> findPath());
    btClearPath.setOnAction(e -> clearPath());

    Scene scene = new Scene(pane, 420, 460);
    primaryStage.setTitle("Exercise28_02");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void findPath() {
    clearPath();
    List<UnweightedGraph.Edge> edges = new ArrayList<>();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j].marked) {
          continue;
        }
        int u = i * SIZE + j;
        if (i > 0 && !board[i - 1][j].marked) {
          edges.add(new UnweightedGraph.Edge(u, (i - 1) * SIZE + j));
        }
        if (i < SIZE - 1 && !board[i + 1][j].marked) {
          edges.add(new UnweightedGraph.Edge(u, (i + 1) * SIZE + j));
        }
        if (j > 0 && !board[i][j - 1].marked) {
          edges.add(new UnweightedGraph.Edge(u, i * SIZE + j - 1));
        }
        if (j < SIZE - 1 && !board[i][j + 1].marked) {
          edges.add(new UnweightedGraph.Edge(u, i * SIZE + j + 1));
        }
      }
    }

    UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, SIZE * SIZE);
    List<Integer> path = graph.getShortestPath(0, SIZE * SIZE - 1);
    if (path.size() >= 15) {
      lblStatus.setText("path found");
      for (int vertex : path) {
        board[vertex / SIZE][vertex % SIZE].rectangle.setFill(Color.RED);
      }
      System.out.println(path);
    }
    else {
      lblStatus.setText("No path exists");
    }
  }

  private void clearPath() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j].rectangle.setFill(Color.WHITE);
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  private class Cell extends StackPane {
    private final int row;
    private final int col;
    private boolean marked;
    private Rectangle rectangle =
        new Rectangle(0, 0, CELL_SIZE, CELL_SIZE);
    private Line line1 = new Line(0, 0, CELL_SIZE, CELL_SIZE);
    private Line line2 = new Line(CELL_SIZE, 0, 0, CELL_SIZE);

    Cell(int row, int col) {
      this.row = row;
      this.col = col;
      rectangle.setFill(Color.WHITE);
      rectangle.setStroke(Color.BLACK);
      getChildren().add(rectangle);
      setOnMousePressed(e -> toggleMark());
    }

    private void toggleMark() {
      marked = !marked;
      if (marked) {
        getChildren().addAll(line1, line2);
      }
      else {
        getChildren().removeAll(line1, line2);
      }
      clearPath();
      lblStatus.setText("Click Find Path to search.");
    }

    @Override
    public String toString() {
      return "(" + row + ", " + col + ")";
    }
  }
}
