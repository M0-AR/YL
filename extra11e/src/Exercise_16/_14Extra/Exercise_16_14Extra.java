package Exercise_16._14Extra;

import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_16_14Extra extends Application {
	private final ClockControl[] clocks = new ClockControl[3];

	@Override
	public void start(Stage primaryStage) {
		HBox clockBox = new HBox(10);
		clockBox.setAlignment(Pos.CENTER);
		for (int i = 0; i < clocks.length; i++) {
			clocks[i] = new ClockControl(i + 1);
			clockBox.getChildren().add(clocks[i]);
		}

		Button startAll = new Button("Start All");
		Button stopAll = new Button("Stop All");
		startAll.setOnAction(e -> {
			for (ClockControl clock : clocks) {
				clock.start();
			}
		});
		stopAll.setOnAction(e -> {
			for (ClockControl clock : clocks) {
				clock.stop();
			}
		});

		HBox controls = new HBox(10, startAll, stopAll);
		controls.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setCenter(clockBox);
		root.setBottom(controls);

		Scene scene = new Scene(root, 460, 230);
		primaryStage.setTitle("Exercise_16_14Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class ClockControl extends BorderPane {
		private final ClockPane clock = new ClockPane();

		ClockControl(int number) {
			Button start = new Button("Start");
			Button stop = new Button("Stop");
			start.setOnAction(e -> clock.start());
			stop.setOnAction(e -> clock.stop());

			HBox buttons = new HBox(5, start, stop);
			buttons.setAlignment(Pos.CENTER);

			setTop(new Label("Clock " + number));
			setCenter(clock);
			setBottom(buttons);
		}

		void start() {
			clock.start();
		}

		void stop() {
			clock.stop();
		}
	}

	static class ClockPane extends Pane {
		private static final double SIZE = 120;
		private final Timeline animation = new Timeline(
			new KeyFrame(Duration.seconds(1), e -> setCurrentTime()));
		private int hour;
		private int minute;
		private int second;

		ClockPane() {
			setPrefSize(SIZE, SIZE);
			animation.setCycleCount(Timeline.INDEFINITE);
			setCurrentTime();
			animation.play();
		}

		void start() {
			animation.play();
		}

		void stop() {
			animation.stop();
		}

		private void setCurrentTime() {
			LocalTime now = LocalTime.now();
			hour = now.getHour();
			minute = now.getMinute();
			second = now.getSecond();
			paint();
		}

		private void paint() {
			getChildren().clear();
			double center = SIZE / 2;
			double radius = SIZE * 0.42;

			Circle face = new Circle(center, center, radius);
			face.setFill(Color.WHITE);
			face.setStroke(Color.BLACK);
			getChildren().add(face);

			for (int i = 0; i < 12; i++) {
				double angle = i * Math.PI / 6;
				double x = center + radius * 0.8 * Math.sin(angle);
				double y = center - radius * 0.8 * Math.cos(angle) + 4;
				getChildren().add(new Text(x - 4, y, (i == 0 ? 12 : i) + ""));
			}

			double hourAngle = (hour % 12 + minute / 60.0) * Math.PI / 6;
			Line hourHand = new Line(center, center,
				center + radius * 0.5 * Math.sin(hourAngle),
				center - radius * 0.5 * Math.cos(hourAngle));
			hourHand.setStroke(Color.GREEN);

			double minuteAngle = minute * Math.PI / 30;
			Line minuteHand = new Line(center, center,
				center + radius * 0.7 * Math.sin(minuteAngle),
				center - radius * 0.7 * Math.cos(minuteAngle));
			minuteHand.setStroke(Color.BLUE);

			double secondAngle = second * Math.PI / 30;
			Line secondHand = new Line(center, center,
				center + radius * 0.85 * Math.sin(secondAngle),
				center - radius * 0.85 * Math.cos(secondAngle));
			secondHand.setStroke(Color.RED);

			getChildren().addAll(hourHand, minuteHand, secondHand);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
