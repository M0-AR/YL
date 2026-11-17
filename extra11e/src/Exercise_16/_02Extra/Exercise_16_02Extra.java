package Exercise_16._02Extra;

import Exercise_08._02Extra.Exercise_08_02Extra;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_16_02Extra extends Application {
	private final TextField[][] coefficients = new TextField[3][3];
	private final TextField[] constants = new TextField[3];
	private final Label status = new Label();

	@Override
	public void start(Stage primaryStage) {
		VBox equations = new VBox(5);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				coefficients[i][j] = new TextField();
				coefficients[i][j].setPrefColumnCount(4);
			}
			constants[i] = new TextField();
			constants[i].setPrefColumnCount(4);

			HBox row = new HBox(5,
				coefficients[i][0], new Label("x +"),
				coefficients[i][1], new Label("y +"),
				coefficients[i][2], new Label("z ="),
				constants[i]);
			row.setAlignment(Pos.CENTER_LEFT);
			equations.getChildren().add(row);
		}

		Button solve = new Button("Solve Equation");
		solve.setOnAction(e -> solve());
		HBox bottom = new HBox(10, solve, status);
		bottom.setAlignment(Pos.CENTER_LEFT);

		BorderPane root = new BorderPane();
		root.setCenter(equations);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 450, 180);
		primaryStage.setTitle("Exercise_16_02Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void solve() {
		double[][] a = new double[3][3];
		double[] b = new double[3];
		try {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					a[i][j] = Double.parseDouble(
						coefficients[i][j].getText().trim());
				}
				b[i] = Double.parseDouble(constants[i].getText().trim());
			}
		}
		catch (NumberFormatException ex) {
			status.setText("Invalid input");
			return;
		}

		double[] solution = Exercise_08_02Extra.solve(a, b);
		if (solution == null) {
			status.setText("No solution");
		}
		else {
			status.setText(String.format("x = %.4f, y = %.4f, z = %.4f",
				solution[0], solution[1], solution[2]));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
