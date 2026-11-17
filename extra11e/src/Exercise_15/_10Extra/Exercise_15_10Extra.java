package Exercise_15._10Extra;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_10Extra extends Application {
	private static final double WIDTH = 200;
	private static final double HEIGHT = 200;

	@Override
	public void start(Stage primaryStage) {
		BalloonPane pane = new BalloonPane();
		Scene scene = new Scene(pane, WIDTH, HEIGHT);

		Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(100), e -> pane.paint()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				pane.turnLeft();
			}
			else if (e.getCode() == KeyCode.RIGHT) {
				pane.turnRight();
			}
			else if (e.getCode() == KeyCode.UP) {
				pane.fire();
			}
		});

		primaryStage.setTitle("Exercise_15_10Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
		pane.requestFocus();
	}

	class BalloonPane extends Pane {
		private static final double BALLOON_RADIUS = 10;
		private static final double BALL_RADIUS = 5;
		private static final double GUN_LENGTH = 25;

		private double balloonX = 15 + Math.random() * (WIDTH - 30);
		private double balloonY = 15 + Math.random() * (HEIGHT - 30);
		private double angle = 90;
		private boolean hit;
		private final List<Shot> shots = new ArrayList<>();
		private final Circle balloon =
			new Circle(balloonX, balloonY, BALLOON_RADIUS);
		private final Line gun = new Line(WIDTH / 2, HEIGHT, WIDTH / 2, HEIGHT);

		public BalloonPane() {
			balloon.setFill(Color.WHITE);
			balloon.setStroke(Color.BLACK);
			setFocusTraversable(true);
			paint();
		}

		public void turnLeft() {
			if (angle < 180) {
				angle += 5;
			}
			paint();
		}

		public void turnRight() {
			if (angle > 0) {
				angle -= 5;
			}
			paint();
		}

		public void fire() {
			shots.add(new Shot(GUN_LENGTH, angle));
			paint();
		}

		public void paint() {
			getChildren().clear();

			if (hit) {
				getChildren().addAll(
					new Circle(balloonX - 5, balloonY, BALLOON_RADIUS / 2),
					new Circle(balloonX + 2 * BALLOON_RADIUS + 5, balloonY,
						BALLOON_RADIUS / 2),
					new Circle(balloonX, balloonY + 2 * BALLOON_RADIUS + 5,
						BALLOON_RADIUS / 2),
					new Circle(balloonX, balloonY - 2 * BALLOON_RADIUS - 5,
						BALLOON_RADIUS / 2));
				balloonX = 15 + Math.random() * (WIDTH - 30);
				balloonY = 15 + Math.random() * (HEIGHT - 30);
				balloon.setCenterX(balloonX);
				balloon.setCenterY(balloonY);
				hit = false;
			}

			gun.setStartX(WIDTH / 2);
			gun.setStartY(HEIGHT);
			gun.setEndX(WIDTH / 2 + GUN_LENGTH * Math.cos(Math.toRadians(angle)));
			gun.setEndY(HEIGHT - GUN_LENGTH * Math.sin(Math.toRadians(angle)));
			gun.setStrokeWidth(10);
			getChildren().addAll(balloon, gun);

			for (int i = shots.size() - 1; i >= 0; i--) {
				Shot shot = shots.get(i);
				shot.length += 5;
				double x = WIDTH / 2 + shot.length * Math.cos(Math.toRadians(shot.angle));
				double y = HEIGHT - shot.length * Math.sin(Math.toRadians(shot.angle));

				if (x < -BALL_RADIUS || x > WIDTH + BALL_RADIUS
						|| y < -BALL_RADIUS) {
					shots.remove(i);
				}
				else {
					Circle ball = new Circle(x, y, BALL_RADIUS);
					getChildren().add(ball);

					if (Math.hypot(x - balloonX, y - balloonY)
							<= BALL_RADIUS + BALLOON_RADIUS) {
						shots.remove(i);
						hit = true;
					}
				}
			}
		}
	}

	static class Shot {
		double length;
		double angle;

		Shot(double length, double angle) {
			this.length = length;
			this.angle = angle;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
