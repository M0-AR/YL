package Exercise_22._18;

import java.util.ArrayList;
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

public class Exercise_22_18 extends Application {
    private final List<Rectangle> bars = new ArrayList<>();
    private final Label status = new Label("Enter a search key and click Step");
    private final TextField keyField = new TextField();
    private int low;
    private int high;
    private int key;
    private boolean running;

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
        Scene scene = new Scene(root, 820, 340);
        primaryStage.setTitle("Exercise_22_18");
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
        low = 0;
        high = 19;
        running = false;
        keyField.setEditable(true);
        keyField.clear();
        status.setText("Enter a search key and click Step");
        for (Rectangle bar : bars) {
            bar.setFill(Color.STEELBLUE);
        }
    }

    private void step() {
        if (!running) {
            try {
                key = Integer.parseInt(keyField.getText().trim());
            } catch (NumberFormatException ex) {
                status.setText("Enter a whole number");
                return;
            }
            if (key < 1 || key > 20) {
                status.setText("The key must be between 1 and 20");
                return;
            }
            running = true;
            keyField.setEditable(false);
        }

        for (int i = 0; i < bars.size(); i++) {
            bars.get(i).setFill(i >= low && i <= high ? Color.LIGHTGRAY : Color.STEELBLUE);
        }

        if (low > high) {
            status.setText("Not found");
            return;
        }

        int middle = (low + high) / 2;
        bars.get(middle).setFill(Color.BLACK);
        if (middle + 1 == key) {
            bars.get(middle).setFill(Color.GREEN);
            status.setText("Found at index " + middle);
            low = bars.size();
            high = -1;
        } else if (middle + 1 < key) {
            status.setText("key > " + (middle + 1) + ", search the right half");
            low = middle + 1;
        } else {
            status.setText("key < " + (middle + 1) + ", search the left half");
            high = middle - 1;
        }
        if (low > high && !status.getText().startsWith("Found")) {
            status.setText(status.getText() + " — not found");
        }
    }
}
