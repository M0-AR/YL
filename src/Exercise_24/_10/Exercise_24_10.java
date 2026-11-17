package Exercise_24._10;

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
import java.util.Deque;

public class Exercise_24_10 extends Application {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Pane canvas = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();

    @Override
    public void start(Stage stage) {
        canvas.setPrefSize(300, 360);
        valueField.setPrefColumnCount(5);
        Button push = new Button("Push");
        Button pop = new Button("Pop");
        push.setOnAction(event -> push());
        pop.setOnAction(event -> pop());

        HBox controls = new HBox(6, new Label("Value:"), valueField, push, pop, status);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(canvas);
        root.setBottom(controls);
        stage.setTitle("Exercise 24.10: Stack animation");
        stage.setScene(new Scene(root, 420, 440));
        stage.show();
        draw(-1);
    }

    private void push() {
        try {
            int value = Integer.parseInt(valueField.getText().trim());
            stack.push(value);
            draw(0);
            status.setText("Pushed " + value);
        } catch (NumberFormatException exception) {
            status.setText("Enter an integer value");
        }
    }

    private void pop() {
        if (stack.isEmpty()) {
            status.setText("Stack is empty");
            return;
        }
        Integer value = stack.pop();
        draw(-1);
        status.setText("Popped " + value);
    }

    private void draw(int highlightFromTop) {
        Platform.runLater(() -> {
            canvas.getChildren().clear();
            int index = 0;
            for (Integer value : stack) {
                int y = 300 - index * 45;
                Rectangle box = new Rectangle(80, y, 120, 38);
                box.setFill(index == highlightFromTop ? Color.YELLOW : Color.LIGHTSTEELBLUE);
                box.setStroke(Color.DARKSLATEBLUE);
                Text text = new Text(130, y + 24, String.valueOf(value));
                canvas.getChildren().addAll(box, text);
                index++;
            }
            Rectangle base = new Rectangle(70, 342, 140, 8);
            canvas.getChildren().add(base);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
