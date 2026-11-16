// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_16._16;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exercise_16_16 extends Application {
	protected ObservableList<String> items =
		FXCollections.observableArrayList("China", "Japan",
			"Korea", "India", "Malaysia", "Vitnam");
	protected ListView<String> lv = new ListView<>(items);
	protected ComboBox<String> cbo = new ComboBox<>();
	protected Label lblSelectedItems = new Label("Selected items are ");

	@Override
	public void start(Stage primaryStage) {

		cbo.getItems().addAll("SINGLE", "MULTIPLE");
		cbo.setValue("SINGLE");


		Label lblSelectionMode = new Label("Choose Selection Mode:", cbo);
		lblSelectionMode.setContentDisplay(ContentDisplay.RIGHT);


		lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


		cbo.setOnAction(e -> {
			setMode();
			setText();
		});

		lv.getSelectionModel().selectedItemProperty().addListener(
			ov -> {
			setMode();
			setText();
		});


		BorderPane pane = new BorderPane();
		pane.setTop(lblSelectionMode);
		pane.setCenter(new ScrollPane(lv));
		pane.setBottom(lblSelectedItems);
		pane.setAlignment(lblSelectionMode, Pos.CENTER);


		Scene scene = new Scene(pane, 268, 196);
		primaryStage.setTitle("Exercise_16_16");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void setMode() {
		if (cbo.getValue().equals("SINGLE"))
				lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		else
			lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	private void setText() {
		String  items = "Selected items are ";
		for (String i: lv.getSelectionModel().getSelectedItems())
			items += i + " ";
		lblSelectedItems.setText(items);
	}
}
