package Exercise_17._20;

import Exercise_17._17.BitOutputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_17_20 extends Application {
    private final TextField fileField = new TextField();
    private final TextArea bitsArea = new TextArea();
    private final Label status = new Label("Enter a file and press Enter.");

    @Override
    public void start(Stage stage) {
        bitsArea.setFont(Font.font("Monospaced", 14));
        bitsArea.setWrapText(true);
        HBox.setHgrow(fileField, Priority.ALWAYS);

        Button load = new Button("Load");
        Button save = new Button("Save the change");
        HBox fileRow = new HBox(8, new Label("Enter a file:"), fileField, load);
        fileRow.setAlignment(Pos.CENTER_LEFT);
        HBox buttonRow = new HBox(save);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, fileRow, bitsArea, buttonRow, status);
        root.setPadding(new Insets(12));
        VBox.setVgrow(bitsArea, Priority.ALWAYS);

        fileField.setOnAction(event -> loadFile());
        load.setOnAction(event -> loadFile());
        save.setOnAction(event -> saveFile());

        stage.setScene(new Scene(root, 720, 520));
        stage.setTitle("Binary Editor");
        stage.show();
    }

    private void loadFile() {
        String fileName = fileField.getText().trim();
        if (fileName.isEmpty()) {
            showError("Enter a file name.");
            return;
        }

        try {
            Path path = Path.of(fileName);
            byte[] bytes = Files.readAllBytes(path);
            bitsArea.setText(toBits(bytes));
            status.setText("Loaded " + bytes.length + " bytes from " + path);
        } catch (IOException | RuntimeException ex) {
            showError("Unable to read " + fileName + ": " + ex.getMessage());
        }
    }

    private void saveFile() {
        String fileName = fileField.getText().trim();
        if (fileName.isEmpty()) {
            showError("Enter a file name.");
            return;
        }

        String bits = bitsArea.getText().replaceAll("\\s+", "");
        if (bits.isEmpty()) {
            showError("The binary text is empty.");
            return;
        }
        if (!bits.matches("[01]+")) {
            showError("Only 0 and 1 are allowed.");
            return;
        }
        if (bits.length() % 8 != 0) {
            showError("The number of bits must be a multiple of 8.");
            return;
        }

        try {
            Path path = Path.of(fileName);
            try (BitOutputStream output = new BitOutputStream(path.toFile())) {
                output.writeBit(bits);
            }
            status.setText("Saved " + (bits.length() / 8) + " bytes to " + path);
        } catch (IOException | RuntimeException ex) {
            showError("Unable to save " + fileName + ": " + ex.getMessage());
        }
    }

    static String toBits(byte[] bytes) {
        StringBuilder text = new StringBuilder(bytes.length * 9);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                text.append(' ');
            }
            for (int bit = 7; bit >= 0; bit--) {
                text.append((bytes[i] >> bit) & 1);
            }
        }
        return text.toString();
    }

    private void showError(String message) {
        status.setText(message);
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
