package Exercise_22._19;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_22_19 extends Application {
    private final TextField[][] cells = new TextField[10][10];
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                TextField field = new TextField();
                field.setPrefColumnCount(2);
                field.setAlignment(Pos.CENTER);
                cells[row][column] = field;
                grid.add(field, column, row);
            }
        }

        Button refresh = new Button("Refresh");
        Button find = new Button("Find Largest Block");
        HBox buttons = new HBox(8, refresh, find);
        buttons.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(buttons);
        root.setCenter(grid);

        refresh.setOnAction(event -> refresh());
        find.setOnAction(event -> findLargestBlock());

        refresh();
        Scene scene = new Scene(root, 420, 420);
        primaryStage.setTitle("Exercise_22_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refresh() {
        for (TextField[] row : cells) {
            for (TextField field : row) {
                field.setText(Integer.toString(random.nextInt(2)));
                field.setStyle("-fx-control-inner-background: white;");
            }
        }
    }

    private void findLargestBlock() {
        int[][] grid = new int[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                grid[row][column] = "1".equals(cells[row][column].getText().trim()) ? 1 : 0;
                cells[row][column].setStyle("-fx-control-inner-background: white;");
            }
        }

        int[][] sizes = new int[10][10];
        int best = 0;
        int bestRow = 0;
        int bestColumn = 0;
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (grid[row][column] == 1) {
                    sizes[row][column] = 1;
                    if (row > 0 && column > 0) {
                        sizes[row][column] += Math.min(
                                sizes[row - 1][column - 1],
                                Math.min(sizes[row - 1][column], sizes[row][column - 1]));
                    }
                    if (sizes[row][column] > best) {
                        best = sizes[row][column];
                        bestRow = row;
                        bestColumn = column;
                    }
                }
            }
        }

        if (best == 0) {
            return;
        }
        for (int row = bestRow - best + 1; row <= bestRow; row++) {
            for (int column = bestColumn - best + 1; column <= bestColumn; column++) {
                cells[row][column].setStyle("-fx-control-inner-background: gold;");
            }
        }
    }
}
