package Exercise_24._11;

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
import java.util.ListIterator;

public class Exercise_24_11 extends Application {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();
    private final TextField indexField = new TextField();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(820, 240);
        for (int i = 10; i <= 50; i += 10) {
            list.add(i);
        }
        valueField.setPrefColumnCount(4);
        indexField.setPrefColumnCount(4);
        Button search = new Button("Search");
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button forward = new Button("Forward Traversal");
        Button backward = new Button("Backward Traversal");
        search.setOnAction(event -> search());
        insert.setOnAction(event -> insert());
        delete.setOnAction(event -> delete());
        forward.setOnAction(event -> traverse(true));
        backward.setOnAction(event -> traverse(false));

        HBox controls = new HBox(5, new Label("Value:"), valueField,
                new Label("Index:"), indexField, search, insert, delete, forward, backward);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setTop(controls);
        root.setBottom(status);
        stage.setTitle("Exercise 24.11: Doubly linked list animation");
        stage.setScene(new Scene(root, 860, 360));
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
        draw(-1);
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

    private void traverse(boolean forward) {
        Thread thread = new Thread(() -> {
            StringBuilder text = new StringBuilder();
            ListIterator<Integer> iterator = forward
                    ? list.listIterator() : list.listIterator(list.size());
            while (forward ? iterator.hasNext() : iterator.hasPrevious()) {
                int value = forward ? iterator.next() : iterator.previous();
                int index = iterator.nextIndex();
                Platform.runLater(() -> draw(forward ? index - 1 : index));
                text.append(value).append(' ');
                sleep();
            }
            Platform.runLater(() -> status.setText(
                    (forward ? "Forward: " : "Backward: ") + text.toString().trim()));
        });
        thread.setDaemon(true);
        thread.start();
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
            box.setFill(index == highlight ? Color.YELLOW : Color.PLUM);
            box.setStroke(Color.DARKSLATEBLUE);
            Text text = new Text(x + 24, 105, String.valueOf(value));
            canvas.getChildren().addAll(box, text);
            if (index < list.size() - 1) {
                Line forwardArrow = new Line(x + 60, 92, x + 90, 92);
                Line backwardArrow = new Line(x + 90, 108, x + 60, 108);
                canvas.getChildren().addAll(forwardArrow, backwardArrow);
            }
            Text position = new Text(x + 24, 145, String.valueOf(index));
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
