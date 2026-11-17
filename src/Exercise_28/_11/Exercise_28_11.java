package Exercise_28._11;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Exercise_28_11 extends Application {
    private static final int SIDE = 3;
    private static final int STATES = 1 << (SIDE * SIDE);
    private final int[] masks = createMasks();
    private final int[] parent = new int[STATES];
    private final int[] flipMove = new int[STATES];
    private final Button[] cells = new Button[SIDE * SIDE];
    private final Label status = new Label();
    private final TextArea solution = new TextArea();
    private int state;

    @Override
    public void start(Stage stage) {
        computeParents();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);
        for (int i = 0; i < cells.length; i++) {
            int index = i;
            Button cell = new Button();
            cell.setPrefSize(64, 64);
            cell.setOnAction(event -> {
                state ^= 1 << index;
                updateCells();
            });
            cells[i] = cell;
            grid.add(cell, i % SIDE, i / SIDE);
        }

        Button solve = new Button("Solve");
        Button reset = new Button("Reset");
        solve.setOnAction(event -> solve());
        reset.setOnAction(event -> {
            state = 0;
            solution.clear();
            status.setText("Click a coin to set the initial pattern");
            updateCells();
        });

        solution.setEditable(false);
        solution.setPrefRowCount(5);
        solution.setPrefColumnCount(24);
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setTop(new Label("Click coins to flip them, then press Solve"));
        root.setBottom(new javafx.scene.layout.VBox(8, new HBox(8, solve, reset), status, solution));
        BorderPane.setAlignment(status, Pos.CENTER);

        stage.setTitle("Exercise 28.11: Nine tails");
        stage.setScene(new Scene(root, 380, 460));
        stage.show();
        updateCells();
        status.setText("Click a coin to set the initial pattern");
    }

    private void updateCells() {
        for (int i = 0; i < cells.length; i++) {
            boolean head = (state & (1 << i)) != 0;
            cells[i].setText(head ? "H" : "T");
            cells[i].setStyle(head ? "-fx-text-fill: red;" : "-fx-text-fill: black;");
        }
    }

    private void solve() {
        if (parent[state] == -2) {
            status.setText("No solution exists for this pattern");
            solution.clear();
            return;
        }
        List<Integer> moves = new ArrayList<>();
        for (int current = state; current != 0; current = parent[current]) {
            moves.add(flipMove[current]);
        }
        status.setText("The solution has " + moves.size() + " flips");
        StringBuilder text = new StringBuilder();
        int board = state;
        for (int move : moves) {
            board ^= masks[move];
            text.append("Flip cell ").append(move).append(": ")
                    .append(boardText(board)).append('\n');
        }
        solution.setText(text.toString());
    }

    private String boardText(int board) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < SIDE * SIDE; i++) {
            if (i > 0 && i % SIDE == 0) {
                text.append(' ');
            }
            text.append((board & (1 << i)) != 0 ? 'H' : 'T');
        }
        return text.toString();
    }

    private static int[] createMasks() {
        int[] result = new int[SIDE * SIDE];
        for (int i = 0; i < result.length; i++) {
            int row = i / SIDE;
            int column = i % SIDE;
            int mask = 0;
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (Math.abs(dr) + Math.abs(dc) != 1) {
                        continue;
                    }
                    int r = row + dr;
                    int c = column + dc;
                    if (r >= 0 && r < SIDE && c >= 0 && c < SIDE) {
                        mask |= 1 << (r * SIDE + c);
                    }
                }
            }
            result[i] = mask;
        }
        return result;
    }

    private void computeParents() {
        Arrays.fill(parent, -2);
        parent[0] = -1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int move = 0; move < masks.length; move++) {
                int next = current ^ masks[move];
                if (parent[next] == -2) {
                    parent[next] = current;
                    flipMove[next] = move;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
