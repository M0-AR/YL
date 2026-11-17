package Exercise_28._18;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Exercise_28_18 extends Application {
    private static final int SIDE = 8;
    private static final int[] DR = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] DC = {1, 2, 2, 1, -1, -2, -2, -1};
    private final Label[][] cells = new Label[SIDE][SIDE];
    private final Label status = new Label("Press Find tour");

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        for (int r = 0; r < SIDE; r++) {
            for (int c = 0; c < SIDE; c++) {
                Label cell = new Label();
                cell.setPrefSize(48, 48);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-border-color: black;");
                cells[r][c] = cell;
                grid.add(cell, c, r);
            }
        }

        Button find = new Button("Find tour");
        find.setOnAction(event -> {
            find.setDisable(true);
            status.setText("Searching...");
            new Thread(() -> {
                int[][] tour = findClosedTour();
                Platform.runLater(() -> {
                    if (tour == null) {
                        status.setText("No closed tour found; press Find tour to retry");
                    } else {
                        showTour(tour);
                        status.setText("A closed knight's tour was found");
                    }
                    find.setDisable(false);
                });
            }).start();
        });

        BorderPane root = new BorderPane(grid);
        root.setTop(new Label("Knight's tour that returns to the starting square"));
        root.setBottom(new HBox(8, find, status));
        BorderPane.setAlignment(status, Pos.CENTER);

        stage.setTitle("Exercise 28.18: Knight's tour cycle");
        stage.setScene(new Scene(root, 520, 540));
        stage.show();
    }

    private void showTour(int[][] tour) {
        for (int r = 0; r < SIDE; r++) {
            for (int c = 0; c < SIDE; c++) {
                cells[r][c].setText(String.valueOf(tour[r][c]));
            }
        }
    }

    public static int[][] findClosedTour() {
        for (int attempt = 1; attempt <= 20; attempt++) {
            Random random = new Random(attempt);
            int[][] board = new int[SIDE][SIDE];
            board[0][0] = 1;
            int[] budget = {3_000_000};
            int[][] firstMoves = {{2, 1}, {1, 2}};
            if (random.nextBoolean()) {
                int[] temp = firstMoves[0];
                firstMoves[0] = firstMoves[1];
                firstMoves[1] = temp;
            }
            for (int[] move : firstMoves) {
                board[move[0]][move[1]] = 2;
                if (search(move[0], move[1], 2, board, random, budget)) {
                    return board;
                }
                board[move[0]][move[1]] = 0;
            }
        }
        return null;
    }

    private static boolean search(int row, int column, int step,
                                  int[][] board, Random random, int[] budget) {
        if (step == SIDE * SIDE) {
            return isKnightMove(row, column, 0, 0);
        }
        if (--budget[0] <= 0) {
            return false;
        }
        List<int[]> candidates = new ArrayList<>();
        for (int i = 0; i < DR.length; i++) {
            int r = row + DR[i];
            int c = column + DC[i];
            if (inside(r, c) && board[r][c] == 0) {
                candidates.add(new int[] {onwardDegree(r, c, board), random.nextInt(1_000_000), r, c});
            }
        }
        candidates.sort(Comparator.<int[]>comparingInt(value -> value[0])
                .thenComparingInt(value -> value[1]));
        for (int[] candidate : candidates) {
            int r = candidate[2];
            int c = candidate[3];
            board[r][c] = step + 1;
            if (search(r, c, step + 1, board, random, budget)) {
                return true;
            }
            board[r][c] = 0;
            if (budget[0] <= 0) {
                return false;
            }
        }
        return false;
    }

    private static int onwardDegree(int row, int column, int[][] board) {
        int degree = 0;
        for (int i = 0; i < DR.length; i++) {
            int r = row + DR[i];
            int c = column + DC[i];
            if (inside(r, c) && board[r][c] == 0) {
                degree++;
            }
        }
        return degree;
    }

    private static boolean inside(int row, int column) {
        return row >= 0 && row < SIDE && column >= 0 && column < SIDE;
    }

    private static boolean isKnightMove(int r1, int c1, int r2, int c2) {
        int dr = Math.abs(r1 - r2);
        int dc = Math.abs(c1 - c2);
        return dr * dc == 2 && dr + dc == 3;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
