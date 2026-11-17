package Exercise_15._07Extra;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_07Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		DetailedClockPane clock = new DetailedClockPane();

		clock.setOnMouseClicked(e -> {
			clock.setHour((int)(Math.random() * 12));
			clock.setMinute((int)(Math.random() * 4) * 15);
		});

		Scene scene = new Scene(clock, 250, 250);
		primaryStage.setTitle("Exercise_15_07Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class DetailedClockPane extends Pane {
		private int hour;
		private int minute;
		private int second;

		public DetailedClockPane() {
			Calendar calendar = new GregorianCalendar();
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
			paintClock();
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour % 12;
			paintClock();
		}

		public int getMinute() {
			return minute;
		}

		public void setMinute(int minute) {
			this.minute = minute;
			paintClock();
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
			paintClock();
		}

		private void paintClock() {
			getChildren().clear();

			double width = 250;
			double height = 250;
			double radius = Math.min(width, height) * 0.4;
			double centerX = width / 2;
			double centerY = height / 2;

			Circle face = new Circle(centerX, centerY, radius);
			face.setFill(Color.WHITE);
			face.setStroke(Color.BLACK);
			getChildren().add(face);

			for (int i = 0; i < 60; i++) {
				double outerX = centerX + radius * Math.sin(i * Math.PI / 30);
				double outerY = centerY - radius * Math.cos(i * Math.PI / 30);
				double innerRadius = i % 5 == 0 ? radius * 0.9 : radius * 0.95;
				double innerX = centerX + innerRadius * Math.sin(i * Math.PI / 30);
				double innerY = centerY - innerRadius * Math.cos(i * Math.PI / 30);
				getChildren().add(new Line(outerX, outerY, innerX, innerY));
			}

			for (int i = 0; i < 12; i++) {
				double angle = i * Math.PI / 6;
				double x = centerX + radius * 0.8 * Math.sin(angle);
				double y = centerY - radius * 0.8 * Math.cos(angle);
				getChildren().add(new Text(x - 4, y + 4, (i == 0 ? 12 : i) + ""));
			}

			double minuteLength = radius * 0.65;
			double minuteAngle = minute * Math.PI / 30;
			Line minuteHand = new Line(centerX, centerY,
				centerX + minuteLength * Math.sin(minuteAngle),
				centerY - minuteLength * Math.cos(minuteAngle));
			minuteHand.setStroke(Color.BLUE);

			double hourLength = radius * 0.5;
			double hourAngle = (hour % 12 + minute / 60.0) * Math.PI / 6;
			Line hourHand = new Line(centerX, centerY,
				centerX + hourLength * Math.sin(hourAngle),
				centerY - hourLength * Math.cos(hourAngle));
			hourHand.setStroke(Color.GREEN);

			getChildren().addAll(minuteHand, hourHand);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
