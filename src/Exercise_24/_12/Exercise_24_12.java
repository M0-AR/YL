package Exercise_24._12;

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

import java.util.ArrayDeque;
import java.util.Queue;

public class Exercise_24_12 extends Application {
    private final Queue<Integer> queue = new ArrayDeque<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(780, 220);
        valueField.setPrefColumnCount(5);
        Button enqueue = new Button("Enqueue");
        Button dequeue = new Button("Dequeue");
        enqueue.setOnAction(event -> enqueue());
        dequeue.setOnAction(event -> dequeue());

        HBox controls = new HBox(6, new Label("Value:"), valueField,
                enqueue, dequeue, status);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setBottom(controls);
        stage.setTitle("Exercise 24.12: Queue animation");
        stage.setScene(new Scene(root, 820, 320));
        stage.show();
        draw(-1);
    }

    private void enqueue() {
        try {
            int value = Integer.parseInt(valueField.getText().trim());
            queue.offer(value);
            draw(queue.size() - 1);
            status.setText("Enqueued " + value);
        } catch (NumberFormatException exception) {
            status.setText("Enter an integer value");
        }
    }

    private void dequeue() {
        if (queue.isEmpty()) {
            status.setText("Queue is empty");
            return;
        }
        Integer value = queue.poll();
        draw(0);
        status.setText("Dequeued " + value);
    }

    private void draw(int highlight) {
        Platform.runLater(() -> {
            canvas.getChildren().clear();
            int index = 0;
            for (Integer value : queue) {
                int x = 20 + index * 75;
                Rectangle box = new Rectangle(x, 80, 60, 40);
                box.setFill(index == highlight ? Color.YELLOW : Color.LIGHTBLUE);
                box.setStroke(Color.DARKSLATEBLUE);
                Text text = new Text(x + 24, 105, String.valueOf(value));
                canvas.getChildren().addAll(box, text);
                index++;
            }
            Text front = new Text(20, 60, "front");
            Text rear = new Text(Math.max(20, 20 + (queue.size() - 1) * 75), 150, "rear");
            canvas.getChildren().addAll(front, rear);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
