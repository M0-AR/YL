package Exercise_15._06Extra;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise_15_06Extra extends Application {
	private final Pane pane = new Pane();
	private final List<Circle> circles = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		pane.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				Circle circle = new Circle(e.getX(), e.getY(), 5);
				circle.setFill(Color.WHITE);
				circle.setStroke(Color.BLACK);
				circles.add(circle);
				pane.getChildren().add(circle);
			}
			else if (e.getButton() == MouseButton.SECONDARY) {
				for (int i = circles.size() - 1; i >= 0; i--) {
					if (circles.get(i).contains(e.getX(), e.getY())) {
						pane.getChildren().remove(circles.remove(i));
						break;
					}
				}
			}
			highlightClosestPair();
		});

		Scene scene = new Scene(pane, 400, 250);
		primaryStage.setTitle("Exercise_15_06Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void highlightClosestPair() {
		for (Circle circle : circles) {
			circle.setFill(Color.WHITE);
		}
		if (circles.size() < 2) {
			return;
		}

		int first = 0;
		int second = 1;
		double best = distance(circles.get(0), circles.get(1));
		for (int i = 0; i < circles.size(); i++) {
			for (int j = i + 1; j < circles.size(); j++) {
				double current = distance(circles.get(i), circles.get(j));
				if (current < best) {
					best = current;
					first = i;
					second = j;
				}
			}
		}

		circles.get(first).setFill(Color.BLACK);
		circles.get(second).setFill(Color.BLACK);
	}

	private static double distance(Circle first, Circle second) {
		return Math.hypot(
			first.getCenterX() - second.getCenterX(),
			first.getCenterY() - second.getCenterY());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
