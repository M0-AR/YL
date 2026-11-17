package Exercise_30._20;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_30_20 extends Application {
    private final TextField[][] cells = new TextField[9][9];
    private final Label status = new Label();
    private final Button solve = new Button("Solve");
    private final Button clear = new Button("Clear");
    private int[][] grid;

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                TextField field = new TextField();
                field.setPrefColumnCount(1);
                field.setPrefWidth(36);
                field.setAlignment(Pos.CENTER);
                field.setStyle(borderStyle(row, column));
                cells[row][column] = field;
                gridPane.add(field, column, row);
            }
        }

        HBox buttons = new HBox(8, solve, clear, status);
        buttons.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(buttons);
        root.setCenter(gridPane);

        solve.setOnAction(event -> startSearch());
        clear.setOnAction(event -> clear());

        stage.setTitle("Exercise 30.20: Sudoku search animation");
        stage.setScene(new Scene(root, 440, 460));
        stage.show();
    }

    private String borderStyle(int row, int column) {
        int top = row % 3 == 0 ? 2 : 1;
        int bottom = row == 8 || row % 3 == 2 ? 2 : 1;
        int left = column % 3 == 0 ? 2 : 1;
        int right = column == 8 || column % 3 == 2 ? 2 : 1;
        return "-fx-alignment: center; -fx-border-color: #444444; -fx-border-width: "
                + top + " " + right + " " + bottom + " " + left + ";";
    }

    private void clear() {
        for (TextField[] row : cells) {
            for (TextField field : row) {
                field.clear();
                field.setEditable(true);
            }
        }
        status.setText("");
        solve.setDisable(false);
    }

    private void startSearch() {
        grid = readGrid();
        if (grid == null) {
            status.setText("Use digits 1-9 in the cells");
            return;
        }
        solve.setDisable(true);
        status.setText("Searching...");
        Thread thread = new Thread(() -> {
            boolean found = solve(grid);
            Platform.runLater(() -> {
                status.setText(found ? "Solved" : "No solution");
                solve.setDisable(false);
            });
        });
        thread.setDaemon(true);
        thread.start();
    }

    private int[][] readGrid() {
        int[][] result = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String text = cells[row][column].getText().trim();
                if (text.isEmpty()) {
                    continue;
                }
                if (!text.matches("[1-9]")) {
                    return null;
                }
                result[row][column] = Integer.parseInt(text);
            }
        }
        return result;
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] != 0) {
                    continue;
                }
                for (int value = 1; value <= 9; value++) {
                    if (isValid(board, row, column, value)) {
                        board[row][column] = value;
                        show(row, column, value);
                        if (solve(board)) {
                            return true;
                        }
                        board[row][column] = 0;
                        show(row, column, 0);
                    }
                }
                return false;
            }
        }
        return true;
    }

    private void show(int row, int column, int value) {
        Platform.runLater(() -> {
            cells[row][column].setText(value == 0 ? "" : String.valueOf(value));
            cells[row][column].setStyle(borderStyle(row, column)
                    + "-fx-background-color: " + (value == 0 ? "white" : "lightblue") + ";");
        });
        try {
            Thread.sleep(30);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isValid(int[][] board, int row, int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value || board[i][column] == value) {
                return false;
            }
        }
        int startRow = row / 3 * 3;
        int startColumn = column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startColumn + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
