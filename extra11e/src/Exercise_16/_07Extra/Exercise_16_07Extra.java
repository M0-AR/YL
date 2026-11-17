package Exercise_16._07Extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_16_07Extra extends Application {
	private final TextField urlField = new TextField();
	private final TextArea content = new TextArea();
	private final Label status = new Label();

	@Override
	public void start(Stage primaryStage) {
		urlField.setPrefColumnCount(50);
		content.setEditable(false);
		status.setWrapText(true);

		Button view = new Button("View");
		view.setOnAction(e -> loadFile());
		urlField.setOnAction(e -> loadFile());

		HBox top = new HBox(5, new Label("Filename"), urlField, view);
		top.setAlignment(Pos.CENTER_LEFT);

		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setCenter(content);
		root.setBottom(status);

		Scene scene = new Scene(root, 650, 400);
		primaryStage.setTitle("Exercise_16_07Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void loadFile() {
		String url = urlField.getText().trim();
		if (url.isEmpty()) {
			status.setText("Enter a URL");
			return;
		}

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(URI.create(url).toURL().openStream(),
					StandardCharsets.UTF_8))) {
			content.clear();
			String line;
			while ((line = reader.readLine()) != null) {
				content.appendText(line + "\n");
			}
			status.setText("File loaded successfully");
		}
		catch (IllegalArgumentException | IOException ex) {
			status.setText("Network connection problem: " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
