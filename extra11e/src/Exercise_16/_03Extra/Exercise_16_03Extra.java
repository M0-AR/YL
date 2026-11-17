package Exercise_16._03Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise_16_03Extra extends Application {
	private final TextField redField = new TextField("77");
	private final TextField greenField = new TextField("58");
	private final TextField blueField = new TextField("159");
	private final MandelbrotCanvas canvas = new MandelbrotCanvas();

	@Override
	public void start(Stage primaryStage) {
		redField.setPrefColumnCount(4);
		greenField.setPrefColumnCount(4);
		blueField.setPrefColumnCount(4);

		redField.setOnAction(e -> updateColors());
		greenField.setOnAction(e -> updateColors());
		blueField.setOnAction(e -> updateColors());

		HBox controls = new HBox(5,
			new Label("Red"), redField,
			new Label("Green"), greenField,
			new Label("Blue"), blueField);
		controls.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setCenter(canvas);
		root.setBottom(controls);

		Scene scene = new Scene(root, 420, 440);
		primaryStage.setTitle("Exercise_16_03Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void updateColors() {
		try {
			canvas.setColors(
				clamp(Integer.parseInt(redField.getText().trim())),
				clamp(Integer.parseInt(greenField.getText().trim())),
				clamp(Integer.parseInt(blueField.getText().trim())));
		}
		catch (NumberFormatException ex) {
			return;
		}
	}

	private static int clamp(int value) {
		return Math.max(0, Math.min(255, value));
	}

	class MandelbrotCanvas extends Canvas {
		private static final int LIMIT = 60;
		private int red = 77;
		private int green = 58;
		private int blue = 159;

		MandelbrotCanvas() {
			super(400, 400);
			paint();
		}

		void setColors(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
			paint();
		}

		void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			int width = (int)getWidth();
			int height = (int)getHeight();

			for (int px = 0; px < width; px++) {
				for (int py = 0; py < height; py++) {
					double x = -2.0 + 4.0 * px / width;
					double y = -2.0 + 4.0 * py / height;
					int count = count(x, y);

					if (count == LIMIT) {
						gc.setFill(Color.BLACK);
					}
					else {
						gc.setFill(Color.rgb((count * red) % 256,
							(count * green) % 256,
							(count * blue) % 256));
					}
					gc.fillRect(px, py, 1, 1);
				}
			}
		}

		private int count(double realPart, double imaginaryPart) {
			double real = 0;
			double imaginary = 0;
			for (int i = 0; i < LIMIT; i++) {
				if (real * real + imaginary * imaginary > 4) {
					return i;
				}
				double newReal = real * real - imaginary * imaginary + realPart;
				imaginary = 2 * real * imaginary + imaginaryPart;
				real = newReal;
			}
			return LIMIT;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
