package Exercise_15._19Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_15_19Extra extends Application {
	private static final double WIDTH = 470;
	private static final double HEIGHT = 410;
	private Rectangle selected;
	private double offsetX;
	private double offsetY;

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

		pane.setOnDragDetected(e -> {
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

			if (selected == null) {
				return;
			}

			offsetX = e.getX() - selected.getX();
			offsetY = e.getY() - selected.getY();
			Dragboard dragboard = pane.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString("move");
			dragboard.setContent(content);
			e.consume();
		});

		pane.setOnDragOver(e -> {
			if (selected != null) {
				selected.setX(e.getX() - offsetX);
				selected.setY(e.getY() - offsetY);
				e.acceptTransferModes(TransferMode.MOVE);
			}
			e.consume();
		});

		pane.setOnDragDone(e -> {
			selected = null;
			e.consume();
		});

		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		primaryStage.setTitle("Exercise_15_19Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
