package Exercise_20._15;

import Exercise_20._19.Exercise_20_19;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Exercise_20_15 extends Application {
    private static final int CARDS = 4;
    private final int[] numbers = new int[CARDS];
    private final Text[] values = new Text[CARDS];
    private final Label result = new Label();
    private final Random random = new Random();

    @Override
    public void start(Stage stage) {
        HBox cardBox = new HBox(10);
        cardBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < CARDS; i++) {
            Rectangle card = new Rectangle(70, 100);
            card.setArcWidth(10);
            card.setArcHeight(10);
            card.setFill(Color.WHITE);
            card.setStroke(Color.DARKSLATEGRAY);
            values[i] = new Text();
            values[i].setFont(Font.font(24));
            values[i].setFill(Color.CRIMSON);
            cardBox.getChildren().add(new StackPane(card, values[i]));
        }

        Button shuffle = new Button("Shuffle");
        Button find = new Button("Find Solution");
        shuffle.setOnAction(event -> shuffle());
        find.setOnAction(event -> findSolution());
        HBox controls = new HBox(8, shuffle, find);
        controls.setAlignment(Pos.CENTER);

        result.setWrapText(true);
        BorderPane root = new BorderPane(cardBox);
        root.setTop(controls);
        root.setBottom(result);
        stage.setTitle("Exercise 20.15: 24-point card game");
        stage.setScene(new Scene(root, 520, 300));
        stage.show();
        shuffle();
    }

    private void shuffle() {
        for (int i = 0; i < CARDS; i++) {
            numbers[i] = random.nextInt(13) + 1;
            values[i].setText(String.valueOf(numbers[i]));
        }
        result.setText("Press Find Solution");
    }

    private void findSolution() {
        String expression = Exercise_20_19.solve(numbers.clone());
        result.setText(expression == null
                ? "No solution exists for these cards"
                : "Solution: " + expression + " = 24");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
