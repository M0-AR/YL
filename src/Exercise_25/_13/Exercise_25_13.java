package Exercise_25._13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exercise_25_13 extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new BSTAnimationPane(), 900, 600));
        stage.setTitle("Exercise 25.13 - BST Animation");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
