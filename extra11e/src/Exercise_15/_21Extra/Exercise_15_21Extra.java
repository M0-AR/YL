package Exercise_15._21Extra;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_21Extra extends Application {
	@Override
	public void start(Stage stage) {
		Polygon triangle = new Polygon(200, 80, 320, 280, 80, 280);
		triangle.setFill(Color.LIGHTBLUE);
		triangle.setStroke(Color.DARKBLUE);
		Rotate rotate = new Rotate(0, 200, 200);
		triangle.getTransforms().add(rotate);
		Text label = new Text(20, 30, "Rotation: 0.0 degrees");
		rotate.angleProperty().addListener((observable, oldValue, newValue) ->
				label.setText(String.format("Rotation: %.1f degrees",
						newValue.doubleValue())));
		RotateTransition transition = new RotateTransition(Duration.seconds(6), triangle);
		transition.setByAngle(360);
		transition.setCycleCount(RotateTransition.INDEFINITE);
		transition.setAutoReverse(false);
		transition.play();
		Pane pane = new Pane(triangle, label);
		stage.setTitle("Exercise_15_21Extra");
		stage.setScene(new Scene(pane, 400, 360));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
