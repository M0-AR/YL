package Exercise_23._15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise_23_15 extends Application {
    private static final int COUNT = 20;
    private final int[] values = new int[COUNT];
    private final List<Rectangle> bars = new ArrayList<>();
    private final HBox barBox = new HBox(3);
    private final Label status = new Label();
    private int nextIndex;
    private int lastSwapped = -1;
    private boolean finished;

    @Override
    public void start(Stage stage) {
        barBox.setAlignment(Pos.BOTTOM_CENTER);
        Button step = new Button("Step");
        Button reset = new Button("Reset");
        step.setOnAction(event -> step());
        reset.setOnAction(event -> reset());

        BorderPane root = new BorderPane(barBox);
        HBox controls = new HBox(8, step, reset, status);
        controls.setAlignment(Pos.CENTER);
        root.setBottom(controls);

        reset();
        stage.setTitle("Exercise 23.15: Selection sort animation");
        stage.setScene(new Scene(root, 780, 460));
        stage.show();
    }

    private void reset() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= COUNT; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        barBox.getChildren().clear();
        bars.clear();
        for (int i = 0; i < COUNT; i++) {
            values[i] = numbers.get(i);
            Rectangle bar = new Rectangle(30, values[i] * 14);
            bar.setFill(Color.CORNFLOWERBLUE);
            bars.add(bar);
            barBox.getChildren().add(bar);
        }
        nextIndex = 0;
        lastSwapped = -1;
        finished = false;
        status.setText("Click Step to perform one outer iteration");
        paint();
    }

    private void step() {
        if (finished) {
            return;
        }
        int smallest = nextIndex;
        for (int i = nextIndex + 1; i < COUNT; i++) {
            if (values[i] < values[smallest]) {
                smallest = i;
            }
        }
        int temp = values[nextIndex];
        values[nextIndex] = values[smallest];
        values[smallest] = temp;
        lastSwapped = smallest;
        nextIndex++;
        if (nextIndex >= COUNT) {
            finished = true;
            status.setText("The array is sorted");
        } else {
            status.setText("Sorted " + nextIndex + " of " + COUNT + " elements");
        }
        paint();
    }

    private void paint() {
        for (int i = 0; i < COUNT; i++) {
            Rectangle bar = bars.get(i);
            bar.setHeight(values[i] * 14);
            if (finished || i < nextIndex) {
                bar.setFill(Color.GREEN);
            } else if (i == lastSwapped) {
                bar.setFill(Color.ORANGERED);
            } else {
                bar.setFill(Color.CORNFLOWERBLUE);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
