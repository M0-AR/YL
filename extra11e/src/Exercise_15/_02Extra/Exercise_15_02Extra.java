package Exercise_15._02Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise_15_02Extra extends Application {
	private static final double WIDTH = 400;
	private static final double HEIGHT = 250;
	private final Pane pane = new Pane();

	@Override
	public void start(Stage primaryStage) {
		drawArrowLine();

		Button button = new Button("Draw a random arrow line");
		button.setOnAction(e -> drawArrowLine());

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(button);
		BorderPane.setAlignment(button, Pos.CENTER);

		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		primaryStage.setTitle("Exercise_15_02Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void drawArrowLine() {
		pane.getChildren().clear();

		double x1 = 20 + Math.random() * (WIDTH - 60);
		double y1 = 20 + Math.random() * (HEIGHT - 60);
		double x2 = 20 + Math.random() * (WIDTH - 60);
		double y2 = 20 + Math.random() * (HEIGHT - 60);

		pane.getChildren().add(new Line(x1, y1, x2, y2));

		double angle = Math.atan2(y2 - y1, x2 - x1);
		double length = 15;
		double wing = Math.PI / 9;
		pane.getChildren().add(new Line(x2, y2,
			x2 - length * Math.cos(angle - wing),
			y2 - length * Math.sin(angle - wing)));
		pane.getChildren().add(new Line(x2, y2,
			x2 - length * Math.cos(angle + wing),
			y2 - length * Math.sin(angle + wing)));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
