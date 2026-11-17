package Exercise_16._10Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_16_10Extra extends Application {
	private static final int[][][] SETS = {
		{{1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21, 23}, {25, 27, 29, 31}},
		{{2, 3, 6, 7}, {10, 11, 14, 15}, {18, 19, 22, 23}, {26, 27, 30, 31}},
		{{4, 5, 6, 7}, {12, 13, 14, 15}, {20, 21, 22, 23}, {28, 29, 30, 31}},
		{{8, 9, 10, 11}, {12, 13, 14, 15}, {24, 25, 26, 27}, {28, 29, 30, 31}},
		{{16, 17, 18, 19}, {20, 21, 22, 23}, {24, 25, 26, 27}, {28, 29, 30, 31}}};

	private final NumberPane[] panes = new NumberPane[5];
	private final TextField birthday = new TextField();

	@Override
	public void start(Stage primaryStage) {
		HBox sets = new HBox(8);
		sets.setAlignment(Pos.CENTER);
		for (int i = 0; i < panes.length; i++) {
			panes[i] = new NumberPane(i);
			sets.getChildren().add(panes[i]);
		}

		Button guess = new Button("Guess Birthday");
		guess.setOnAction(e -> guessBirthday());
		birthday.setEditable(false);
		birthday.setPrefColumnCount(3);

		HBox bottom = new HBox(5, guess, birthday);
		bottom.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setCenter(sets);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 620, 190);
		primaryStage.setTitle("Exercise_16_10Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void guessBirthday() {
		int result = 0;
		for (int i = 0; i < panes.length; i++) {
			if (panes[i].isChecked()) {
				result += toPowerOfTwo(i);
			}
		}
		birthday.setText(result + "");
	}

	private static int toPowerOfTwo(int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= 2;
		}
		return result;
	}

	static class NumberPane extends VBox {
		private final CheckBox checkBox = new CheckBox();

		NumberPane(int setNumber) {
			GridPane grid = new GridPane();
			grid.setHgap(12);
			grid.setVgap(5);
			for (int i = 0; i < SETS[setNumber].length; i++) {
				for (int j = 0; j < SETS[setNumber][i].length; j++) {
					grid.add(new Label(SETS[setNumber][i][j] + ""), j, i);
				}
			}

			setSpacing(8);
			setPadding(new javafx.geometry.Insets(5));
			setStyle("-fx-border-color: black; -fx-border-width: 1;");
			getChildren().addAll(new Label("Set " + (setNumber + 1)),
				grid, checkBox);
		}

		boolean isChecked() {
			return checkBox.isSelected();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
