package Exercise_15._08Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_08Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		Rectangle rectangle = new Rectangle(40, 40, 40, 60);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);

		Circle circle = new Circle();
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);

		Text text = new Text();
		Pane pane = new Pane(circle, rectangle, text);

		pane.setOnMouseClicked(e -> {
			circle.setCenterX(e.getX());
			circle.setCenterY(e.getY());
			circle.setRadius(30);
			text.setX(e.getX() + 32);
			text.setY(e.getY() - 32);

			boolean contains = circle.contains(rectangle.getX(), rectangle.getY())
				&& circle.contains(rectangle.getX() + rectangle.getWidth(),
					rectangle.getY())
				&& circle.contains(rectangle.getX() + rectangle.getWidth(),
					rectangle.getY() + rectangle.getHeight())
				&& circle.contains(rectangle.getX(),
					rectangle.getY() + rectangle.getHeight());

			if (contains) {
				text.setText("Circle contains the rectangle");
			}
			else if (circle.intersects(rectangle.getX(), rectangle.getY(),
					rectangle.getWidth(), rectangle.getHeight())) {
				text.setText("Circle intersects the rectangle");
			}
			else {
				text.setText("Circle is outside the rectangle");
			}
		});

		Scene scene = new Scene(pane, 400, 300);
		primaryStage.setTitle("Exercise_15_08Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
