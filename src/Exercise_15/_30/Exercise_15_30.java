// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._30;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_30 extends Application {
	@Override
	public void start(Stage primaryStage) {

		ImagePane pane = new ImagePane();





		Scene scene = new Scene(pane);
		primaryStage.setTitle("Exercise_15_30");
		primaryStage.setScene(scene);
		primaryStage.show();

		pane.requestFocus();
	}
}
