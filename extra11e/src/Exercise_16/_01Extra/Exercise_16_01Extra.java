package Exercise_16._01Extra;

import Exercise_08._03Extra.Exercise_08_03Extra;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_16_01Extra extends Application {
	private final TextField[][] original = new TextField[3][3];
	private final TextField[][] inverse = new TextField[3][3];
	private final Label status = new Label("Inverse Matrix");

	@Override
	public void start(Stage primaryStage) {
		GridPane originalPane = createMatrixPane(original);
		GridPane inversePane = createMatrixPane(inverse);

		VBox originalBox = new VBox(5, new Label("Original Matrix"), originalPane);
		VBox inverseBox = new VBox(5, status, inversePane);

		HBox matrices = new HBox(15, originalBox, inverseBox);
		matrices.setAlignment(Pos.CENTER);

		Button getInverse = new Button("Get Inverse");
		getInverse.setOnAction(e -> showInverse());

		BorderPane root = new BorderPane();
		root.setCenter(matrices);
		root.setBottom(getInverse);
		BorderPane.setAlignment(getInverse, Pos.CENTER);

		Scene scene = new Scene(root, 520, 210);
		primaryStage.setTitle("Exercise_16_01Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane createMatrixPane(TextField[][] fields) {
		GridPane pane = new GridPane();
		pane.setHgap(3);
		pane.setVgap(3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fields[i][j] = new TextField();
				fields[i][j].setPrefColumnCount(5);
				pane.add(fields[i][j], j, i);
			}
		}
		return pane;
	}

	private void showInverse() {
		double[][] matrix = new double[3][3];
		try {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					matrix[i][j] = Double.parseDouble(
						original[i][j].getText().trim());
				}
			}
		}
		catch (NumberFormatException ex) {
			status.setText("Inverse Matrix: invalid input");
			clearFields(inverse);
			return;
		}

		double[][] result = Exercise_08_03Extra.inverse(matrix);
		if (result == null) {
			status.setText("Inverse Matrix: no inverse matrix");
			clearFields(inverse);
		}
		else {
			status.setText("Inverse Matrix");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					inverse[i][j].setText(String.format("%.4f", result[i][j]));
				}
			}
		}
	}

	private void clearFields(TextField[][] fields) {
		for (TextField[] row : fields) {
			for (TextField field : row) {
				field.clear();
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
