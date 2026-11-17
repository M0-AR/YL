package Exercise_23._17;

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
import java.util.List;
import java.util.Random;

public class Exercise_23_17 extends Application {
    private static final int COUNT = 20;
    private final int[] values = new int[COUNT];
    private final Label[] arrayLabels = new Label[COUNT];
    private final List<List<Integer>> buckets = new ArrayList<>();
    private final List<VBox> bucketBoxes = new ArrayList<>();
    private final HBox arrayBox = new HBox(4);
    private final HBox bucketBox = new HBox(8);
    private final Label status = new Label();
    private int index;
    private int place = 1;
    private boolean distributing = true;
    private boolean finished;
    private int lastBucket = -1;

    @Override
    public void start(Stage stage) {
        arrayBox.setAlignment(Pos.CENTER);
        bucketBox.setAlignment(Pos.TOP_CENTER);
        for (int digit = 0; digit < 10; digit++) {
            Label header = new Label(String.valueOf(digit));
            VBox column = new VBox(2, header);
            column.setAlignment(Pos.TOP_CENTER);
            column.setStyle("-fx-border-color: gray; -fx-padding: 2;");
            bucketBoxes.add(column);
            buckets.add(new ArrayList<>());
            bucketBox.getChildren().add(column);
        }
        for (int i = 0; i < COUNT; i++) {
            Label label = new Label();
            label.setStyle("-fx-border-color: gray; -fx-padding: 3; -fx-min-width: 34; -fx-alignment: center;");
            arrayLabels[i] = label;
            arrayBox.getChildren().add(label);
        }

        Button step = new Button("Step");
        Button reset = new Button("Reset");
        step.setOnAction(event -> step());
        reset.setOnAction(event -> reset());

        VBox center = new VBox(18, arrayBox, bucketBox);
        center.setAlignment(Pos.TOP_CENTER);
        BorderPane root = new BorderPane(center);
        HBox controls = new HBox(8, step, reset, status);
        controls.setAlignment(Pos.CENTER);
        root.setBottom(controls);

        reset();
        stage.setTitle("Exercise 23.17: Radix sort animation");
        stage.setScene(new Scene(root, 820, 520));
        stage.show();
    }

    private void reset() {
        Random random = new Random();
        for (int i = 0; i < COUNT; i++) {
            values[i] = random.nextInt(1001);
        }
        index = 0;
        place = 1;
        distributing = true;
        finished = false;
        lastBucket = -1;
        for (List<Integer> bucket : buckets) {
            bucket.clear();
        }
        refreshArray();
        refreshBuckets();
        status.setText("Click Step to place a number in a bucket");
    }

    private void step() {
        if (finished) {
            return;
        }
        if (distributing) {
            int value = values[index];
            int digit = value / place % 10;
            buckets.get(digit).add(value);
            lastBucket = digit;
            index++;
            if (index == COUNT) {
                distributing = false;
                status.setText("All numbers placed. Click Step to collect them");
            } else {
                status.setText("Placed " + value + " in bucket " + digit);
            }
        } else {
            int position = 0;
            for (List<Integer> bucket : buckets) {
                for (int value : bucket) {
                    values[position++] = value;
                }
                bucket.clear();
            }
            place *= 10;
            lastBucket = -1;
            index = 0;
            if (place > 1000) {
                finished = true;
                status.setText("The array is sorted");
            } else {
                distributing = true;
                status.setText("Collected numbers. Click Step to place them again");
            }
            refreshArray();
        }
        refreshBuckets();
    }

    private void refreshArray() {
        for (int i = 0; i < COUNT; i++) {
            arrayLabels[i].setText(String.valueOf(values[i]));
            arrayLabels[i].setStyle("-fx-border-color: gray; -fx-padding: 3; -fx-min-width: 34; -fx-alignment: center;");
        }
    }

    private void refreshBuckets() {
        for (int digit = 0; digit < buckets.size(); digit++) {
            VBox column = bucketBoxes.get(digit);
            column.getChildren().remove(1, column.getChildren().size());
            for (int value : buckets.get(digit)) {
                Label label = new Label(String.valueOf(value));
                label.setStyle("-fx-border-color: gray; -fx-padding: 1; -fx-min-width: 40; -fx-alignment: center;");
                if (digit == lastBucket && value == buckets.get(digit).get(buckets.get(digit).size() - 1)) {
                    label.setStyle(label.getStyle() + "-fx-text-fill: red;");
                }
                column.getChildren().add(label);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
