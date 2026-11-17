package Exercise_24._08;

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

public class Exercise_24_08 extends Application {
    private final ArrayList<Integer> list = new ArrayList<>();
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
        stage.setTitle("Exercise 24.08: Array list animation");
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
            for (int i = 0; i < list.size(); i++) {
                int index = i;
                Platform.runLater(() -> {
                    draw(index);
                    status.setText("Comparing " + list.get(index));
                });
                sleep();
                if (list.get(i).equals(value)) {
                    int found = i;
                    Platform.runLater(() -> status.setText("Found at index " + found));
                    return;
                }
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
            draw(list.size() - 1);
        } else {
            try {
                int index = Integer.parseInt(indexText);
                if (index < 0 || index > list.size()) {
                    status.setText("Index out of range");
                    return;
                }
                list.add(index, value);
                draw(index);
            } catch (NumberFormatException exception) {
                status.setText("Enter an integer index");
                return;
            }
        }
        status.setText("Inserted " + value);
    }

    private void delete() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        int index = list.indexOf(value);
        if (index >= 0) {
            list.remove(index);
        }
        draw(-1);
        status.setText(index >= 0 ? "Deleted " + value : "Not found");
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
        for (int i = 0; i < list.size(); i++) {
            Rectangle box = new Rectangle(x, 80, 60, 40);
            box.setFill(i == highlight ? Color.YELLOW : Color.LIGHTGREEN);
            box.setStroke(Color.DARKGREEN);
            Text value = new Text(x + 24, 105, String.valueOf(list.get(i)));
            Text index = new Text(x + 24, 140, String.valueOf(i));
            canvas.getChildren().addAll(box, value, index);
            x += 70;
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
