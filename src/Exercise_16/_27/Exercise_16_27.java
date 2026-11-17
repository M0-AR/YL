package Exercise_16._27;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_16_27 extends Application {
    private static final String[] COUNTRIES = {
        "Canada", "China", "Denmark", "France", "Germany",
        "India", "Norway", "United Kingdom", "United States"
    };
    private final ComboBox<String> countryBox = new ComboBox<>();
    private final TextArea description = new TextArea();

    @Override
    public void start(Stage stage) {
        countryBox.getItems().addAll(COUNTRIES);
        countryBox.getSelectionModel().selectFirst();
        countryBox.setOnAction(event -> showDescription(
                countryBox.getSelectionModel().getSelectedIndex()));

        description.setWrapText(true);
        description.setEditable(false);
        HBox top = new HBox(8, new Label("Select a country:"), countryBox);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(8));
        BorderPane root = new BorderPane(description);
        root.setTop(top);
        stage.setTitle("Exercise 16.27: Country descriptions from files");
        stage.setScene(new Scene(root, 560, 360));
        stage.show();
        showDescription(0);
    }

    private void showDescription(int index) {
        if (index < 0) {
            return;
        }
        Path file = Path.of("text", "description" + index + ".txt");
        if (!Files.exists(file)) {
            file = Path.of("description" + index + ".txt");
        }
        try {
            description.setText(Files.readString(file));
        } catch (IOException exception) {
            description.setText("Could not read " + file + "\n" + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
