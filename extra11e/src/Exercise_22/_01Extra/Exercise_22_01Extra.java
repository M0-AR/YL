package Exercise_22._01Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_22_01Extra extends Application {
	private static final int SIZE = 8;
	private static final double CELL_SIZE = 50;
	private final int[] queens = new int[SIZE];
	private final Label status = new Label(
		"Click the mouse to see the next move");
	private final ChessBoard board = new ChessBoard();
	private int k;

	@Override
	public void start(Stage primaryStage) {
		for (int i = 0; i < SIZE; i++) {
			queens[i] = -1;
		}

		BorderPane root = new BorderPane();
		root.setTop(status);
		BorderPane.setAlignment(status, Pos.CENTER);
		root.setCenter(board);
		Label instruction = new Label("Click the mouse to see the next move");
		root.setBottom(instruction);
		BorderPane.setAlignment(instruction, Pos.CENTER);

		board.setOnMouseClicked(e -> {
			search();
			board.repaint();
			if (k == -1) {
				status.setText("No solution found");
				board.setOnMouseClicked(null);
			}
			else if (k == SIZE) {
				status.setText("A solution found");
				board.setOnMouseClicked(null);
			}
		});

		Scene scene = new Scene(root, SIZE * CELL_SIZE + 20,
			SIZE * CELL_SIZE + 70);
		primaryStage.setTitle("Eight Queens Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
		board.repaint();
	}

	private void search() {
		if (k >= 0 && k < SIZE) {
			int column = findPosition(k);
			if (column < 0) {
				queens[k] = -1;
				k--;
			}
			else {
				queens[k] = column;
				k++;
			}
		}
	}

	private int findPosition(int row) {
		for (int column = queens[row] + 1; column < SIZE; column++) {
			if (isValid(row, column)) {
				return column;
			}
		}
		return -1;
	}

	private boolean isValid(int row, int column) {
		for (int i = 1; i <= row; i++) {
			if (queens[row - i] == column
					|| queens[row - i] == column - i
					|| queens[row - i] == column + i) {
				return false;
			}
		}
		return true;
	}

	private class ChessBoard extends Pane {
		private final Image queenImage = new Image("file:src/image/queen.jpg");

		ChessBoard() {
			setPrefSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE);
			setStyle("-fx-border-color: black");
		}

		private void repaint() {
			getChildren().clear();

			if (k >= 0 && k < SIZE) {
				Rectangle highlight = new Rectangle(0, k * CELL_SIZE,
					SIZE * CELL_SIZE, CELL_SIZE);
				highlight.setFill(Color.YELLOW);
				getChildren().add(highlight);
			}

			for (int row = 0; row < SIZE; row++) {
				int column = queens[row];
				if (column < 0) {
					continue;
				}
				ImageView image = new ImageView(queenImage);
				image.setX(column * CELL_SIZE);
				image.setY(row * CELL_SIZE);
				image.setFitWidth(CELL_SIZE);
				image.setFitHeight(CELL_SIZE);
				getChildren().add(image);
			}

			for (int i = 1; i < SIZE; i++) {
				getChildren().addAll(
					new Line(0, i * CELL_SIZE, SIZE * CELL_SIZE,
						i * CELL_SIZE),
					new Line(i * CELL_SIZE, 0, i * CELL_SIZE,
						SIZE * CELL_SIZE));
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
