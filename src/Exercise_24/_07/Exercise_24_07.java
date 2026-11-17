package Exercise_24._07;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Exercise_24_07 extends Application {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();
    private final TextField indexField = new TextField();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(760, 220);
        for (int i = 10; i <= 50; i += 10) {
            list.add(i);
        }
        valueField.setPrefColumnCount(4);
        indexField.setPrefColumnCount(4);

        Button search = new Button("Search");
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        search.setOnAction(event -> search());
        insert.setOnAction(event -> insert());
        delete.setOnAction(event -> delete());

        HBox controls = new HBox(6, new Label("Value:"), valueField,
                new Label("Index:"), indexField, search, insert, delete, status);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setTop(controls);
        stage.setTitle("Exercise 24.07: Linked list animation");
        stage.setScene(new Scene(root, 800, 320));
        stage.show();
        draw(-1);
    }

    private void search() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        Thread thread = new Thread(() -> {
            int index = 0;
            for (Integer element : list) {
                int current = index;
                Platform.runLater(() -> {
                    draw(current);
                    status.setText("Comparing " + element);
                });
                sleep();
                if (element.equals(value)) {
                    Platform.runLater(() -> status.setText("Found at index " + current));
                    return;
                }
                index++;
            }
            Platform.runLater(() -> status.setText("Not found"));
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void insert() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        String indexText = indexField.getText().trim();
        if (indexText.isEmpty()) {
            list.add(value);
        } else {
            try {
                int index = Integer.parseInt(indexText);
                if (index < 0 || index > list.size()) {
                    status.setText("Index out of range");
                    return;
                }
                list.add(index, value);
            } catch (NumberFormatException exception) {
                status.setText("Enter an integer index");
                return;
            }
        }
        draw(list.size() - 1);
        status.setText("Inserted " + value);
    }

    private void delete() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        boolean removed = list.remove(value);
        draw(-1);
        status.setText(removed ? "Deleted " + value : "Not found");
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
        int x = 20;
        int index = 0;
        for (Integer value : list) {
            Rectangle box = new Rectangle(x, 80, 60, 40);
            box.setFill(index == highlight ? Color.YELLOW : Color.LIGHTBLUE);
            box.setStroke(Color.DARKSLATEBLUE);
            Text text = new Text(x + 24, 105, String.valueOf(value));
            canvas.getChildren().addAll(box, text);
            if (index < list.size() - 1) {
                Line arrow = new Line(x + 60, 100, x + 90, 100);
                canvas.getChildren().add(arrow);
            }
            Text position = new Text(x + 24, 140, String.valueOf(index));
            canvas.getChildren().add(position);
            x += 90;
            index++;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
