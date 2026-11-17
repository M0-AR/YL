package Exercise_14._06Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_06Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		HBox pane = new HBox(10);
		pane.getChildren().addAll(
			new SquareFunction(), new SineFunction(), new CosineFunction());

		Scene scene = new Scene(pane, 900, 270);
		primaryStage.setTitle("Exercise_14_06Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

abstract class AbstractDrawFunction extends Pane {
	private final Polyline curve = new Polyline();
	protected static final double CENTER_X = 140;
	protected static final double CENTER_Y = 125;

	protected AbstractDrawFunction() {
		setPrefSize(280, 250);
		curve.setStroke(Color.BLACK);
		curve.setFill(null);
		drawFunction();
		paint();
	}

	public abstract double f(double x);

	public void drawFunction() {
		for (int x = -100; x <= 100; x++) {
			curve.getPoints().addAll(
				CENTER_X + x, CENTER_Y - f(x));
		}
	}

	private void paint() {
		getChildren().addAll(
			new Line(20, CENTER_Y, 260, CENTER_Y),
			new Line(CENTER_X, 20, CENTER_X, 230),
			new Text(250, CENTER_Y - 8, "x"),
			new Text(CENTER_X + 8, 30, "y"),
			curve);
	}
}

class SquareFunction extends AbstractDrawFunction {
	@Override
	public double f(double x) {
		return 0.01 * x * x;
	}
}

class SineFunction extends AbstractDrawFunction {
	@Override
	public double f(double x) {
		return 50 * Math.sin((x / 100.0) * 2 * Math.PI);
	}
}

class CosineFunction extends AbstractDrawFunction {
	@Override
	public double f(double x) {
		return 50 * Math.cos((x / 100.0) * 2 * Math.PI);
	}
}
