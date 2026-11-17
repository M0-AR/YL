package Exercise_24._09;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Exercise_24_09 extends Application {
    private final ArrayList<Integer> list = new ArrayList<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();
    private final TextField indexField = new TextField();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(780, 240);
        for (int i = 10; i <= 40; i += 10) {
            list.add(i);
        }
        valueField.setPrefColumnCount(4);
        indexField.setPrefColumnCount(4);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        insert.setOnAction(event -> insert());
        delete.setOnAction(event -> delete());

        HBox controls = new HBox(6, new Label("Value:"), valueField,
                new Label("Index:"), indexField, insert, delete, status);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setTop(controls);
        stage.setTitle("Exercise 24.09: Slow-motion array list animation");
        stage.setScene(new Scene(root, 820, 340));
        stage.show();
        draw(-1);
    }

    private void insert() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        int index = list.size();
        String indexText = indexField.getText().trim();
        if (!indexText.isEmpty()) {
            try {
                index = Integer.parseInt(indexText);
            } catch (NumberFormatException exception) {
                status.setText("Enter an integer index");
                return;
            }
        }
        if (index < 0 || index > list.size()) {
            status.setText("Index out of range");
            return;
        }
        int target = index;
        Thread thread = new Thread(() -> {
            for (int i = list.size(); i > target; i--) {
                int current = i;
                Platform.runLater(() -> {
                    draw(current - 1);
                    status.setText("Shifting element at index " + (current - 1));
                });
                sleep();
            }
            Platform.runLater(() -> {
                list.add(target, value);
                draw(target);
                status.setText("Inserted " + value + " at index " + target);
            });
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void delete() {
        try {
            int index = Integer.parseInt(indexField.getText().trim());
            if (index < 0 || index >= list.size()) {
                status.setText("Index out of range");
                return;
            }
            Thread thread = new Thread(() -> {
                for (int i = index; i < list.size() - 1; i++) {
                    int current = i;
                    Platform.runLater(() -> {
                        draw(current + 1);
                        status.setText("Shifting element at index " + (current + 1));
                    });
                    sleep();
                }
                Platform.runLater(() -> {
                    list.remove(index);
                    draw(-1);
                    status.setText("Deleted index " + index);
                });
            });
            thread.setDaemon(true);
            thread.start();
        } catch (NumberFormatException exception) {
            status.setText("Enter an integer index");
        }
    }

    private Integer readValue() {
        try {
            return Integer.parseInt(valueField.getText().trim());
        } catch (NumberFormatException exception) {
            status.setText("Enter an integer value");
            return null;
        }
    }

    private void draw(int highlight) {
        canvas.getChildren().clear();
        for (int i = 0; i < list.size(); i++) {
            int x = 20 + i * 70;
            Rectangle box = new Rectangle(x, 80, 60, 40);
            box.setFill(i == highlight ? Color.ORANGE : Color.LIGHTCYAN);
            box.setStroke(Color.DARKSLATEBLUE);
            Text value = new Text(x + 24, 105, String.valueOf(list.get(i)));
            Text index = new Text(x + 24, 140, String.valueOf(i));
            canvas.getChildren().addAll(box, value, index);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
