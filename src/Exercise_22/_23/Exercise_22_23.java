package Exercise_22._23;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_22_23 extends Application {
    private static final int SIZE = 8;

    @Override
    public void start(Stage primaryStage) {
        List<int[]> solutions = new ArrayList<>();
        solve(0, new int[SIZE], new boolean[SIZE], new boolean[2 * SIZE - 1],
                new boolean[2 * SIZE - 1], solutions);

        HBox board = new HBox(12);
        board.setAlignment(Pos.CENTER);
        for (int i = 0; i < solutions.size(); i++) {
            board.getChildren().add(solutionPane(i + 1, solutions.get(i)));
        }

        ScrollPane scroll = new ScrollPane(board);
        scroll.setFitToHeight(true);
        Scene scene = new Scene(scroll, 900, 620);
        primaryStage.setTitle("Exercise_22_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox solutionPane(int number, int[] columns) {
        Label label = new Label("Solution " + number);
        GridPane grid = new GridPane();
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Rectangle square = new Rectangle(24, 24,
                        (row + column) % 2 == 0 ? Color.BEIGE : Color.SADDLEBROWN);
                square.setStroke(Color.BLACK);
                StackPane cell = new StackPane(square);
                if (columns[row] == column) {
                    cell.getChildren().add(new Circle(8, Color.BLACK));
                }
                grid.add(cell, column, row);
            }
        }
        VBox pane = new VBox(4, label, grid);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void solve(int row, int[] columns, boolean[] usedColumns,
            boolean[] usedDown, boolean[] usedUp, List<int[]> solutions) {
        if (row == SIZE) {
            solutions.add(columns.clone());
            return;
        }
        for (int column = 0; column < SIZE; column++) {
            int down = row + column;
            int up = row - column + SIZE - 1;
            if (!usedColumns[column] && !usedDown[down] && !usedUp[up]) {
                usedColumns[column] = true;
                usedDown[down] = true;
                usedUp[up] = true;
                columns[row] = column;
                solve(row + 1, columns, usedColumns, usedDown, usedUp, solutions);
                usedColumns[column] = false;
                usedDown[down] = false;
                usedUp[up] = false;
            }
        }
    }
}
