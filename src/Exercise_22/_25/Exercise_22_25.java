package Exercise_22._25;

import java.util.ArrayList;
import java.util.List;

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

public class Exercise_22_25 extends Application {
    private static final int MAX_SOLUTIONS = 1000;

    private final TextField[][] cells = new TextField[9][9];
    private final List<int[][]> solutions = new ArrayList<>();
    private final Label status = new Label();
    private final Button next = new Button("Next");
    private int current;

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
        next.setVisible(false);
        HBox buttons = new HBox(8, solve, clear, next, status);
        buttons.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(buttons);
        root.setCenter(grid);

        solve.setOnAction(event -> solve());
        clear.setOnAction(event -> clear());
        next.setOnAction(event -> showNext());

        Scene scene = new Scene(root, 460, 440);
        primaryStage.setTitle("Exercise_22_25");
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
            }
        }
        solutions.clear();
        current = 0;
        next.setVisible(false);
        status.setText("");
    }

    private void solve() {
        int[][] grid = readGrid();
        if (grid == null) {
            status.setText("Use digits 1-9 in the cells");
            return;
        }
        boolean hasClue = false;
        for (int[] row : grid) {
            for (int value : row) {
                hasClue |= value != 0;
            }
        }
        if (!hasClue) {
            status.setText("Enter at least one clue");
            return;
        }

        solutions.clear();
        collect(grid);
        if (solutions.isEmpty()) {
            next.setVisible(false);
            status.setText("No solution");
            return;
        }
        current = 0;
        showSolution();
        next.setVisible(solutions.size() > 1);
        status.setText("Solution 1 of " + solutions.size()
                + (solutions.size() == MAX_SOLUTIONS ? " (first " + MAX_SOLUTIONS + ")" : ""));
    }

    private void showNext() {
        current = (current + 1) % solutions.size();
        showSolution();
        status.setText("Solution " + (current + 1) + " of " + solutions.size()
                + (solutions.size() == MAX_SOLUTIONS ? " (first " + MAX_SOLUTIONS + ")" : ""));
    }

    private void showSolution() {
        int[][] solution = solutions.get(current);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                cells[row][column].setText(Integer.toString(solution[row][column]));
            }
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

    private void collect(int[][] grid) {
        if (solutions.size() >= MAX_SOLUTIONS) {
            return;
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (grid[row][column] != 0) {
                    continue;
                }
                for (int value = 1; value <= 9; value++) {
                    if (isValid(grid, row, column, value)) {
                        grid[row][column] = value;
                        collect(grid);
                        grid[row][column] = 0;
                    }
                }
                return;
            }
        }
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, 9);
        }
        solutions.add(copy);
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
