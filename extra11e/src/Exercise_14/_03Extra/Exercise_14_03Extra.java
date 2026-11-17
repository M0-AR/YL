package Exercise_14._03Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise_14_03Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: blue");
		pane.getChildren().add(star(200, 200, 120, 48, Color.WHITE));

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise_14_03Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	static Polygon star(double centerX, double centerY,
			double outerRadius, double innerRadius, Color fill) {
		Polygon polygon = new Polygon();
		polygon.setFill(fill);
		polygon.setStroke(fill);

		for (int i = 0; i < 10; i++) {
			double radius = i % 2 == 0 ? outerRadius : innerRadius;
			double angle = -Math.PI / 2 + i * Math.PI / 5;
			polygon.getPoints().addAll(
				centerX + radius * Math.cos(angle),
				centerY + radius * Math.sin(angle));
		}
		return polygon;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
