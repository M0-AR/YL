package Exercise_30._01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise_30_01 extends Application {
    private final TextArea output = new TextArea();

    @Override
    public void start(Stage stage) {
        output.setEditable(false);
        Thread first = new Thread(() -> print('a', 100));
        Thread second = new Thread(() -> print('b', 100));
        Thread third = new Thread(() -> print('c', 100));
        first.start();
        second.start();
        third.start();

        stage.setTitle("Exercise 30.01");
        stage.setScene(new Scene(output, 420, 240));
        stage.show();
    }

    private void print(char character, int count) {
        for (int i = 0; i < count; i++) {
            Platform.runLater(() -> output.appendText(String.valueOf(character)));
            try {
                Thread.sleep(50);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
