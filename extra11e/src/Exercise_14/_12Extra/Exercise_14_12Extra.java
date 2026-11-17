package Exercise_14._12Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise_14_12Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(300, 300);
		GraphicsContext graphics = canvas.getGraphicsContext2D();

		for (double x = -1.5; x < 1.5; x += 0.005) {
			for (double y = -1.5; y < 1.5; y += 0.005) {
				int count = count(x, y);
				if (count == 50) {
					graphics.setFill(Color.BLACK);
				}
				else {
					int color = 50 - count;
					graphics.setFill(Color.rgb(
						color * 14 % 256,
						color * 6 % 256,
						color * 13 % 256));
				}
				graphics.fillRect(x * 100 + 150, y * 100 + 150, 1, 1);
			}
		}

		Pane pane = new Pane(canvas);
		Scene scene = new Scene(pane, 450, 450);
		primaryStage.setTitle("Exercise_14_12Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static int count(double x, double y) {
		Complex c = new Complex(0.2, -0.6);
		Complex z = new Complex(x, y);

		for (int i = 0; i < 50; i++) {
			z = z.multiply(z).add(c);
			if (z.abs() > 2) {
				return i;
			}
		}
		return 50;
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
