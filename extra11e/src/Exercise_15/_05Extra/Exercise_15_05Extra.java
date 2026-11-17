package Exercise_15._05Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise_15_05Extra extends Application {
	private final Circle[] points = new Circle[4];
	private final Line line1 = new Line();
	private final Line line2 = new Line();
	private final Circle marker = new Circle(5);

	@Override
	public void start(Stage primaryStage) {
		double[][] initial = {
			{20, 20}, {56, 130}, {100, 20}, {16, 130}
		};

		for (int i = 0; i < points.length; i++) {
			points[i] = new Circle(initial[i][0], initial[i][1], 4);
			points[i].setFill(Color.BLACK);
		}

		line1.setStartX(points[0].getCenterX());
		line1.setStartY(points[0].getCenterY());
		line1.setEndX(points[1].getCenterX());
		line1.setEndY(points[1].getCenterY());
		line2.setStartX(points[2].getCenterX());
		line2.setStartY(points[2].getCenterY());
		line2.setEndX(points[3].getCenterX());
		line2.setEndY(points[3].getCenterY());
		marker.setFill(Color.RED);

		Pane pane = new Pane();
		pane.getChildren().addAll(points[0], points[1], points[2], points[3],
			line1, line2, marker);

		for (int i = 0; i < points.length; i++) {
			final int index = i;
			points[i].setOnMouseDragged(e -> {
				points[index].setCenterX(e.getX());
				points[index].setCenterY(e.getY());
				updateLines();
				updateMarker();
			});
		}

		updateMarker();

		Scene scene = new Scene(pane, 270, 210);
		primaryStage.setTitle("Exercise_15_05Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void updateLines() {
		line1.setStartX(points[0].getCenterX());
		line1.setStartY(points[0].getCenterY());
		line1.setEndX(points[1].getCenterX());
		line1.setEndY(points[1].getCenterY());
		line2.setStartX(points[2].getCenterX());
		line2.setStartY(points[2].getCenterY());
		line2.setEndX(points[3].getCenterX());
		line2.setEndY(points[3].getCenterY());
	}

	private void updateMarker() {
		double[] p = getIntersectingPoint(points);
		if (p == null) {
			marker.setVisible(false);
		}
		else {
			marker.setVisible(true);
			marker.setCenterX(p[0]);
			marker.setCenterY(p[1]);
		}
	}

	public static double[] getIntersectingPoint(Circle[] points) {
		return getIntersectingPoint(
			points[0].getCenterX(), points[0].getCenterY(),
			points[1].getCenterX(), points[1].getCenterY(),
			points[2].getCenterX(), points[2].getCenterY(),
			points[3].getCenterX(), points[3].getCenterY());
	}

	public static double[] getIntersectingPoint(
			double x1, double y1, double x2, double y2,
			double x3, double y3, double x4, double y4) {
		double a = y1 - y2;
		double b = x2 - x1;
		double c = y3 - y4;
		double d = x4 - x3;
		double e = x1 * y2 - x2 * y1;
		double f = x3 * y4 - x4 * y3;

		double determinant = a * d - b * c;
		if (determinant == 0) {
			return null;
		}

		return new double[] {
			(b * f - d * e) / determinant,
			(c * e - a * f) / determinant
		};
	}

	public static void main(String[] args) {
		launch(args);
	}
}
