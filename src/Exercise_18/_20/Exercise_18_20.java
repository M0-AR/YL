// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_18._20;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Exercise_18_20 extends Application {
	protected static StackPane pane = new StackPane();

	@Override
	public void start(Stage primaryStage) {
		int numberOfCircles = 16;
		addCircles(numberOfCircles);
		pane.setPadding(new Insets(10, 10, 10, 10));


		Scene scene = new Scene(pane);
		primaryStage.setTitle("Exercise_18_20");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void addCircles(int n) {
		if (n > 0) {
			Circle circle = new Circle(10 * n);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			pane.getChildren().add(circle);
			addCircles(n - 1);
		}
	}
}
