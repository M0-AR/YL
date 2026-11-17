package Exercise_15._15Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_15Extra extends Application {
	private final double[] xPoints = {25, 170, 300, 100, 50, 20};
	private final double[] yPoints = {20, 25, 100, 110, 90, 50};
	private final Circle[] vertices = new Circle[xPoints.length];
	private final Text[] labels = new Text[xPoints.length];
	private final Line[] lines = new Line[xPoints.length];
	private final Polygon polygon = new Polygon();
	private final Circle strategicPoint = new Circle(0, 0, 4);
	private final Text strategicText = new Text();

	@Override
	public void start(Stage primaryStage) {
		polygon.setFill(Color.TRANSPARENT);
		polygon.setStroke(Color.BLACK);
		strategicPoint.setFill(Color.RED);

		Pane pane = new Pane(polygon);

		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line();
			pane.getChildren().add(lines[i]);
		}

		for (int i = 0; i < xPoints.length; i++) {
			Circle vertex = new Circle(xPoints[i], yPoints[i], 6);
			vertex.setFill(Color.BLACK);
			Text label = new Text(xPoints[i] - 16, yPoints[i] - 8,
				"(" + (int)xPoints[i] + ", " + (int)yPoints[i] + ")");
			vertices[i] = vertex;
			labels[i] = label;

			vertex.setOnMouseDragged(e -> {
				vertex.setCenterX(e.getX());
				vertex.setCenterY(e.getY());
				label.setX(e.getX() - 16);
				label.setY(e.getY() - 8);
				label.setText("(" + (int)e.getX() + ", " + (int)e.getY() + ")");
				updatePolygon();
				updateStrategicPoint();
			});

			pane.getChildren().addAll(vertex, label);
		}

		pane.getChildren().addAll(strategicPoint, strategicText);
		updatePolygon();
		updateStrategicPoint();

		Scene scene = new Scene(pane, 570, 310);
		primaryStage.setTitle("Exercise_15_15Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void updatePolygon() {
		polygon.getPoints().clear();
		for (Circle vertex : vertices) {
			polygon.getPoints().addAll(
				vertex.getCenterX(), vertex.getCenterY());
		}
	}

	private void updateStrategicPoint() {
		for (int i = 0; i < vertices.length; i++) {
			xPoints[i] = vertices[i].getCenterX();
			yPoints[i] = vertices[i].getCenterY();
		}

		double[] point = getStrategicPoint();
		strategicPoint.setCenterX(point[0]);
		strategicPoint.setCenterY(point[1]);
		strategicText.setX(point[0] - 16);
		strategicText.setY(point[1] - 6);
		strategicText.setText("(" + (int)point[0] + ", " + (int)point[1] + ")");

		for (int i = 0; i < lines.length; i++) {
			lines[i].setStartX(vertices[i].getCenterX());
			lines[i].setStartY(vertices[i].getCenterY());
			lines[i].setEndX(point[0]);
			lines[i].setEndY(point[1]);
		}
	}

	private double[] getStrategicPoint() {
		double minX = min(xPoints);
		double maxX = max(xPoints);
		double minY = min(yPoints);
		double maxY = max(yPoints);

		double[] result = new double[2];
		double best = Double.MAX_VALUE;
		for (int x = (int)Math.floor(minX); x <= maxX; x++) {
			for (int y = (int)Math.floor(minY); y <= maxY; y++) {
				if (!polygon.contains(x, y)) {
					continue;
				}

				double total = 0;
				for (int i = 0; i < xPoints.length; i++) {
					total += Math.hypot(x - xPoints[i], y - yPoints[i]);
				}
				if (total < best) {
					best = total;
					result[0] = x;
					result[1] = y;
				}
			}
		}

		if (best == Double.MAX_VALUE) {
			for (int i = 0; i < xPoints.length; i++) {
				result[0] += xPoints[i];
				result[1] += yPoints[i];
			}
			result[0] /= xPoints.length;
			result[1] /= yPoints.length;
		}
		return result;
	}

	private static double min(double[] values) {
		double result = values[0];
		for (double value : values) {
			result = Math.min(result, value);
		}
		return result;
	}

	private static double max(double[] values) {
		double result = values[0];
		for (double value : values) {
			result = Math.max(result, value);
		}
		return result;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
