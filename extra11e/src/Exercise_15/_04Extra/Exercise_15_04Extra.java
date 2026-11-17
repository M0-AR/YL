package Exercise_15._04Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise_15_04Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		RegularPolygonPane polygonPane = new RegularPolygonPane(6);

		Button increase = new Button("+");
		Button decrease = new Button("-");
		increase.setOnAction(e -> polygonPane.increase());
		decrease.setOnAction(e -> polygonPane.decrease());

		HBox buttons = new HBox(5, increase, decrease);
		buttons.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(polygonPane);
		pane.setBottom(buttons);
		pane.setFocusTraversable(true);

		pane.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				polygonPane.increase();
			}
			else if (e.getButton() == MouseButton.PRIMARY) {
				polygonPane.decrease();
			}
		});
		pane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				polygonPane.increase();
			}
			else if (e.getCode() == KeyCode.DOWN) {
				polygonPane.decrease();
			}
		});

		Scene scene = new Scene(pane, 270, 210);
		primaryStage.setTitle("Exercise_15_04Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
		pane.requestFocus();
	}

	class RegularPolygonPane extends Pane {
		private final Polygon polygon = new Polygon();
		private int numberOfSides;

		public RegularPolygonPane(int numberOfSides) {
			this.numberOfSides = numberOfSides;
			polygon.setFill(Color.WHITE);
			polygon.setStroke(Color.BLACK);
			getChildren().add(polygon);
			setPrefSize(200, 200);
			paint();
		}

		private void paint() {
			polygon.getPoints().clear();
			double centerX = 100;
			double centerY = 100;
			double radius = 80;

			for (int i = 0; i < numberOfSides; i++) {
				double angle = 2 * i * Math.PI / numberOfSides;
				polygon.getPoints().addAll(
					centerX + radius * Math.cos(angle),
					centerY - radius * Math.sin(angle));
			}
		}

		public void increase() {
			numberOfSides++;
			paint();
		}

		public void decrease() {
			if (numberOfSides > 3) {
				numberOfSides--;
				paint();
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
