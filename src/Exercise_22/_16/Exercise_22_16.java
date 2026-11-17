package Exercise_22._16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_22_16 extends Application {
    private final List<Integer> numbers = new ArrayList<>();
    private final List<Rectangle> bars = new ArrayList<>();
    private final Label status = new Label("Enter a search key and click Step");
    private final TextField keyField = new TextField();
    private int index;

    @Override
    public void start(Stage primaryStage) {
        HBox controls = new HBox(8, status, new Label("Key:"), keyField);
        controls.setAlignment(Pos.CENTER);
        keyField.setPrefColumnCount(4);

        Button step = new Button("Step");
        Button reset = new Button("Reset");
        HBox buttons = new HBox(8, step, reset);
        buttons.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(new VBox(6, controls, buttons));
        root.setCenter(createHistogram());

        step.setOnAction(event -> step());
        reset.setOnAction(event -> reset());

        reset();
        Scene scene = new Scene(root, 820, 360);
        primaryStage.setTitle("Exercise_22_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHistogram() {
        HBox pane = new HBox(4);
        pane.setAlignment(Pos.BOTTOM_CENTER);
        for (int value = 1; value <= 20; value++) {
            Rectangle bar = new Rectangle(30, value * 12, Color.STEELBLUE);
            Label label = new Label(Integer.toString(value));
            VBox column = new VBox(2, label, bar);
            column.setAlignment(Pos.BOTTOM_CENTER);
            bars.add(bar);
            pane.getChildren().add(column);
        }
        return pane;
    }

    private void reset() {
        numbers.clear();
        for (int value = 1; value <= 20; value++) {
            numbers.add(value);
        }
        Collections.shuffle(numbers);
        index = 0;
        keyField.setEditable(true);
        keyField.clear();
        status.setText("Enter a search key and click Step");
        for (Rectangle bar : bars) {
            bar.setFill(Color.STEELBLUE);
        }
    }

    private void step() {
        int key;
        try {
            key = Integer.parseInt(keyField.getText().trim());
        } catch (NumberFormatException ex) {
            status.setText("Enter a whole number");
            return;
        }
        keyField.setEditable(false);
        if (index >= numbers.size()) {
            status.setText("Not found");
            return;
        }
        bars.get(index).setFill(Color.STEELBLUE);
        int value = numbers.get(index);
        if (value == key) {
            bars.get(index).setFill(Color.GREEN);
            status.setText("Found at index " + index);
            index = numbers.size();
        } else {
            bars.get(index).setFill(Color.ORANGE);
            status.setText("Checked index " + index + " (" + value + ")");
            index++;
            if (index == numbers.size()) {
                status.setText("Not found");
            }
        }
    }
}
