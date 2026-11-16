// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._27;

import javafx.application.Application;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_27 extends Application {
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();


		Text text = new Text("Programing is fun");
		pane.getChildren().add(text);


		PathTransition pt = new PathTransition(Duration.millis(10000),
			new Line(-50, 50, 250, 50), text);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.play();


		pane.setOnMousePressed(e -> {
			pt.pause();
		});

		pane.setOnMouseReleased(e -> {
			pt.play();
		});


		Scene scene = new Scene(pane, 200, 100);
		primaryStage.setTitle("Exercise_15_27");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
