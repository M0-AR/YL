// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_12 extends Application {
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();


		Circle circle = new Circle(100, 60, 50);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		pane.getChildren().add(circle);


		pane.setOnMouseMoved(e -> {
			pane.getChildren().clear();
			Text text = new Text(e.getX(), e.getY(), "Mouse point is " +
				(circle.contains(e.getX(), e.getY()) ? "inside " : "outside ") +
				"the circle");
			pane.getChildren().addAll(circle, text);
		});


		Scene scene = new Scene(pane, 350, 150);
		primaryStage.setTitle("Exercise_15_12");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
