package Exercise_30._22;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_30_22 extends Application {
    private static final int SIZE = 8;
    private final StackPane[][] squares = new StackPane[SIZE][SIZE];
    private final Label status = new Label();
    private final Button solve = new Button("Solve");
    private volatile boolean cancelled;

    @Override
    public void start(Stage stage) {
        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Rectangle square = new Rectangle(56, 56);
                square.setFill((row + column) % 2 == 0 ? Color.BEIGE : Color.SADDLEBROWN);
                Text text = new Text(row + "," + column);
                text.setVisible(false);
                StackPane pane = new StackPane(square, text);
                squares[row][column] = pane;
                board.add(pane, column, row);
            }
        }

        Button reset = new Button("Reset");
        solve.setOnAction(event -> startSearch());
        reset.setOnAction(event -> {
            cancelled = true;
            resetBoard();
            status.setText("");
            solve.setDisable(false);
        });

        BorderPane root = new BorderPane(board);
        root.setTop(new HBox(8, solve, reset, status));
        stage.setTitle("Exercise 30.22: Eight Queens animation");
        stage.setScene(new Scene(root, 520, 540));
        stage.show();
    }

    private void startSearch() {
        cancelled = false;
        solve.setDisable(true);
        status.setText("Searching...");
        Thread thread = new Thread(() -> {
            int[] columns = new int[SIZE];
            java.util.Arrays.fill(columns, -1);
            int count = search(0, columns);
            Platform.runLater(() -> {
                status.setText("Solutions: " + count);
                solve.setDisable(false);
            });
        });
        thread.setDaemon(true);
        thread.start();
    }

    private int search(int row, int[] columns) {
        if (cancelled) {
            return 0;
        }
        if (row == SIZE) {
            return 1;
        }
        int total = 0;
        for (int column = 0; column < SIZE; column++) {
            if (!isValid(columns, row, column)) {
                continue;
            }
            columns[row] = column;
            showState(row, columns, column);
            total += search(row + 1, columns);
            columns[row] = -1;
            showState(row, columns, column);
        }
        return total;
    }

    private void showState(int row, int[] columns, int column) {
        int[] snapshot = columns.clone();
        int currentRow = row;
        Platform.runLater(() -> {
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    StackPane pane = squares[r][c];
                    Text text = (Text) pane.getChildren().get(1);
                    boolean queen = snapshot[r] == c;
                    text.setText(queen ? "Q" : "");
                    text.setVisible(queen);
                    Rectangle square = (Rectangle) pane.getChildren().get(0);
                    if (r == currentRow) {
                        square.setStroke(Color.RED);
                        square.setStrokeWidth(3);
                    } else {
                        square.setStroke(null);
                        square.setStrokeWidth(0);
                    }
                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private void resetBoard() {
        Platform.runLater(() -> {
            for (StackPane[] row : squares) {
                for (StackPane pane : row) {
                    Text text = (Text) pane.getChildren().get(1);
                    text.setText("");
                    text.setVisible(false);
                    Rectangle square = (Rectangle) pane.getChildren().get(0);
                    square.setStroke(null);
                    square.setStrokeWidth(0);
                }
            }
        });
    }

    private boolean isValid(int[] columns, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (columns[i] == column || Math.abs(columns[i] - column) == row - i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
