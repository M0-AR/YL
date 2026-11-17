package Exercise_22._21;

import javafx.application.Application;
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

public class Exercise_22_21 extends Application {
    private final TextField[][] cells = new TextField[9][9];
    private final Label status = new Label();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                TextField field = new TextField();
                field.setPrefColumnCount(1);
                field.setPrefWidth(36);
                field.setAlignment(Pos.CENTER);
                field.setStyle(borderStyle(row, column));
                cells[row][column] = field;
                grid.add(field, column, row);
            }
        }

        Button solve = new Button("Solve");
        Button clear = new Button("Clear");
        HBox buttons = new HBox(8, solve, clear, status);
        buttons.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(buttons);
        root.setCenter(grid);

        solve.setOnAction(event -> solve());
        clear.setOnAction(event -> clear());

        Scene scene = new Scene(root, 420, 440);
        primaryStage.setTitle("Exercise_22_21");
        primaryStage.setScene(scene);
        primaryStage.show();
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
    }

    private void solve() {
        int[][] grid = readGrid();
        if (grid == null) {
            status.setText("Use digits 1-9 in the cells");
            return;
        }
        if (solve(grid)) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    cells[row][column].setText(Integer.toString(grid[row][column]));
                    cells[row][column].setEditable(false);
                }
            }
            status.setText("Solved");
        } else {
            status.setText("No solution");
        }
    }

    private int[][] readGrid() {
        int[][] grid = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String text = cells[row][column].getText().trim();
                if (text.isEmpty()) {
                    continue;
                }
                if (!text.matches("[1-9]")) {
                    return null;
                }
                grid[row][column] = Integer.parseInt(text);
            }
        }
        return grid;
    }

    private boolean solve(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (grid[row][column] != 0) {
                    continue;
                }
                for (int value = 1; value <= 9; value++) {
                    if (isValid(grid, row, column, value)) {
                        grid[row][column] = value;
                        if (solve(grid)) {
                            return true;
                        }
                        grid[row][column] = 0;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int[][] grid, int row, int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value || grid[i][column] == value) {
                return false;
            }
        }
        int startRow = row / 3 * 3;
        int startColumn = column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startColumn + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
