package Exercise_18._32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_18_32 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label status = new Label("Click a cell to choose a starting square.");
        KnightTourPane board = new KnightTourPane(status);
        Button solve = new Button("Solve");
        solve.setOnAction(e -> board.solve());

        HBox controls = new HBox(10, solve, status);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(board);
        root.setBottom(controls);

        Scene scene = new Scene(root, 420, 460);
        primaryStage.setTitle("Exercise_18_32");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static class KnightTourPane extends Pane {
        static final int SIZE = 8;
        static final int CELL = 50;

        private final Text[][] numbers = new Text[SIZE][SIZE];
        private final Polyline path = new Polyline();
        private final Rectangle startMarker = new Rectangle(CELL, CELL);
        private final Label status;
        private int startRow;
        private int startCol;

        KnightTourPane(Label status) {
            this.status = status;
            setPrefSize(SIZE * CELL, SIZE * CELL);
            setMinSize(SIZE * CELL, SIZE * CELL);
            setMaxSize(SIZE * CELL, SIZE * CELL);

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    Rectangle cell = new Rectangle(
                            col * CELL, row * CELL, CELL, CELL);
                    cell.setFill(Color.WHITE);
                    cell.setStroke(Color.GRAY);

                    int r = row;
                    int c = col;
                    cell.setOnMouseClicked(e -> {
                        startRow = r;
                        startCol = c;
                        startMarker.setX(c * CELL);
                        startMarker.setY(r * CELL);
                        clearSolution();
                        status.setText("Start: (" + r + ", " + c
                                + "). Click Solve.");
                    });

                    getChildren().add(cell);
                }
            }

            startMarker.setFill(Color.web("#d6ebff"));
            startMarker.setStroke(Color.DODGERBLUE);
            startMarker.setStrokeWidth(2);
            startMarker.setMouseTransparent(true);
            getChildren().add(startMarker);

            path.setStroke(Color.RED);
            path.setStrokeWidth(2);
            path.setFill(Color.TRANSPARENT);
            path.setMouseTransparent(true);
            getChildren().add(path);

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    Text text = new Text();
                    text.setFont(Font.font(10));
                    text.setFill(Color.BLACK);
                    text.setMouseTransparent(true);
                    text.setX(col * CELL + 4);
                    text.setY(row * CELL + 15);
                    numbers[row][col] = text;
                    getChildren().add(text);
                }
            }
        }

        void solve() {
            clearSolution();
            Tour tour = TourSolver.findTour(startRow, startCol);

            if (tour == null) {
                status.setText("No tour found.");
                return;
            }

            for (int[] cell : tour.path) {
                path.getPoints().addAll(
                        cell[1] * CELL + CELL / 2.0,
                        cell[0] * CELL + CELL / 2.0);
                numbers[cell[0]][cell[1]].setText(
                        String.valueOf(tour.order[cell[0]][cell[1]]));
            }

            status.setText("Tour found. Click a cell to choose a new start.");
        }

        private void clearSolution() {
            path.getPoints().clear();
            for (Text[] row : numbers) {
                for (Text text : row) {
                    text.setText("");
                }
            }
        }
    }

    static class TourSolver {
        private static final int SIZE = KnightTourPane.SIZE;
        private static final int[] ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
        private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

        static Tour findTour(int startRow, int startCol) {
            if (startRow < 0 || startRow >= SIZE
                    || startCol < 0 || startCol >= SIZE) {
                return null;
            }

            int[][] order = new int[SIZE][SIZE];
            for (int[] row : order) {
                Arrays.fill(row, -1);
            }

            order[startRow][startCol] = 0;
            List<int[]> path = new ArrayList<>();
            path.add(new int[]{startRow, startCol});

            long deadline = System.nanoTime() + 5_000_000_000L;
            boolean found = search(startRow, startCol, 1, order, path, deadline);
            return found ? new Tour(order, path) : null;
        }

        private static boolean search(int row, int col, int step, int[][] order,
                                      List<int[]> path, long deadline) {
            if (step == SIZE * SIZE) {
                return true;
            }
            if (System.nanoTime() > deadline) {
                return false;
            }

            List<int[]> candidates = new ArrayList<>();
            for (int i = 0; i < ROW_MOVES.length; i++) {
                int nextRow = row + ROW_MOVES[i];
                int nextCol = col + COL_MOVES[i];
                if (nextRow >= 0 && nextRow < SIZE
                        && nextCol >= 0 && nextCol < SIZE
                        && order[nextRow][nextCol] == -1) {
                    candidates.add(new int[]{nextRow, nextCol,
                            onwardDegree(nextRow, nextCol, order)});
                }
            }

            candidates.sort(Comparator.comparingInt(candidate -> candidate[2]));

            for (int[] candidate : candidates) {
                int nextRow = candidate[0];
                int nextCol = candidate[1];

                order[nextRow][nextCol] = step;
                path.add(new int[]{nextRow, nextCol});

                if (search(nextRow, nextCol, step + 1, order, path, deadline)) {
                    return true;
                }

                path.remove(path.size() - 1);
                order[nextRow][nextCol] = -1;
            }

            return false;
        }

        private static int onwardDegree(int row, int col, int[][] order) {
            int count = 0;

            for (int i = 0; i < ROW_MOVES.length; i++) {
                int nextRow = row + ROW_MOVES[i];
                int nextCol = col + COL_MOVES[i];
                if (nextRow >= 0 && nextRow < SIZE
                        && nextCol >= 0 && nextCol < SIZE
                        && order[nextRow][nextCol] == -1) {
                    count++;
                }
            }

            return count;
        }
    }

    static class Tour {
        final int[][] order;
        final List<int[]> path;

        Tour(int[][] order, List<int[]> path) {
            this.order = order;
            this.path = path;
        }
    }
}
