// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_17._13;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.*;

public class Exercise_17_13 extends Application {
	protected TextField tfFileName = new TextField();
	protected TextField tfNumberOfFiles = new TextField();

	@Override
	public void start(Stage primaryStage) {

		Label lblText = new Label(
			"If the base file is named temp.txt wth three pieces,\n" +
			"temp.txt.1, temp.txt.2, and temp.txt.3 are combined into temp.txt.");



		GridPane paneForFields = new GridPane();
		paneForFields.setHgap(5);
		paneForFields.add(new Label("Enter a file"), 0, 0);
		paneForFields.add(tfFileName, 1, 0);
		paneForFields.add(new Label("Specify the number of smaller files"), 0, 1);
		paneForFields.add(tfNumberOfFiles, 1, 1);
		paneForFields.setPadding(new Insets(5, 20, 5, 0));


		Button btStart = new Button("Start");


		BorderPane pane = new BorderPane();
		pane.setTop(lblText);
		pane.setCenter(paneForFields);
		pane.setBottom(btStart);
		pane.setAlignment(btStart, Pos.CENTER);


		btStart.setOnAction(e -> start());


		Scene scene = new Scene(pane);
		primaryStage.setTitle("Exercise_17_13");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void start() {

		int n = Integer.parseInt(tfNumberOfFiles.getText());


		RandomAccessFile[] source = new RandomAccessFile[n];

		try (
			RandomAccessFile target =
				new RandomAccessFile(tfFileName.getText(), "rw");
		) {

			for (int i = 0; i < source.length; i++) {
				source[i] = new RandomAccessFile(
					tfFileName.getText() + "." + (i + 1), "rw");
			}



			for (int i = 0; i < source.length; i++) {
				byte[] b = new byte[(int)source[i].length()];
				source[i].seek(0);
				source[i].read(b);
				target.seek(target.length());
				target.write(b);
			}
		}
		catch (IOException ex) {
			System.out.println("IO exception");
		}
	}
}
