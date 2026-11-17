package Exercise_23._18;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exercise_23_18 extends Application {
    private static final int SIZE = 8;
    private static final String BASE_STYLE =
            "-fx-border-color: gray; -fx-padding: 4; -fx-min-width: 38; -fx-alignment: center;";
    private final int[] first = new int[SIZE];
    private final int[] second = new int[SIZE];
    private final Label[] firstLabels = new Label[SIZE];
    private final Label[] secondLabels = new Label[SIZE];
    private final HBox firstBox = new HBox(4);
    private final HBox secondBox = new HBox(4);
    private final HBox tempBox = new HBox(4);
    private final Label status = new Label();
    private int firstIndex;
    private int secondIndex;
    private boolean finished;

    @Override
    public void start(Stage stage) {
        for (int i = 0; i < SIZE; i++) {
            firstLabels[i] = createLabel();
            secondLabels[i] = createLabel();
            firstBox.getChildren().add(firstLabels[i]);
            secondBox.getChildren().add(secondLabels[i]);
        }
        firstBox.setAlignment(Pos.CENTER);
        secondBox.setAlignment(Pos.CENTER);
        tempBox.setAlignment(Pos.CENTER);

        VBox center = new VBox(14,
                new Label("List 1"), firstBox,
                new Label("List 2"), secondBox,
                new Label("Temp"), tempBox);
        center.setAlignment(Pos.CENTER);

        Button step = new Button("Step");
        Button reset = new Button("Reset");
        step.setOnAction(event -> step());
        reset.setOnAction(event -> reset());

        BorderPane root = new BorderPane(center);
        HBox controls = new HBox(8, step, reset, status);
        controls.setAlignment(Pos.CENTER);
        root.setBottom(controls);

        reset();
        stage.setTitle("Exercise 23.18: Merge animation");
        stage.setScene(new Scene(root, 720, 480));
        stage.show();
    }

    private Label createLabel() {
        Label label = new Label();
        label.setStyle(BASE_STYLE);
        return label;
    }

    private void reset() {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            first[i] = random.nextInt(999) + 1;
            second[i] = random.nextInt(999) + 1;
        }
        Arrays.sort(first);
        Arrays.sort(second);
        for (int i = 0; i < SIZE; i++) {
            firstLabels[i].setText(String.valueOf(first[i]));
            firstLabels[i].setStyle(BASE_STYLE);
            secondLabels[i].setText(String.valueOf(second[i]));
            secondLabels[i].setStyle(BASE_STYLE);
        }
        tempBox.getChildren().clear();
        firstIndex = 0;
        secondIndex = 0;
        finished = false;
        status.setText("Click Step to move the next element to temp");
    }

    private void step() {
        if (finished) {
            return;
        }
        int value;
        if (firstIndex < SIZE && (secondIndex >= SIZE || first[firstIndex] <= second[secondIndex])) {
            value = first[firstIndex];
            firstLabels[firstIndex].setStyle(BASE_STYLE + "-fx-text-fill: lightgray;");
            firstIndex++;
        } else {
            value = second[secondIndex];
            secondLabels[secondIndex].setStyle(BASE_STYLE + "-fx-text-fill: lightgray;");
            secondIndex++;
        }
        Label label = createLabel();
        label.setText(String.valueOf(value));
        label.setStyle(BASE_STYLE + "-fx-text-fill: red;");
        tempBox.getChildren().add(label);
        status.setText("Moved " + value + " to temp");
        if (firstIndex >= SIZE && secondIndex >= SIZE) {
            finished = true;
            status.setText("The merge is complete");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
