package Exercise_15._20Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise_15_20Extra extends Application {
	private final Label[] labels = new Label[4];
	private final int[] numbers = new int[52];

	@Override
	public void start(Stage primaryStage) {
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}

		HBox hBox = new HBox(5);
		hBox.setAlignment(Pos.CENTER);
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label();
			labels[i].setStyle(
				"-fx-border-color: red; -fx-padding: 5px; -fx-min-width: 30px;"
				+ " -fx-alignment: center;");
			hBox.getChildren().add(labels[i]);
		}

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> displayNumbers());

		BorderPane pane = new BorderPane();
		pane.setCenter(hBox);
		pane.setBottom(refresh);
		BorderPane.setAlignment(refresh, Pos.TOP_CENTER);

		displayNumbers();

		Scene scene = new Scene(pane, 250, 150);
		primaryStage.setTitle("Exercise_15_20Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void displayNumbers() {
		for (int i = numbers.length - 1; i > 0; i--) {
			int j = (int)(Math.random() * (i + 1));
			int temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
		}

		for (int i = 0; i < labels.length; i++) {
			labels[i].setText(numbers[i] + "");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
