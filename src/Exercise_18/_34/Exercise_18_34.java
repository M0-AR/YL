package Exercise_18._34;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_18_34 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ChessBoardPane board = new ChessBoardPane();
        Label status = new Label("Click Solve to find a solution.");
        Button solve = new Button("Solve");
        solve.setOnAction(e -> {
            if (board.solve()) {
                status.setText("Solution found.");
            } else {
                status.setText("No solution found.");
            }
        });

        HBox controls = new HBox(10, solve, status);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(board);
        root.setBottom(controls);

        Scene scene = new Scene(root, 420, 460);
        primaryStage.setTitle("Exercise_18_34");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static class ChessBoardPane extends Pane {
        static final int SIZE = 8;
        static final int CELL = 50;

        private final List<Text> queens = new ArrayList<>();

        ChessBoardPane() {
            setPrefSize(SIZE * CELL, SIZE * CELL);
            setMinSize(SIZE * CELL, SIZE * CELL);
            setMaxSize(SIZE * CELL, SIZE * CELL);

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    Rectangle cell = new Rectangle(
                            col * CELL, row * CELL, CELL, CELL);
                    cell.setFill((row + col) % 2 == 0
                            ? Color.web("#f0d9b5")
                            : Color.web("#b58863"));
                    cell.setStroke(Color.web("#8a6642"));
                    getChildren().add(cell);
                }
            }
        }

        boolean solve() {
            getChildren().removeAll(queens);
            queens.clear();

            int[] columns = EightQueens.solve();
            if (columns == null) {
                return false;
            }

            for (int row = 0; row < SIZE; row++) {
                Text queen = new Text(
                        columns[row] * CELL + 12,
                        row * CELL + 38,
                        "\u265B");
                queen.setFont(Font.font(30));
                queen.setFill(Color.BLACK);
                queen.setMouseTransparent(true);
                queens.add(queen);
                getChildren().add(queen);
            }

            return true;
        }
    }

    static class EightQueens {
        private static final int SIZE = 8;

        static int[] solve() {
            int[] columns = new int[SIZE];
            boolean[] usedColumns = new boolean[SIZE];
            boolean[] usedUpDiagonal = new boolean[2 * SIZE - 1];
            boolean[] usedDownDiagonal = new boolean[2 * SIZE - 1];

            return place(0, columns, usedColumns, usedUpDiagonal,
                    usedDownDiagonal) ? columns : null;
        }

        private static boolean place(int row, int[] columns,
                                     boolean[] usedColumns,
                                     boolean[] usedUpDiagonal,
                                     boolean[] usedDownDiagonal) {
            if (row == SIZE) {
                return true;
            }

            for (int col = 0; col < SIZE; col++) {
                int upDiagonal = row + col;
                int downDiagonal = row - col + SIZE - 1;

                if (usedColumns[col] || usedUpDiagonal[upDiagonal]
                        || usedDownDiagonal[downDiagonal]) {
                    continue;
                }

                columns[row] = col;
                usedColumns[col] = true;
                usedUpDiagonal[upDiagonal] = true;
                usedDownDiagonal[downDiagonal] = true;

                if (place(row + 1, columns, usedColumns, usedUpDiagonal,
                        usedDownDiagonal)) {
                    return true;
                }

                usedColumns[col] = false;
                usedUpDiagonal[upDiagonal] = false;
                usedDownDiagonal[downDiagonal] = false;
            }

            return false;
        }
    }
}
