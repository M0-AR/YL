package Exercise_20._17;

import Exercise_20._19.Exercise_20_19;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_20_17 extends Application {
    private final TextField[] fields = new TextField[4];
    private final Label result = new Label();

    @Override
    public void start(Stage stage) {
        HBox inputs = new HBox(8);
        inputs.setAlignment(Pos.CENTER);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new TextField();
            fields[i].setPrefColumnCount(3);
            fields[i].setPromptText("1-13");
            inputs.getChildren().add(fields[i]);
        }

        Button solve = new Button("Solve");
        solve.setOnAction(event -> solve());
        HBox controls = new HBox(8, inputs, solve);
        controls.setAlignment(Pos.CENTER);

        result.setWrapText(true);
        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setBottom(result);
        stage.setTitle("Exercise 20.17: 24-point verifier");
        stage.setScene(new Scene(root, 560, 220));
        stage.show();
    }

    private void solve() {
        int[] numbers = new int[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                numbers[i] = Integer.parseInt(fields[i].getText().trim());
                if (numbers[i] < 1 || numbers[i] > 13) {
                    result.setText("Enter four values between 1 and 13");
                    return;
                }
            }
        } catch (NumberFormatException exception) {
            result.setText("Enter four integer values");
            return;
        }
        String expression = Exercise_20_19.solve(numbers);
        result.setText(expression == null
                ? "No solution"
                : expression + " = 24");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
