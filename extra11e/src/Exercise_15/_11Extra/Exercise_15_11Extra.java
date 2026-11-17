package Exercise_15._11Extra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_11Extra extends Application {
	private double delta = -1;

	@Override
	public void start(Stage primaryStage) {
		ImageView imageView = new ImageView("file:src/image/us.gif");
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);

		StackPane pane = new StackPane(imageView);
		Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(50), e -> {
				if (imageView.getFitWidth() <= 50) {
					delta = 1;
				}
				else if (imageView.getFitWidth() >= 300) {
					delta = -1;
				}
				imageView.setFitWidth(imageView.getFitWidth() + delta);
				imageView.setFitHeight(imageView.getFitHeight() + delta);
			}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("Exercise_15_11Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
