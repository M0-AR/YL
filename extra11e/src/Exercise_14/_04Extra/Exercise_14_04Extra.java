package Exercise_14._04Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_14_04Extra extends Application {
	private static final double FLAG_X = 10;
	private static final double FLAG_Y = 10;
	private static final double FLAG_WIDTH = 550;
	private static final double FLAG_HEIGHT = 290;
	private static final int STRIPES = 13;
	private static final double CANTON_WIDTH = 230;
	private static final double CANTON_HEIGHT = 160;

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: white");

		double stripeHeight = FLAG_HEIGHT / STRIPES;
		for (int i = 0; i < STRIPES; i++) {
			if (i % 2 == 0) {
				Rectangle stripe = new Rectangle(
					FLAG_X, FLAG_Y + i * stripeHeight,
					FLAG_WIDTH, stripeHeight);
				stripe.setFill(Color.RED);
				pane.getChildren().add(stripe);
			}
		}

		Rectangle canton = new Rectangle(
			FLAG_X, FLAG_Y, CANTON_WIDTH, CANTON_HEIGHT);
		canton.setFill(Color.DARKBLUE);
		pane.getChildren().add(canton);

		for (int row = 0; row < 9; row++) {
			int stars = row % 2 == 0 ? 5 : 4;
			for (int column = 0; column < stars; column++) {
				double x = FLAG_X + CANTON_WIDTH * (column + 1) / (stars + 1);
				double y = FLAG_Y + CANTON_HEIGHT * (row + 1) / 10;
				pane.getChildren().add(star(x, y, 5));
			}
		}

		Scene scene = new Scene(pane, 570, 310);
		primaryStage.setTitle("Exercise_14_04Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static Polygon star(double centerX, double centerY, double radius) {
		Polygon polygon = new Polygon();
		for (int i = 0; i < 10; i++) {
			double currentRadius = i % 2 == 0 ? radius : radius * 0.4;
			double angle = -Math.PI / 2 + i * Math.PI / 5;
			polygon.getPoints().addAll(
				centerX + currentRadius * Math.cos(angle),
				centerY + currentRadius * Math.sin(angle));
		}
		polygon.setFill(Color.WHITE);
		return polygon;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
