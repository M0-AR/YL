package Exercise_15._17Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_17Extra extends Application {
	private final double[][] points = {
		{20, 20}, {300, 20}, {256, 330}, {16, 130}};
	private final Circle[] vertices = new Circle[4];
	private final Line[] sides = new Line[4];
	private final Line diagonal1 = new Line();
	private final Line diagonal2 = new Line();
	private final Circle intersection = new Circle(4);
	private final Text[] areas = new Text[4];

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		for (int i = 0; i < 4; i++) {
			vertices[i] = new Circle(points[i][0], points[i][1], 5);
			vertices[i].setFill(Color.BLACK);
			areas[i] = new Text();
			sides[i] = new Line();

			final int index = i;
			vertices[i].setOnMouseDragged(e -> {
				vertices[index].setCenterX(e.getX());
				vertices[index].setCenterY(e.getY());
				update();
			});
		}

		intersection.setFill(Color.RED);

		pane.getChildren().addAll(sides);
		pane.getChildren().addAll(diagonal1, diagonal2);
		pane.getChildren().addAll(vertices);
		pane.getChildren().add(intersection);
		pane.getChildren().addAll(areas);

		update();

		Scene scene = new Scene(pane, 470, 410);
		primaryStage.setTitle("Exercise_15_17Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void update() {
		sides[0].setStartX(vertices[0].getCenterX());
		sides[0].setStartY(vertices[0].getCenterY());
		sides[0].setEndX(vertices[1].getCenterX());
		sides[0].setEndY(vertices[1].getCenterY());

		sides[1].setStartX(vertices[1].getCenterX());
		sides[1].setStartY(vertices[1].getCenterY());
		sides[1].setEndX(vertices[2].getCenterX());
		sides[1].setEndY(vertices[2].getCenterY());

		sides[2].setStartX(vertices[2].getCenterX());
		sides[2].setStartY(vertices[2].getCenterY());
		sides[2].setEndX(vertices[3].getCenterX());
		sides[2].setEndY(vertices[3].getCenterY());

		sides[3].setStartX(vertices[3].getCenterX());
		sides[3].setStartY(vertices[3].getCenterY());
		sides[3].setEndX(vertices[0].getCenterX());
		sides[3].setEndY(vertices[0].getCenterY());

		diagonal1.setStartX(vertices[0].getCenterX());
		diagonal1.setStartY(vertices[0].getCenterY());
		diagonal1.setEndX(vertices[2].getCenterX());
		diagonal1.setEndY(vertices[2].getCenterY());

		diagonal2.setStartX(vertices[1].getCenterX());
		diagonal2.setStartY(vertices[1].getCenterY());
		diagonal2.setEndX(vertices[3].getCenterX());
		diagonal2.setEndY(vertices[3].getCenterY());

		double[] point = getIntersectingPoint();
		if (point == null) {
			intersection.setVisible(false);
			for (Text text : areas) {
				text.setText("");
			}
			return;
		}

		intersection.setVisible(true);
		intersection.setCenterX(point[0]);
		intersection.setCenterY(point[1]);

		for (int i = 0; i < 4; i++) {
			Circle first = vertices[i];
			Circle second = vertices[(i + 1) % 4];
			double area = triangleArea(first, second, point[0], point[1]);
			double centerX = (first.getCenterX() + second.getCenterX()
				+ point[0]) / 3;
			double centerY = (first.getCenterY() + second.getCenterY()
				+ point[1]) / 3;
			areas[i].setX(centerX - 12);
			areas[i].setY(centerY);
			areas[i].setText(String.format("%.2f", area));
		}
	}

	private double[] getIntersectingPoint() {
		double x1 = vertices[0].getCenterX();
		double y1 = vertices[0].getCenterY();
		double x2 = vertices[2].getCenterX();
		double y2 = vertices[2].getCenterY();
		double x3 = vertices[1].getCenterX();
		double y3 = vertices[1].getCenterY();
		double x4 = vertices[3].getCenterX();
		double y4 = vertices[3].getCenterY();

		double a = y1 - y2;
		double b = x2 - x1;
		double e = x1 * y2 - x2 * y1;
		double c = y3 - y4;
		double d = x4 - x3;
		double f = x3 * y4 - x4 * y3;
		double determinant = a * d - b * c;

		if (Math.abs(determinant) < 1e-9) {
			return null;
		}

		return new double[] {
			(b * f - d * e) / determinant,
			(c * e - a * f) / determinant};
	}

	private static double triangleArea(Circle first, Circle second,
			double x, double y) {
		return Math.abs((second.getCenterX() - first.getCenterX()) * (y
			- first.getCenterY()) - (x - first.getCenterX())
			* (second.getCenterY() - first.getCenterY())) / 2;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
