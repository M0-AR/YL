package Exercise_14._13Extra;

import java.util.Calendar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_13Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		for (int i = 0; i < 4; i++) {
			pane.add(new ClockPane(), i % 2, i / 2);
		}

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise_14_13Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;

	public ClockPane() {
		Calendar now = Calendar.getInstance();
		hour = now.get(Calendar.HOUR_OF_DAY);
		minute = now.get(Calendar.MINUTE);
		second = now.get(Calendar.SECOND);
		paintClock();
		setPrefSize(200, 200);
	}

	private void paintClock() {
		double centerX = 100;
		double centerY = 100;
		double radius = 90;

		Circle clock = new Circle(centerX, centerY, radius);
		clock.setFill(Color.WHITE);
		clock.setStroke(Color.BLACK);
		getChildren().add(clock);

		for (int i = 1; i <= 12; i++) {
			double angle = i * Math.PI / 6;
			double x = centerX + radius * 0.85 * Math.sin(angle);
			double y = centerY - radius * 0.85 * Math.cos(angle);
			getChildren().add(new Text(x - 6, y + 5, i + ""));
		}

		double secondAngle = second * Math.PI / 30;
		double minuteAngle = minute * Math.PI / 30 + second * Math.PI / 1800;
		double hourAngle = (hour % 12) * Math.PI / 6 + minute * Math.PI / 360;

		getChildren().addAll(
			hand(centerX, centerY, secondAngle, radius * 0.9, Color.RED),
			hand(centerX, centerY, minuteAngle, radius * 0.7, Color.BLACK),
			hand(centerX, centerY, hourAngle, radius * 0.5, Color.BLACK),
			new Circle(centerX, centerY, 4));
	}

	private static Line hand(double centerX, double centerY,
			double angle, double length, Color color) {
		Line line = new Line(
			centerX, centerY,
			centerX + length * Math.sin(angle),
			centerY - length * Math.cos(angle));
		line.setStroke(color);
		return line;
	}
}
