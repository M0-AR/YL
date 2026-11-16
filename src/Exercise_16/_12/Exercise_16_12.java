// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_16._12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Exercise_16_12 extends Application {
	@Override
	public void start(Stage primaryStage) {

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(false);


		ScrollPane scrollPane = new ScrollPane(textArea);


		CheckBox chkEditable = new CheckBox("Editable");
		CheckBox chkWrap = new CheckBox("Wrap");


		HBox paneForButtons = new HBox(5);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForButtons.getChildren().addAll(chkEditable, chkWrap);


		BorderPane pane = new BorderPane();
		pane.setCenter(scrollPane);
		pane.setBottom(paneForButtons);


		chkEditable.setOnAction(e -> {
			textArea.setEditable(chkEditable.isSelected());
		});

		chkWrap.setOnAction(e -> {
			textArea.setWrapText(chkWrap.isSelected());
		});


		Scene scene = new Scene(pane);
		primaryStage.setTitle("Exercise_16_12");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
