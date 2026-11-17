package Exercise_16._17Extra;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_16_17Extra extends Application {
	@Override
	public void start(Stage stage) {
		Slider red = slider();
		Slider green = slider();
		Slider blue = slider();
		Rectangle preview = new Rectangle(180, 90);
		Label hex = new Label();
		update(preview, hex, red, green, blue);
		red.valueProperty().addListener((observable, oldValue, newValue) ->
				update(preview, hex, red, green, blue));
		green.valueProperty().addListener((observable, oldValue, newValue) ->
				update(preview, hex, red, green, blue));
		blue.valueProperty().addListener((observable, oldValue, newValue) ->
				update(preview, hex, red, green, blue));
		GridPane controls = new GridPane();
		controls.setHgap(10);
		controls.setVgap(10);
		controls.addRow(0, new Label("Red"), red);
		controls.addRow(1, new Label("Green"), green);
		controls.addRow(2, new Label("Blue"), blue);
		controls.setAlignment(Pos.CENTER);
		VBox root = new VBox(15, preview, hex, controls);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(15));
		stage.setTitle("Exercise_16_17Extra");
		stage.setScene(new Scene(root, 360, 360));
		stage.show();
	}

	private Slider slider() {
		Slider slider = new Slider(0, 255, 128);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setPrefWidth(220);
		return slider;
	}

	private void update(Rectangle preview, Label hex, Slider red, Slider green,
			Slider blue) {
		int r = (int) red.getValue();
		int g = (int) green.getValue();
		int b = (int) blue.getValue();
		preview.setFill(Color.rgb(r, g, b));
		hex.setText(String.format("#%02X%02X%02X", r, g, b));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
