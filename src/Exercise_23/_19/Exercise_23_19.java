package Exercise_23._19;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Exercise_23_19 extends Application {
    private static final int COUNT = 20;
    private static final String BASE_STYLE =
            "-fx-border-color: gray; -fx-padding: 4; -fx-min-width: 34; -fx-alignment: center;";
    private final int[] values = new int[COUNT];
    private final Label[] labels = new Label[COUNT];
    private final HBox arrayBox = new HBox(4);
    private final Label status = new Label();
    private int low;
    private int high;
    private int pivot;
    private boolean searchingHigh = true;
    private boolean finished;

    @Override
    public void start(Stage stage) {
        arrayBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < COUNT; i++) {
            Label label = new Label();
            label.setStyle(BASE_STYLE);
            labels[i] = label;
            arrayBox.getChildren().add(label);
        }

        Button step = new Button("Step");
        Button reset = new Button("Reset");
        step.setOnAction(event -> step());
        reset.setOnAction(event -> reset());

        BorderPane root = new BorderPane(arrayBox);
        HBox controls = new HBox(8, step, reset, status);
        controls.setAlignment(Pos.CENTER);
        root.setBottom(controls);

        reset();
        stage.setTitle("Exercise 23.19: Quick sort partition animation");
        stage.setScene(new Scene(root, 820, 300));
        stage.show();
    }

    private void reset() {
        Random random = new Random();
        for (int i = 0; i < COUNT; i++) {
            values[i] = random.nextInt(999) + 1;
        }
        low = 0;
        high = COUNT - 1;
        pivot = values[0];
        searchingHigh = true;
        finished = false;
        status.setText("Click Step to move low or high, or to swap");
        paint();
    }

    private void step() {
        if (finished) {
            return;
        }
        if (searchingHigh) {
            if (low >= high) {
                finish();
            } else if (values[high] > pivot) {
                high--;
                status.setText("Moved high to " + high);
            } else {
                swap(low, high);
                low++;
                searchingHigh = false;
                status.setText("Swapped and moved low to " + low);
            }
        } else if (low >= high) {
            finish();
        } else if (values[low] < pivot) {
            low++;
            status.setText("Moved low to " + low);
        } else {
            swap(low, high);
            high--;
            searchingHigh = true;
            status.setText("Swapped and moved high to " + high);
        }
        paint();
    }

    private void finish() {
        finished = true;
        status.setText("Partition complete at index " + low + " (pivot " + pivot + ")");
    }

    private void swap(int first, int second) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }

    private void paint() {
        for (int i = 0; i < COUNT; i++) {
            Label label = labels[i];
            label.setText(String.valueOf(values[i]));
            String style = BASE_STYLE;
            if (values[i] == pivot) {
                style += "-fx-background-color: lightyellow;";
            }
            if (i == low && i == high) {
                style += "-fx-background-color: orange;";
            } else if (i == low) {
                style += "-fx-background-color: lightblue;";
            } else if (i == high) {
                style += "-fx-background-color: lightsalmon;";
            }
            label.setStyle(style);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
