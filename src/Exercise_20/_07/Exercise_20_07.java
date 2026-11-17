package Exercise_20._07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Exercise_20_07 extends Application {
    private static final String[] WORDS = {
        "programming", "java", "computer", "hangman", "exercise",
        "keyboard", "monitor", "science", "freedom", "library",
        "student", "teacher", "winter", "summer", "coffee"
    };
    private final Random random = new Random();
    private final Label wordLabel = new Label();
    private final Label missesLabel = new Label();
    private final Label status = new Label();
    private final TextField guessField = new TextField();
    private final HangmanPane hangman = new HangmanPane();
    private String word;
    private boolean[] revealed;
    private final Set<Character> guessed = new HashSet<>();
    private int misses;
    private boolean gameOver;

    @Override
    public void start(Stage stage) {
        guessField.setPrefColumnCount(3);
        guessField.setOnAction(event -> handleGuess());

        HBox top = new HBox(20, wordLabel, missesLabel);
        top.setAlignment(Pos.CENTER);
        HBox bottom = new HBox(8, new Label("Guess a letter:"), guessField, status);
        bottom.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(hangman);
        root.setTop(top);
        root.setBottom(bottom);
        stage.setTitle("Exercise 20.07: Hangman");
        stage.setScene(new Scene(root, 480, 440));
        stage.show();
        newGame();
    }

    private void newGame() {
        word = WORDS[random.nextInt(WORDS.length)];
        revealed = new boolean[word.length()];
        guessed.clear();
        misses = 0;
        gameOver = false;
        hangman.setMisses(0);
        status.setText("Enter a letter");
        guessField.clear();
        update();
    }

    private void handleGuess() {
        if (gameOver) {
            newGame();
            return;
        }
        String text = guessField.getText().trim();
        guessField.clear();
        if (text.isEmpty()) {
            return;
        }
        char letter = Character.toLowerCase(text.charAt(0));
        if (letter < 'a' || letter > 'z') {
            status.setText("Enter a letter a-z");
            return;
        }
        if (!guessed.add(letter)) {
            status.setText("Already guessed '" + letter + "'");
            return;
        }
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                revealed[i] = true;
                found = true;
            }
        }
        if (!found) {
            misses++;
            hangman.setMisses(misses);
        }
        update();

        if (isComplete()) {
            gameOver = true;
            status.setText("You win! Press Enter for another word");
        } else if (misses == 7) {
            gameOver = true;
            status.setText("You lose! The word was " + word + ". Press Enter");
        } else {
            status.setText(found ? "Good guess" : "Misses: " + misses + " of 7");
        }
    }

    private void update() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i > 0) {
                text.append(' ');
            }
            text.append(revealed[i] ? Character.toUpperCase(word.charAt(i)) : '_');
        }
        wordLabel.setText(text.toString());
        missesLabel.setText("Misses: " + misses + " of 7");
    }

    private boolean isComplete() {
        for (boolean value : revealed) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    private static class HangmanPane extends Pane {
        HangmanPane() {
            setPrefSize(360, 300);
        }

        void setMisses(int misses) {
            getChildren().clear();
            Line base = new Line(30, 270, 210, 270);
            Line pole = new Line(70, 270, 70, 30);
            Line beam = new Line(70, 30, 180, 30);
            Line rope = new Line(180, 30, 180, 60);
            getChildren().addAll(base, pole, beam, rope);

            if (misses >= 1) {
                getChildren().add(new Circle(180, 80, 20));
            }
            if (misses >= 2) {
                getChildren().add(new Line(180, 100, 180, 180));
            }
            if (misses >= 3) {
                getChildren().add(new Line(180, 120, 150, 155));
            }
            if (misses >= 4) {
                getChildren().add(new Line(180, 120, 210, 155));
            }
            if (misses >= 5) {
                getChildren().add(new Line(180, 180, 150, 230));
            }
            if (misses >= 6) {
                getChildren().add(new Line(180, 180, 210, 230));
            }
            if (misses >= 7) {
                Circle leftEye = new Circle(173, 75, 2.5);
                Circle rightEye = new Circle(187, 75, 2.5);
                Arc mouth = new Arc(180, 88, 8, 6, 200, 140);
                mouth.setType(ArcType.OPEN);
                getChildren().addAll(leftEye, rightEye, mouth);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
