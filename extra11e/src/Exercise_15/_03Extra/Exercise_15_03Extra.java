package Exercise_15._03Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_03Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				pane.add(new Cell(), j, i);
			}
		}

		Scene scene = new Scene(pane, 200, 250);
		primaryStage.setTitle("Exercise_15_03Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class Cell extends Text {
		Cell() {
			setFont(Font.font("Times Roman", 35));
			setText("H");
			setOnMouseClicked(e -> {
				setText(getText().equals("H") ? "T" : "H");
			});
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
