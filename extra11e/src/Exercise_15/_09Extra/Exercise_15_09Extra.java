package Exercise_15._09Extra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_09Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Arc base = new Arc(60, 240, 40, 20, 0, 180);
		base.setFill(Color.WHITE);
		base.setStroke(Color.BLACK);

		Line pole = new Line(60, 220, 60, 20);
		Line hanger = new Line(60, 20, 160, 20);

		Line rope = new Line(160, 20, 160, 40);
		Circle head = new Circle(160, 60, 20);
		head.setFill(Color.WHITE);
		head.setStroke(Color.BLACK);
		Line body = new Line(160, 80, 160, 160);
		Line leftArm = new Line(160, 90, 130, 120);
		Line rightArm = new Line(160, 90, 190, 120);
		Line leftLeg = new Line(160, 160, 130, 200);
		Line rightLeg = new Line(160, 160, 190, 200);

		Group figure = new Group(rope, head, body, leftArm, rightArm,
			leftLeg, rightLeg);
		Rotate rotation = new Rotate(0, 160, 20);
		figure.getTransforms().add(rotation);

		Pane pane = new Pane(base, pole, hanger, figure);

		double[] angle = {0};
		double[] delta = {1};
		Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(30), e -> {
				angle[0] += delta[0];
				if (angle[0] >= 30) {
					angle[0] = 30;
					delta[0] = -1;
				}
				else if (angle[0] <= -30) {
					angle[0] = -30;
					delta[0] = 1;
				}
				rotation.setAngle(angle[0]);
			}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		Scene scene = new Scene(pane, 400, 250);
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				animation.setRate(animation.getRate() + 0.1);
			}
			else if (e.getCode() == KeyCode.DOWN) {
				animation.setRate(Math.max(0.1, animation.getRate() - 0.1));
			}
			else if (e.getCode() == KeyCode.S) {
				animation.pause();
			}
			else if (e.getCode() == KeyCode.R) {
				animation.play();
			}
		});

		primaryStage.setTitle("Exercise_15_09Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
