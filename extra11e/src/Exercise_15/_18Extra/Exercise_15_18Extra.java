package Exercise_15._18Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_15_18Extra extends Application {
	private static final double WIDTH = 470;
	private static final double HEIGHT = 410;
	private Rectangle selected;

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		for (int i = 0; i < 10; i++) {
			Rectangle rectangle = new Rectangle(
				Math.random() * (WIDTH - 150),
				Math.random() * (HEIGHT - 100), 150, 100);
			rectangle.setFill(Color.WHITE);
			rectangle.setStroke(Color.BLACK);
			pane.getChildren().add(rectangle);
		}

		pane.setOnMousePressed(e -> {
			selected = null;
			for (int i = pane.getChildren().size() - 1; i >= 0; i--) {
				Rectangle rectangle = (Rectangle)pane.getChildren().get(i);
				if (rectangle.contains(e.getX(), e.getY())) {
					selected = rectangle;
					pane.getChildren().remove(rectangle);
					pane.getChildren().add(rectangle);
					break;
				}
			}
		});

		pane.setOnMouseDragged(e -> {
			if (selected != null) {
				selected.setX(e.getX() - selected.getWidth() / 2);
				selected.setY(e.getY() - selected.getHeight() / 2);
			}
		});

		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		primaryStage.setTitle("Exercise_15_18Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
