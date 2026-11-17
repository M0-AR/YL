package Exercise_15._13Extra;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise_15_13Extra extends Application {
	private final Pane pane = new Pane();
	private final List<Circle> cities = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		pane.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY
					&& !isInsideCity(e.getX(), e.getY())) {
				Circle city = new Circle(e.getX(), e.getY(), 5);
				city.setFill(Color.BLACK);
				cities.add(city);
				pane.getChildren().add(city);
			}
			else if (e.getButton() == MouseButton.SECONDARY) {
				for (int i = cities.size() - 1; i >= 0; i--) {
					if (cities.get(i).contains(e.getX(), e.getY())) {
						pane.getChildren().remove(cities.remove(i));
						break;
					}
				}
			}
			updateCentralCity();
		});

		Scene scene = new Scene(pane, 570, 310);
		primaryStage.setTitle("Exercise_15_13Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private boolean isInsideCity(double x, double y) {
		for (Circle city : cities) {
			if (city.contains(x, y)) {
				return true;
			}
		}
		return false;
	}

	private void updateCentralCity() {
		for (Circle city : cities) {
			city.setFill(Color.BLACK);
		}
		if (cities.size() < 3) {
			return;
		}

		int center = 0;
		double best = totalDistance(0);
		for (int i = 1; i < cities.size(); i++) {
			double total = totalDistance(i);
			if (total < best) {
				best = total;
				center = i;
			}
		}
		cities.get(center).setFill(Color.RED);
	}

	private double totalDistance(int index) {
		double total = 0;
		Circle city = cities.get(index);
		for (Circle other : cities) {
			total += Math.hypot(
				city.getCenterX() - other.getCenterX(),
				city.getCenterY() - other.getCenterY());
		}
		return total;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
