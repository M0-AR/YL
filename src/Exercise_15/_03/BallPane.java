// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._03;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BallPane extends Pane {
	private Circle circle = new Circle(20, 20, 20);


	public BallPane() {
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		getChildren().add(circle);
	}


	public void left() {
		circle.setCenterX(circle.getCenterX() > circle.getRadius() ?
			circle.getCenterX() - 20 : circle.getCenterX());
		circle.setCenterY(circle.getCenterY());
	}


	public void right() {
		circle.setCenterX(circle.getCenterX() < getWidth() - circle.getRadius() ?
			circle.getCenterX() + 20 : circle.getCenterX());
		circle.setCenterY(circle.getCenterY());
	}


	public void up() {
		circle.setCenterY(circle.getCenterY() > circle.getRadius() ?
			circle.getCenterY() - 20 : circle.getCenterY());
		circle.setCenterX(circle.getCenterX());
	}


	public void down() {
		circle.setCenterX(circle.getCenterX());
		circle.setCenterY(circle.getCenterY() < getHeight() - circle.getRadius() ?
			circle.getCenterY() + 20 : circle.getCenterY());
	}
}
