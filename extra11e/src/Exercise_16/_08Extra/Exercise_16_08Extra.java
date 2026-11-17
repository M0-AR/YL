package Exercise_16._08Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_16_08Extra extends Application {
	private final TextArea textArea = new TextArea();

	@Override
	public void start(Stage primaryStage) {
		Button show = new Button("Show Histogram");
		show.setOnAction(e -> showHistogram());

		BorderPane root = new BorderPane();
		root.setTop(new Label("Enter text:"));
		root.setCenter(textArea);
		root.setBottom(show);
		BorderPane.setAlignment(show, Pos.CENTER);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setTitle("Exercise_16_08Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showHistogram() {
		int[] counts = new int[26];
		String text = textArea.getText().toUpperCase();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				counts[ch - 'A']++;
			}
		}

		Stage stage = new Stage();
		stage.setTitle("Histogram");
		stage.setScene(new Scene(new HistogramPane(counts), 620, 340));
		stage.show();
	}

	static class HistogramPane extends Pane {
		private final int[] counts;

		HistogramPane(int[] counts) {
			this.counts = counts;
			setPrefSize(600, 300);
			paint();
		}

		private void paint() {
			getChildren().clear();
			double width = 600;
			double height = 300;
			double margin = 30;
			double baseline = height - 40;
			int max = 0;
			for (int count : counts) {
				max = Math.max(max, count);
			}

			getChildren().add(new Line(margin, baseline, width - margin, baseline));

			double barWidth = (width - 2 * margin) / 26.0;
			for (int i = 0; i < 26; i++) {
				double x = margin + i * barWidth;
				if (max > 0 && counts[i] > 0) {
					double barHeight = counts[i] * (baseline - 40) / max;
					Rectangle bar = new Rectangle(x + 2, baseline - barHeight,
						barWidth - 4, barHeight);
					bar.setFill(Color.WHITE);
					bar.setStroke(Color.BLACK);
					getChildren().add(bar);

					Text value = new Text(x + 4, baseline - barHeight - 4,
						counts[i] + "");
					getChildren().add(value);
				}
				getChildren().add(new Text(x + barWidth / 2 - 4,
					baseline + 18, (char)('A' + i) + ""));
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
