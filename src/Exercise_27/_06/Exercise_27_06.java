package Exercise_27._06;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_27_06 extends Application {
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private final HBox tablePane = new HBox(4);
    private final Label status = new Label();
    private final TextField keyField = new TextField();
    private Integer[] table = new Integer[10];
    private Rectangle[] cells = new Rectangle[10];
    private Label[] values = new Label[10];
    private int size;

    @Override
    public void start(Stage primaryStage) {
        Spinner<Integer> capacity = new Spinner<>(4, 64, 10);
        capacity.setPrefWidth(80);
        Button insert = new Button("Insert");
        Button clear = new Button("Clear");
        HBox controls = new HBox(8, new Label("Capacity:"), capacity, new Label("Key:"),
                keyField, insert, clear, status);
        controls.setAlignment(Pos.CENTER);
        keyField.setPrefColumnCount(5);

        tablePane.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(tablePane);

        insert.setOnAction(event -> insert());
        clear.setOnAction(event -> reset(capacity.getValue()));
        capacity.valueProperty().addListener((observable, oldValue, newValue) ->
                reset(newValue));

        buildTable(10);
        Scene scene = new Scene(root, 820, 360);
        primaryStage.setTitle("Exercise_27_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buildTable(int newCapacity) {
        table = new Integer[newCapacity];
        cells = new Rectangle[newCapacity];
        values = new Label[newCapacity];
        size = 0;
        tablePane.getChildren().clear();
        for (int i = 0; i < newCapacity; i++) {
            Rectangle cell = new Rectangle(46, 46, Color.WHITE);
            cell.setStroke(Color.DARKSLATEBLUE);
            Label value = new Label("");
            Label index = new Label(Integer.toString(i));
            VBox column = new VBox(2, index, cell, value);
            column.setAlignment(Pos.CENTER);
            cells[i] = cell;
            values[i] = value;
            tablePane.getChildren().add(column);
        }
        status.setText("Table size " + newCapacity);
    }

    private void reset(int capacity) {
        buildTable(capacity);
        keyField.clear();
    }

    private void insert() {
        int key;
        try {
            key = Integer.parseInt(keyField.getText().trim());
        } catch (NumberFormatException ex) {
            status.setText("Enter a whole number");
            return;
        }
        keyField.clear();
        for (Rectangle cell : cells) {
            cell.setFill(Color.WHITE);
        }
        if ((size + 1) > capacity() * LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        insertKey(key, true);
    }

    private void insertKey(int key, boolean animate) {
        int capacity = capacity();
        int start = Math.floorMod(Integer.hashCode(key), capacity);
        List<Integer> probes = new ArrayList<>();
        int placed = -1;
        for (int step = 0; step < capacity; step++) {
            int index = (start + step) % capacity;
            probes.add(index);
            if (table[index] == null || table[index] == key) {
                placed = index;
                break;
            }
        }
        if (placed < 0) {
            resize();
            insertKey(key, animate);
            return;
        }
        boolean added = table[placed] == null;
        table[placed] = key;
        values[placed].setText(Integer.toString(key));
        if (added) {
            size++;
        }
        status.setText("hash(" + key + ") = " + start + ", probes: " + probes.size());
        if (animate) {
            animate(probes, placed);
        } else {
            cells[placed].setFill(Color.HONEYDEW);
        }
    }

    private void animate(List<Integer> probes, int placed) {
        Timeline timeline = new Timeline();
        for (int i = 0; i < probes.size(); i++) {
            int index = probes.get(i);
            Color color = index == placed ? Color.LIGHTGREEN : Color.ORANGE;
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(260L * (i + 1)), event -> cells[index].setFill(color)));
        }
        timeline.play();
    }

    private void resize() {
        Integer[] old = table;
        int capacity = capacity() * 2;
        buildTable(capacity);
        for (Integer value : old) {
            if (value != null) {
                insertKey(value, false);
            }
        }
        status.setText("Rehashed to size " + capacity);
    }

    private int capacity() {
        return table.length;
    }
}
