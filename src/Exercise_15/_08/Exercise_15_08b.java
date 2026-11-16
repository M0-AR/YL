// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_08b extends Application {
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();


		pane.setOnMousePressed(e -> {
			pane.getChildren().add(new Text(e.getX(), e.getY(),
				"(" + e.getX() + ", " + e.getY() + ")"));
		});

		pane.setOnMouseReleased(e -> {
			pane.getChildren().clear();
		});


		Scene scene = new Scene(pane, 200, 200);
		primaryStage.setTitle("Exercise_15_08b");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
