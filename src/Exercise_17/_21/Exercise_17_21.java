package Exercise_17._21;

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

public class Exercise_17_21 extends Application {
    private final TextField fileField = new TextField();
    private final TextArea hexArea = new TextArea();
    private final Label status = new Label("Enter a file and press Enter.");

    @Override
    public void start(Stage stage) {
        hexArea.setFont(Font.font("Monospaced", 14));
        hexArea.setWrapText(true);
        HBox.setHgrow(fileField, Priority.ALWAYS);

        Button load = new Button("Load");
        Button save = new Button("Save the change");
        HBox fileRow = new HBox(8, new Label("Enter a file:"), fileField, load);
        fileRow.setAlignment(Pos.CENTER_LEFT);
        HBox buttonRow = new HBox(save);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, fileRow, hexArea, buttonRow, status);
        root.setPadding(new Insets(12));
        VBox.setVgrow(hexArea, Priority.ALWAYS);

        fileField.setOnAction(event -> loadFile());
        load.setOnAction(event -> loadFile());
        save.setOnAction(event -> saveFile());

        stage.setScene(new Scene(root, 720, 520));
        stage.setTitle("Hex Editor");
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
            hexArea.setText(toHex(bytes));
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

        try {
            Path path = Path.of(fileName);
            Files.write(path, fromHex(hexArea.getText()));
            status.setText("Saved " + Files.size(path) + " bytes to " + path);
        } catch (IOException | RuntimeException ex) {
            showError("Unable to save " + fileName + ": " + ex.getMessage());
        }
    }

    static String toHex(byte[] bytes) {
        StringBuilder text = new StringBuilder(bytes.length * 3);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                text.append(' ');
            }
            text.append(String.format("%02X", bytes[i] & 0xFF));
        }
        return text.toString();
    }

    static byte[] fromHex(String text) {
        String hex = text.replaceAll("\\s+", "");
        if (hex.isEmpty()) {
            throw new IllegalArgumentException("The hex text is empty.");
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("The hex text must contain an even number of digits.");
        }
        if (!hex.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Only hexadecimal digits are allowed.");
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
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
