package Exercise_16._11;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_16_11 extends Application {
    private final TextField fileName = new TextField();
    private final Label status = new Label();
    private final Histogram histogram = new Histogram();

    @Override
    public void start(Stage stage) {
        fileName.setPrefColumnCount(28);
        fileName.setPromptText("Enter a file name and press Enter");
        fileName.setOnAction(event -> processFile());

        HBox top = new HBox(8, new Label("File:"), fileName, status);
        top.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(histogram);
        root.setTop(top);
        stage.setTitle("Exercise 16.11: Letter histogram");
        stage.setScene(new Scene(root, 820, 460));
        stage.show();
    }

    private void processFile() {
        int[] counts = new int[26];
        try {
            String text = Files.readString(Path.of(fileName.getText().trim()));
            for (char character : text.toCharArray()) {
                if ((character >= 'a' && character <= 'z')
                        || (character >= 'A' && character <= 'Z')) {
                    counts[Character.toUpperCase(character) - 'A']++;
                }
            }
            histogram.setCounts(counts);
            status.setText("Letters counted");
        } catch (IOException exception) {
            status.setText("Cannot read " + fileName.getText().trim());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
