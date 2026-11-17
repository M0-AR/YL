package Exercise_16._28;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_16_28 extends Application {
    private static final int SLIDES = 10;
    private final TextArea slideArea = new TextArea();
    private int index;

    @Override
    public void start(Stage stage) {
        slideArea.setEditable(false);
        slideArea.setWrapText(true);
        slideArea.setFont(Font.font(18));
        showNextSlide();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> showNextSlide()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.setTitle("Exercise 16.28: Text slide show");
        stage.setScene(new Scene(slideArea, 620, 360));
        stage.show();
    }

    private void showNextSlide() {
        Path file = Path.of("text", "slide" + index + ".txt");
        if (!Files.exists(file)) {
            file = Path.of("slide" + index + ".txt");
        }
        try {
            slideArea.setText(Files.readString(file));
        } catch (IOException exception) {
            slideArea.setText("Could not read " + file + "\n" + exception.getMessage());
        }
        index = (index + 1) % SLIDES;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
