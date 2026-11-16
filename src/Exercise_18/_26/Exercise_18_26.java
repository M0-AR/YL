package Exercise_18._26;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_18_26 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label status = new Label("Click cells to mark them, then click Solve.");
        MazePane maze = new MazePane(status);
        Button solve = new Button("Solve");
        solve.setOnAction(e -> maze.solve());

        HBox controls = new HBox(10, solve, status);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(new StackPane(maze));
        root.setBottom(controls);

        Scene scene = new Scene(root, 380, 440);
        primaryStage.setTitle("Exercise_18_26");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class MazePane extends Pane {
        private static final int SIZE = 8;
        private static final int CELL = 45;

        private final boolean[][] marked = new boolean[SIZE][SIZE];
        private final Polyline path = new Polyline();
        private final Label status;

        MazePane(Label status) {
            this.status = status;
            setPrefSize(SIZE * CELL, SIZE * CELL);

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    Rectangle cell = new Rectangle(
                            col * CELL, row * CELL, CELL, CELL);
                    cell.setFill(Color.WHITE);
                    cell.setStroke(Color.GRAY);

                    int r = row;
                    int c = col;
                    cell.setOnMouseClicked(e -> {
                        marked[r][c] = !marked[r][c];
                        cell.setFill(marked[r][c] ? Color.BLACK : Color.WHITE);
                        clearPath();
                        status.setText("");
                    });

                    getChildren().add(cell);
                }
            }

            path.setStroke(Color.RED);
            path.setStrokeWidth(3);
            path.setFill(Color.TRANSPARENT);
            getChildren().add(path);
        }

        void solve() {
            clearPath();
            List<int[]> solution = findPath();

            if (solution.isEmpty()) {
                status.setText("No path found.");
                return;
            }

            for (int[] cell : solution) {
                path.getPoints().addAll(
                        cell[1] * CELL + CELL / 2.0,
                        cell[0] * CELL + CELL / 2.0);
            }
            status.setText("Path found: " + solution.size() + " cells.");
        }

        private void clearPath() {
            path.getPoints().clear();
        }

        List<int[]> findPath() {
            boolean[][] onPath = new boolean[SIZE][SIZE];
            List<int[]> pathCells = new ArrayList<>();

            if (search(0, 0, onPath, pathCells)) {
                return pathCells;
            }
            return new ArrayList<>();
        }

        private boolean search(int row, int col, boolean[][] onPath,
                               List<int[]> pathCells) {
            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE
                    || marked[row][col] || onPath[row][col]) {
                return false;
            }

            onPath[row][col] = true;
            pathCells.add(new int[]{row, col});

            if (!formsSquare(onPath, row, col)) {
                if (row == SIZE - 1 && col == SIZE - 1) {
                    return true;
                }

                int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
                for (int[] direction : directions) {
                    if (search(row + direction[0], col + direction[1],
                            onPath, pathCells)) {
                        return true;
                    }
                }
            }

            pathCells.remove(pathCells.size() - 1);
            onPath[row][col] = false;
            return false;
        }

        private boolean formsSquare(boolean[][] onPath, int row, int col) {
            for (int rowOffset = -1; rowOffset <= 0; rowOffset++) {
                for (int colOffset = -1; colOffset <= 0; colOffset++) {
                    int r = row + rowOffset;
                    int c = col + colOffset;

                    if (r >= 0 && r + 1 < SIZE && c >= 0 && c + 1 < SIZE
                            && onPath[r][c] && onPath[r + 1][c]
                            && onPath[r][c + 1] && onPath[r + 1][c + 1]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
