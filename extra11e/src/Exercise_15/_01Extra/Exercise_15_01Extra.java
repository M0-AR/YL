package Exercise_15._01Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_01Extra extends Application {
	private final Text[][] texts = new Text[10][10];

	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Text text = new Text();
				text.setFont(Font.font("Times Roman", 35));
				texts[i][j] = text;
				grid.add(text, j, i);
			}
		}

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> refresh());
		refresh();

		BorderPane pane = new BorderPane();
		pane.setCenter(grid);
		pane.setBottom(refresh);
		BorderPane.setAlignment(refresh, Pos.CENTER);

		Scene scene = new Scene(pane, 400, 450);
		primaryStage.setTitle("Exercise_15_01Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void refresh() {
		for (int i = 0; i < texts.length; i++) {
			for (int j = 0; j < texts[i].length; j++) {
				texts[i][j].setText((int)(Math.random() * 2) + "");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
