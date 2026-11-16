package Exercise_15._10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_10 extends Application {
    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;

    private final Pane pane = new Pane();
    private final Text input = new Text(20, HEIGHT - 20, "");
    private String buffer = "";

    @Override
    public void start(Stage primaryStage) {
        input.setFont(Font.font(18));
        input.setFill(Color.DARKBLUE);
        pane.getChildren().add(input);
        pane.setFocusTraversable(true);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.setOnKeyTyped(e -> {
            String character = e.getCharacter();
            if (!character.isEmpty() && character.charAt(0) >= 32) {
                buffer += character;
                input.setText(buffer);
            }
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                commit();
            } else if (e.getCode() == KeyCode.BACK_SPACE && !buffer.isEmpty()) {
                buffer = buffer.substring(0, buffer.length() - 1);
                input.setText(buffer);
            }
        });

        primaryStage.setTitle("Exercise_15_10");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.requestFocus();
    }

    private void commit() {
        if (buffer.isEmpty()) {
            return;
        }
        Text text = new Text(buffer);
        text.setFont(Font.font(18));
        text.setFill(Color.BLACK);
        text.setX(20 + Math.random() * Math.max(1, WIDTH - 140));
        text.setY(40 + Math.random() * Math.max(1, HEIGHT - 120));
        pane.getChildren().add(text);
        buffer = "";
        input.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
