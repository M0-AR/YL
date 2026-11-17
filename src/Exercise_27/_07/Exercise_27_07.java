package Exercise_27._07;

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

public class Exercise_27_07 extends Application {
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private final HBox tablePane = new HBox(6);
    private final Label status = new Label();
    private final TextField keyField = new TextField();
    private List<List<Integer>> buckets = new ArrayList<>();
    private Rectangle[] markers = new Rectangle[10];
    private int size;

    @Override
    public void start(Stage primaryStage) {
        Spinner<Integer> capacity = new Spinner<>(4, 32, 10);
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
        Scene scene = new Scene(root, 820, 420);
        primaryStage.setTitle("Exercise_27_07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buildTable(int newCapacity) {
        buckets = new ArrayList<>(newCapacity);
        markers = new Rectangle[newCapacity];
        size = 0;
        tablePane.getChildren().clear();
        for (int i = 0; i < newCapacity; i++) {
            buckets.add(new ArrayList<>());
            Rectangle marker = new Rectangle(46, 8, Color.WHITE);
            marker.setStroke(Color.DARKSLATEBLUE);
            markers[i] = marker;
            VBox column = new VBox(3, new Label(Integer.toString(i)), marker);
            column.setAlignment(Pos.CENTER);
            column.setPrefWidth(60);
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
        for (Rectangle marker : markers) {
            marker.setFill(Color.WHITE);
        }
        if ((size + 1) > buckets.size() * LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        insertKey(key, true);
    }

    private void insertKey(int key, boolean animate) {
        int index = Math.floorMod(Integer.hashCode(key), buckets.size());
        List<Integer> chain = buckets.get(index);
        if (!chain.contains(key)) {
            chain.add(key);
            size++;
        }
        refreshBucket(index);
        status.setText("hash(" + key + ") = " + index + ", chain length " + chain.size());
        if (animate) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(220), event -> markers[index].setFill(Color.ORANGE)),
                    new KeyFrame(Duration.millis(600), event -> markers[index].setFill(Color.LIGHTGREEN)));
            timeline.play();
        } else {
            markers[index].setFill(Color.LIGHTGREEN);
        }
    }

    private void refreshBucket(int index) {
        VBox column = (VBox) tablePane.getChildren().get(index);
        while (column.getChildren().size() > 2) {
            column.getChildren().remove(2);
        }
        for (int value : buckets.get(index)) {
            Label label = new Label(Integer.toString(value));
            label.setStyle("-fx-border-color: #444444; -fx-padding: 2 6 2 6;");
            column.getChildren().add(label);
        }
    }

    private void resize() {
        List<List<Integer>> old = buckets;
        List<Integer> values = new ArrayList<>();
        for (List<Integer> chain : old) {
            values.addAll(chain);
        }
        buildTable(old.size() * 2);
        for (int value : values) {
            insertKey(value, false);
        }
        status.setText("Rehashed to size " + buckets.size());
    }
}
