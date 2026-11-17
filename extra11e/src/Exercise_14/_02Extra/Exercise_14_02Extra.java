package Exercise_14._02Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_02Extra extends Application {
	private static final double RADIUS = 10;
	private final Pane pane = new Pane();

	@Override
	public void start(Stage primaryStage) {
		draw();

		pane.widthProperty().addListener(observable -> draw());
		pane.heightProperty().addListener(observable -> draw());

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise_14_02Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void draw() {
		double width = pane.getWidth();
		double height = pane.getHeight();
		if (width <= 2 * RADIUS || height <= 2 * RADIUS) {
			return;
		}

		pane.getChildren().clear();
		Circle[] circles = new Circle[4];
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new Circle(
				RADIUS + Math.random() * (width - 2 * RADIUS),
				RADIUS + Math.random() * (height - 2 * RADIUS),
				RADIUS);
			circles[i].setFill(Color.hsb(Math.random() * 360, 0.7, 0.9, 0.85));
			circles[i].setStroke(Color.BLACK);
		}

		for (int i = 0; i < circles.length; i++) {
			for (int j = i + 1; j < circles.length; j++) {
				pane.getChildren().add(connect(circles[i], circles[j]));
			}
		}

		for (int i = 0; i < circles.length; i++) {
			Circle circle = circles[i];
			pane.getChildren().addAll(circle,
				new Text(circle.getCenterX() - 5,
					circle.getCenterY() + 4, "" + (i + 1)));
		}
	}

	private static Line connect(Circle first, Circle second) {
		double dx = second.getCenterX() - first.getCenterX();
		double dy = second.getCenterY() - first.getCenterY();
		double distance = Math.hypot(dx, dy);
		double ux = dx / distance;
		double uy = dy / distance;
		return new Line(
			first.getCenterX() + ux * first.getRadius(),
			first.getCenterY() + uy * first.getRadius(),
			second.getCenterX() - ux * second.getRadius(),
			second.getCenterY() - uy * second.getRadius());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
