package Exercise_16._30;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Exercise_16_30 extends Application {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final TextField[][] fields = new TextField[ROWS][COLUMNS];
    private final Label status = new Label();
    private final Random random = new Random();

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(3);
        grid.setVgap(3);
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                TextField field = new TextField(String.valueOf(random.nextInt(10)));
                field.setPrefColumnCount(2);
                field.setAlignment(Pos.CENTER);
                fields[row][column] = field;
                grid.add(field, column, row);
            }
        }

        Button shuffle = new Button("Shuffle");
        Button solve = new Button("Solve");
        shuffle.setOnAction(event -> shuffle());
        solve.setOnAction(event -> solve());
        HBox controls = new HBox(8, shuffle, solve, status);
        controls.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(grid);
        root.setTop(controls);
        stage.setTitle("Exercise 16.30: Consecutive four");
        stage.setScene(new Scene(root, 620, 360));
        stage.show();
    }

    private void shuffle() {
        for (TextField[] row : fields) {
            for (TextField field : row) {
                field.setText(String.valueOf(random.nextInt(10)));
                field.setStyle("");
            }
        }
        status.setText("");
    }

    private void solve() {
        for (TextField[] row : fields) {
            for (TextField field : row) {
                field.setStyle("");
            }
        }
        status.setText("No four consecutive equal numbers");
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (!isNumber(fields[row][column].getText())) {
                    continue;
                }
                int value = Integer.parseInt(fields[row][column].getText());
                for (int[] direction : directions) {
                    int endRow = row + direction[0] * 3;
                    int endColumn = column + direction[1] * 3;
                    if (endRow < 0 || endRow >= ROWS
                            || endColumn < 0 || endColumn >= COLUMNS) {
                        continue;
                    }
                    boolean equal = true;
                    for (int step = 1; step < 4; step++) {
                        String text = fields[row + direction[0] * step]
                                [column + direction[1] * step].getText();
                        if (!isNumber(text) || Integer.parseInt(text) != value) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) {
                        for (int step = 0; step < 4; step++) {
                            fields[row + direction[0] * step]
                                    [column + direction[1] * step]
                                    .setStyle("-fx-control-inner-background: yellow;");
                        }
                        status.setText("Four consecutive " + value + "s found");
                        return;
                    }
                }
            }
        }
    }

    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text.trim());
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
