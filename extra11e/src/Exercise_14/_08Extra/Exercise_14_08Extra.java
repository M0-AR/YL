package Exercise_14._08Extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise_14_08Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		HBox pane = new HBox(5);
		for (int sides = 5; sides <= 10; sides++) {
			pane.getChildren().add(new RegularPolygonPane(sides));
		}

		Scene scene = new Scene(pane, 700, 300);
		primaryStage.setTitle("Exercise_14_08Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class RegularPolygonPane extends StackPane {
	private final int numberOfSides;
	private final Polygon polygon = new Polygon();

	public RegularPolygonPane(int numberOfSides) {
		this.numberOfSides = numberOfSides;
		polygon.setFill(Color.WHITE);
		polygon.setStroke(Color.BLACK);
		getChildren().add(polygon);
		setPrefSize(100, 250);
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		double radius = Math.min(getWidth(), getHeight()) * 0.4;

		polygon.getPoints().clear();
		for (int i = 0; i < numberOfSides; i++) {
			double angle = 2 * i * Math.PI / numberOfSides;
			polygon.getPoints().addAll(
				centerX + radius * Math.cos(angle),
				centerY - radius * Math.sin(angle));
		}
	}
}
