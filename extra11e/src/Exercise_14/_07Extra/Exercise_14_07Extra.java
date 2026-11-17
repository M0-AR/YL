package Exercise_14._07Extra;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_07Extra extends Application {
	private final double[] xPoints = new double[6];
	private final double[] yPoints = new double[6];
	private final Polygon polygon = new Polygon();

	@Override
	public void start(Stage primaryStage) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter coordinates of six points in a polygon: ");
		for (int i = 0; i < 6; i++) {
			xPoints[i] = input.nextDouble();
			yPoints[i] = input.nextDouble();
		}

		polygon.setFill(Color.TRANSPARENT);
		polygon.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().add(polygon);

		for (int i = 0; i < xPoints.length; i++) {
			polygon.getPoints().addAll(xPoints[i], yPoints[i]);
			pane.getChildren().addAll(
				new Circle(xPoints[i], yPoints[i], 4),
				new Text(xPoints[i] - 16, yPoints[i] - 8,
					"(" + xPoints[i] + ", " + yPoints[i] + ")"));
		}

		double[] strategicPoint = getStrategicPoint();
		Circle marker = new Circle(strategicPoint[0], strategicPoint[1], 5);
		marker.setFill(Color.RED);
		pane.getChildren().addAll(marker,
			new Text(strategicPoint[0] - 16, strategicPoint[1] - 8,
				"(" + strategicPoint[0] + ", " + strategicPoint[1] + ")"));

		Scene scene = new Scene(pane, 570, 310);
		primaryStage.setTitle("Exercise_14_07Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private double[] getStrategicPoint() {
		double minX = min(xPoints);
		double maxX = max(xPoints);
		double minY = min(yPoints);
		double maxY = max(yPoints);

		double[] result = new double[2];
		double bestDistance = Double.MAX_VALUE;
		for (int x = (int)minX; x <= maxX; x++) {
			for (int y = (int)minY; y <= maxY; y++) {
				if (!polygon.contains(x, y)) {
					continue;
				}

				double distance = 0;
				for (int i = 0; i < xPoints.length; i++) {
					distance += Math.hypot(x - xPoints[i], y - yPoints[i]);
				}
				if (distance < bestDistance) {
					bestDistance = distance;
					result[0] = x;
					result[1] = y;
				}
			}
		}

		if (bestDistance == Double.MAX_VALUE) {
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
