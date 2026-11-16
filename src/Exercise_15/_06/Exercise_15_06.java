// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_15._06;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Exercise_15_06 extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane pane = new StackPane();
		Text text1 = new Text("Java is fun");
		Text text2 = new Text("Java is powerful");
		pane.getChildren().addAll(text1);

		pane.setOnMouseClicked(e -> {
			if (pane.getChildren().contains(text1)) {
				pane.getChildren().add(text2);
				pane.getChildren().remove(text1);
			}
			else {
				pane.getChildren().add(text1);
				pane.getChildren().remove(text2);
			}
		});


		Scene scene = new Scene(pane, 150, 50);
		primaryStage.setTitle("Exercise_15_06");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
