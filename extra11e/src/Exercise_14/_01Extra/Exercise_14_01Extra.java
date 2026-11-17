package Exercise_14._01Extra;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_01Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter coordinates and radius of two circles "
			+ "in this order x1 y1 r1 x2 y2 r2: ");
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double r1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		double r2 = input.nextDouble();

		Circle circle1 = createCircle(x1, y1, r1);
		Circle circle2 = createCircle(x2, y2, r2);

		Pane pane = new Pane();
		Text status = new Text(20, 380, "");
		if (contains(circle1, circle2) || contains(circle2, circle1)) {
			status.setText("One circle is contained in another");
		}
		else if (overlaps(circle1, circle2)) {
			status.setText("The circles overlap");
		}
		else {
			status.setText("The circles do not overlap");
		}
		pane.getChildren().addAll(circle1, circle2, status);

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise_14_01Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static Circle createCircle(double x, double y, double radius) {
		Circle circle = new Circle(x, y, radius);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		return circle;
	}

	private static boolean contains(Circle first, Circle second) {
		double distance = Math.hypot(
			first.getCenterX() - second.getCenterX(),
			first.getCenterY() - second.getCenterY());
		return distance + second.getRadius() <= first.getRadius();
	}

	private static boolean overlaps(Circle first, Circle second) {
		double distance = Math.hypot(
			first.getCenterX() - second.getCenterX(),
			first.getCenterY() - second.getCenterY());
		return distance <= first.getRadius() + second.getRadius();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
