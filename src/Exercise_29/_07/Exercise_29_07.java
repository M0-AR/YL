package Exercise_29._07;

import Exercise_29._06.WeightedTailModel16;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Exercise_29_07 extends Application {
    private static final int SIDE = 4;
    private static final int CELLS = SIDE * SIDE;
    private final Button[] cells = new Button[CELLS];
    private final Label status = new Label();
    private final TextArea solution = new TextArea();
    private WeightedTailModel16 model;
    private int state;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);
        for (int i = 0; i < cells.length; i++) {
            int index = i;
            Button cell = new Button();
            cell.setPrefSize(54, 54);
            cell.setOnAction(event -> {
                state ^= 1 << index;
                updateCells();
            });
            cells[i] = cell;
            grid.add(cell, i % SIDE, i / SIDE);
        }

        Button load = new Button("Load model");
        Button solve = new Button("Solve");
        load.setOnAction(event -> loadModel());
        solve.setOnAction(event -> solve());

        solution.setEditable(false);
        solution.setPrefRowCount(6);
        solution.setPrefColumnCount(26);
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setTop(new Label("Load WeightedTailModel16.dat, set a pattern, then Solve"));
        root.setBottom(new VBox(8, new HBox(8, load, solve), status, solution));

        stage.setTitle("Exercise 29.07: Weighted 4 x 4 16 tails");
        stage.setScene(new Scene(root, 440, 540));
        stage.show();
        updateCells();
        status.setText("Click a coin to set the initial pattern");
    }

    private void loadModel() {
        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream("WeightedTailModel16.dat"))) {
            model = (WeightedTailModel16) input.readObject();
            status.setText("Model loaded");
        } catch (IOException | ClassNotFoundException exception) {
            status.setText("Could not load WeightedTailModel16.dat: " + exception.getMessage());
        }
    }

    private void updateCells() {
        for (int i = 0; i < cells.length; i++) {
            boolean head = (state & (1 << i)) != 0;
            cells[i].setText(head ? "H" : "T");
            cells[i].setStyle(head ? "-fx-text-fill: red;" : "-fx-text-fill: black;");
        }
    }

    private void solve() {
        if (model == null) {
            status.setText("Load the model first");
            return;
        }
        if (!model.isSolvable(state)) {
            status.setText("No solution exists for this pattern");
            solution.clear();
            return;
        }
        List<Integer> moves = model.getSolution(state);
        status.setText("Flips: " + moves.size() + ", cost: " + model.getCost(state));
        int[] masks = WeightedTailModel16.createMasks();
        int board = state;
        StringBuilder text = new StringBuilder();
        for (int move : moves) {
            board ^= masks[move];
            text.append("Flip ").append(move).append(": ").append(boardText(board)).append('\n');
        }
        solution.setText(text.toString());
    }

    private String boardText(int board) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < CELLS; i++) {
            if (i > 0 && i % SIDE == 0) {
                text.append(' ');
            }
            text.append((board & (1 << i)) != 0 ? 'H' : 'T');
        }
        return text.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
