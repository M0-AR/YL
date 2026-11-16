// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_13 extends Application {
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();


		Rectangle rectangle = new Rectangle(100, 60, 100, 40);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);


		pane.getChildren().add(rectangle);


		pane.setOnMouseMoved(e -> {
			pane.getChildren().clear();
			Text text = new Text(e.getX(), e.getY(), "Mouse point is " +
				(rectangle.contains(e.getX(), e.getY()) ? "inside " : "outside ") +
				"the rectangle");
			pane.getChildren().addAll(rectangle, text);
		});


		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setTitle("Exercise_15_13");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
