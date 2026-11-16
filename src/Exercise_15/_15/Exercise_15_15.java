// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._15;

import javafx.application.Application;
import javafx.scene.input.MouseButton;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise_15_15 extends Application {
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();


		pane.setOnMouseClicked(e -> {

			Circle circle = new Circle(e.getX(), e.getY(), 5);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			if (e.getButton() == MouseButton.PRIMARY) {
				pane.getChildren().add(circle);
			}
			else if (e.getButton() == MouseButton.SECONDARY) {
				ObservableList<Node> list = pane.getChildren();
				for (int i = 0; i < list.size(); i++) {
					Circle c = (Circle)list.get(i);
					if (circle.contains(c.getCenterX(), c.getCenterY())) {
						list.remove(i);
					}
				}
			}
		});


		Scene scene = new Scene(pane);
		primaryStage.setTitle("Exercise_15_15");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
