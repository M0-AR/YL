package Exercise_16._09Extra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_16_09Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		BeanMachinePane machine = new BeanMachinePane();
		Spinner<Integer> slots = new Spinner<>(2, 12, 9);
		Button start = new Button("Start");
		Button stop = new Button("Stop");

		start.setOnAction(e -> {
			machine.setNumberOfSlots(slots.getValue());
			machine.startAnimation();
		});
		stop.setOnAction(e -> machine.stopAnimation());

		HBox controls = new HBox(5, new Label("Number of slots:"), slots,
			start, stop);
		controls.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setCenter(machine);
		root.setBottom(controls);

		Scene scene = new Scene(root, 520, 420);
		primaryStage.setTitle("Exercise_16_09Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class BeanMachinePane extends Pane {
		private static final double GAP = 24;
		private static final double VGAP = 26;
		private static final double PEG_RADIUS = 4;
		private static final double BALL_RADIUS = 6;
		private static final double TOP_Y = 50;
		private static final double SLOT_HEIGHT = 50;

		private int numberOfSlots = 9;
		private int rows = 8;
		private int[] counts = new int[numberOfSlots];
		private int ballRow = -2;
		private int position;
		private final Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(250), e -> tick()));

		BeanMachinePane() {
			animation.setCycleCount(Timeline.INDEFINITE);
			paint();
		}

		void setNumberOfSlots(int slots) {
			numberOfSlots = Math.max(2, Math.min(12, slots));
			rows = numberOfSlots - 1;
			counts = new int[numberOfSlots];
			ballRow = -2;
			position = 0;
			paint();
		}

		void startAnimation() {
			animation.stop();
			counts = new int[numberOfSlots];
			ballRow = -2;
			position = 0;
			paint();
			animation.playFromStart();
		}

		void stopAnimation() {
			animation.stop();
		}

		private void tick() {
			if (ballRow == -2) {
				ballRow = 0;
				position = 0;
			}
			else if (ballRow < rows) {
				ballRow++;
				if (Math.random() < 0.5) {
					position++;
				}
			}
			else {
				counts[position]++;
				ballRow = -2;
			}
			paint();
		}

		private void paint() {
			getChildren().clear();
			double centerX = getWidth() > 0 ? getWidth() / 2 : 260;

			getChildren().addAll(
				new Line(centerX - GAP / 2, 10, centerX - GAP / 2, TOP_Y),
				new Line(centerX + GAP / 2, 10, centerX + GAP / 2, TOP_Y));

			for (int row = 0; row < rows; row++) {
				double y = TOP_Y + row * VGAP;
				for (int k = 0; k <= row; k++) {
					double x = centerX + (k - row / 2.0) * GAP;
					Circle peg = new Circle(x, y, PEG_RADIUS);
					peg.setFill(Color.GREEN);
					getChildren().add(peg);
				}
			}

			double slotTop = TOP_Y + rows * VGAP;
			double left = centerX - (rows + 1) * GAP / 2;
			double right = centerX + (rows + 1) * GAP / 2;
			for (int i = 0; i <= numberOfSlots; i++) {
				double x = left + i * GAP;
				getChildren().add(new Line(x, slotTop, x, slotTop + SLOT_HEIGHT));
			}
			getChildren().add(new Line(left, slotTop + SLOT_HEIGHT,
				right, slotTop + SLOT_HEIGHT));

			for (int i = 0; i < numberOfSlots; i++) {
				double x = centerX + (i - rows / 2.0) * GAP;
				int count = counts[i];
				for (int j = 0; j < Math.min(count, 7); j++) {
					Circle ball = new Circle(x,
						slotTop + SLOT_HEIGHT - 4 - j * 7, 3);
					ball.setFill(Color.RED);
					getChildren().add(ball);
				}
				Text label = new Text(x - 3, slotTop + SLOT_HEIGHT + 15,
					count + "");
				getChildren().add(label);
			}

			if (ballRow >= 0) {
				double x = centerX + (2 * position - ballRow) * GAP / 2;
				double y = TOP_Y + ballRow * VGAP + VGAP / 2;
				Circle ball = new Circle(x, y, BALL_RADIUS);
				ball.setFill(Color.RED);
				getChildren().add(ball);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
