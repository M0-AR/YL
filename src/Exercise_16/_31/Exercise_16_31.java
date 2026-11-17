package Exercise_16._31;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Exercise_16_31 extends Application {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final int[][] board = new int[ROWS][COLUMNS];
    private final Circle[][] discs = new Circle[ROWS][COLUMNS];
    private final Label status = new Label();
    private int turn;
    private boolean gameOver;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Rectangle cell = new Rectangle(58, 58, Color.DARKBLUE);
                Circle disc = new Circle(24, Color.TRANSPARENT);
                StackPane pane = new StackPane(cell, disc);
                int clickedRow = row;
                int clickedColumn = column;
                pane.setOnMouseClicked(event -> play(clickedRow, clickedColumn));
                discs[row][column] = disc;
                grid.add(pane, column, row);
            }
        }
        for (int[] row : board) {
            java.util.Arrays.fill(row, -1);
        }

        Button reset = new Button("Reset");
        reset.setOnAction(event -> reset());
        HBox top = new HBox(12, reset, status);
        top.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(grid);
        root.setTop(top);
        stage.setTitle("Exercise 16.31: Connect four");
        stage.setScene(new Scene(root, 500, 440));
        stage.show();
        status.setText("Red's turn");
    }

    private void play(int row, int column) {
        if (gameOver || board[row][column] != -1) {
            return;
        }
        int target = -1;
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][column] == -1) {
                target = r;
                break;
            }
        }
        if (target != row) {
            status.setText("Click the lowest empty cell in the column");
            return;
        }
        board[row][column] = turn;
        discs[row][column].setFill(turn == 0 ? Color.RED : Color.YELLOW);

        List<int[]> winning = findWinningCells(row, column, turn);
        if (!winning.isEmpty()) {
            gameOver = true;
            status.setText(turn == 0 ? "Red wins!" : "Yellow wins!");
            flash(winning);
            return;
        }
        if (isFull()) {
            gameOver = true;
            status.setText("No winners");
            return;
        }
        turn = 1 - turn;
        status.setText(turn == 0 ? "Red's turn" : "Yellow's turn");
    }

    private List<int[]> findWinningCells(int row, int column, int player) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] direction : directions) {
            List<int[]> cells = new ArrayList<>();
            cells.add(new int[] {row, column});
            for (int sign = -1; sign <= 1; sign += 2) {
                for (int step = 1; step < ROWS; step++) {
                    int r = row + direction[0] * step * sign;
                    int c = column + direction[1] * step * sign;
                    if (r < 0 || r >= ROWS || c < 0 || c >= COLUMNS
                            || board[r][c] != player) {
                        break;
                    }
                    cells.add(new int[] {r, c});
                }
            }
            if (cells.size() >= 4) {
                return cells;
            }
        }
        return new ArrayList<>();
    }

    private void flash(List<int[]> cells) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250), event -> {
            for (int[] cell : cells) {
                Circle disc = discs[cell[0]][cell[1]];
                disc.setVisible(!disc.isVisible());
            }
        }));
        timeline.setCycleCount(8);
        timeline.setOnFinished(event -> {
            for (int[] cell : cells) {
                discs[cell[0]][cell[1]].setVisible(true);
            }
        });
        timeline.play();
    }

    private boolean isFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reset() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                board[row][column] = -1;
                discs[row][column].setFill(Color.TRANSPARENT);
                discs[row][column].setVisible(true);
            }
        }
        turn = 0;
        gameOver = false;
        status.setText("Red's turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
