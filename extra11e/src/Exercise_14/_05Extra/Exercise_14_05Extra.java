package Exercise_14._05Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise_14_05Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.getChildren().add(star(200, 200, 140, 55));

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise_14_05Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static Polygon star(double centerX, double centerY,
			double outerRadius, double innerRadius) {
		Polygon polygon = new Polygon();
		polygon.setFill(Color.TRANSPARENT);
		polygon.setStroke(Color.BLACK);
		polygon.setStrokeWidth(2);

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
