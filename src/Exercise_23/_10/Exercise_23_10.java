package Exercise_23._10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_23_10 extends Application {
    private final List<Integer> heap = new ArrayList<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label("Insert or delete a value");

    @Override
    public void start(Stage stage) {
        TextField valueField = new TextField();
        valueField.setPromptText("Value");
        valueField.setPrefColumnCount(8);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");

        insert.setOnAction(event -> {
            try {
                int value = Integer.parseInt(valueField.getText().trim());
                heap.add(value);
                siftUp();
                status.setText("Inserted " + value);
                valueField.clear();
                draw();
            } catch (NumberFormatException exception) {
                status.setText("Enter an integer");
            }
        });

        delete.setOnAction(event -> {
            if (heap.isEmpty()) {
                status.setText("Heap is empty");
                return;
            }
            int removed = heap.get(0);
            int last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                siftDown();
            }
            status.setText("Deleted " + removed);
            draw();
        });

        canvas.setPrefSize(760, 420);
        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());

        HBox controls = new HBox(8, valueField, insert, delete);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setTop(controls);
        root.setBottom(status);

        stage.setTitle("Exercise 23.10: Heap visualization");
        stage.setScene(new Scene(root, 780, 480));
        stage.show();
        draw();
    }

    private void siftUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) <= heap.get(parent)) {
                return;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown() {
        int index = 0;
        while (true) {
            int left = index * 2 + 1;
            int right = left + 1;
            int largest = index;
            if (left < heap.size() && heap.get(left) > heap.get(largest)) {
                largest = left;
            }
            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
                largest = right;
            }
            if (largest == index) {
                return;
            }
            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int first, int second) {
        int temp = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    }

    private void draw() {
        canvas.getChildren().clear();
        if (heap.isEmpty()) {
            return;
        }
        double width = Math.max(canvas.getWidth(), canvas.getPrefWidth());
        double height = Math.max(canvas.getHeight(), canvas.getPrefHeight());
        int lastIndex = heap.size() - 1;
        int levels = 31 - Integer.numberOfLeadingZeros(lastIndex + 1);
        double[] xs = new double[heap.size()];
        double[] ys = new double[heap.size()];
        for (int i = 0; i < heap.size(); i++) {
            int level = 31 - Integer.numberOfLeadingZeros(i + 1);
            int slots = 1 << level;
            int slot = i - (slots - 1);
            xs[i] = width * (2 * slot + 1) / (2.0 * slots);
            ys[i] = height * (level + 0.8) / (levels + 1.4);
        }
        for (int i = 1; i < heap.size(); i++) {
            int parent = (i - 1) / 2;
            Line line = new Line(xs[parent], ys[parent], xs[i], ys[i]);
            line.setStroke(Color.GRAY);
            canvas.getChildren().add(line);
        }
        for (int i = 0; i < heap.size(); i++) {
            Circle circle = new Circle(xs[i], ys[i], 18, Color.LIGHTYELLOW);
            circle.setStroke(Color.DARKSLATEGRAY);
            Text text = new Text(xs[i] - 7, ys[i] + 5, String.valueOf(heap.get(i)));
            canvas.getChildren().addAll(circle, text);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
