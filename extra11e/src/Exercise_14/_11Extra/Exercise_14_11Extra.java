package Exercise_14._11Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise_14_11Extra extends Application {
	private static final int COUNT_LIMIT = 60;

	@Override
	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(400, 400);
		GraphicsContext graphics = canvas.getGraphicsContext2D();

		for (double x = -2.0; x < 2.0; x += 0.01) {
			for (double y = -2.0; y < 2.0; y += 0.01) {
				int count = count(new Complex(x, y));
				if (count == COUNT_LIMIT) {
					graphics.setFill(Color.BLACK);
				}
				else {
					graphics.setFill(Color.rgb(
						count * 77 % 256,
						count * 58 % 256,
						count * 159 % 256));
				}
				graphics.fillRect(x * 100 + 200, y * 100 + 200, 1, 1);
			}
		}

		Pane pane = new Pane(canvas);
		Scene scene = new Scene(pane, 450, 450);
		primaryStage.setTitle("Exercise_14_11Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static int count(Complex c) {
		Complex z = new Complex(0, 0);
		for (int i = 0; i < COUNT_LIMIT; i++) {
			z = z.multiply(z).add(c);
			if (z.abs() > 2) {
				return i;
			}
		}
		return COUNT_LIMIT;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class Complex {
	private final double real;
	private final double imaginary;

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public Complex add(Complex other) {
		return new Complex(real + other.real, imaginary + other.imaginary);
	}

	public Complex multiply(Complex other) {
		return new Complex(
			real * other.real - imaginary * other.imaginary,
			imaginary * other.real + real * other.imaginary);
	}

	public double abs() {
		return Math.hypot(real, imaginary);
	}
}
