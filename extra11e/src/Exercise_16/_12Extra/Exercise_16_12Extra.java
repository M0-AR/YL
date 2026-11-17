package Exercise_16._12Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_16_12Extra extends Application {
	private final TextField input = new TextField();
	private final ListView<String> listView = new ListView<>();

	@Override
	public void start(Stage primaryStage) {
		input.setPrefColumnCount(25);

		Button delete = new Button("Delete");
		delete.setOnAction(e -> deleteSelected());

		HBox top = new HBox(8, new Label("Enter a string:"), input);
		top.setAlignment(Pos.CENTER_LEFT);
		HBox bottom = new HBox(delete);
		bottom.setAlignment(Pos.CENTER_RIGHT);

		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setCenter(listView);
		root.setBottom(bottom);

		input.setOnAction(e -> {
			String text = input.getText();
			if (!text.isBlank()) {
				listView.getItems().add(text);
				input.clear();
			}
		});

		listView.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DELETE) {
				deleteSelected();
			}
		});

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setTitle("Exercise_16_12Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void deleteSelected() {
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			listView.getItems().remove(index);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
