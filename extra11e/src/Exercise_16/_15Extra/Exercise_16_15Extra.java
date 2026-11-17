package Exercise_16._15Extra;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_16_15Extra extends Application {
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final int[][] DIRECTIONS =
		{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
	private final Cell[][] cells = new Cell[ROWS][COLUMNS];
	private final Label status = new Label("Your turn (Red)");
	private final PauseTransition computerDelay =
		new PauseTransition(Duration.millis(350));
	private boolean gameOver;
	private boolean userTurn = true;

	@Override
	public void start(Stage primaryStage) {
		GridPane board = new GridPane();
		board.setHgap(4);
		board.setVgap(4);
		board.setPadding(new Insets(8));
		board.setStyle("-fx-background-color: green; -fx-border-color: black;");
		board.setAlignment(Pos.CENTER);

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				cells[i][j] = new Cell(i, j);
				board.add(cells[i][j], j, i);
			}
		}

		Button newGame = new Button("New Game");
		newGame.setOnAction(e -> reset());
		computerDelay.setOnFinished(e -> computerMove());

		VBox bottom = new VBox(8, status, newGame);
		bottom.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setCenter(board);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 380, 420);
		primaryStage.setTitle("Exercise_16_15Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void userMove(int column) {
		if (gameOver || !userTurn) {
			return;
		}

		int row = drop(column, 'R');
		if (row < 0) {
			return;
		}

		List<int[]> winning = getWinningCells(row, column, 'R');
		if (winning != null) {
			gameOver = true;
			highlight(winning);
			status.setText("You win!");
			return;
		}
		if (isFull()) {
			gameOver = true;
			status.setText("Draw!");
			return;
		}

		userTurn = false;
		status.setText("Computer's turn (Yellow)");
		computerDelay.playFromStart();
	}

	private void computerMove() {
		if (gameOver) {
			return;
		}

		int column = chooseComputerColumn();
		if (column < 0) {
			gameOver = true;
			status.setText("Draw!");
			return;
		}

		int row = drop(column, 'Y');
		List<int[]> winning = getWinningCells(row, column, 'Y');
		if (winning != null) {
			gameOver = true;
			highlight(winning);
			status.setText("Computer wins!");
			return;
		}
		if (isFull()) {
			gameOver = true;
			status.setText("Draw!");
			return;
		}

		userTurn = true;
		status.setText("Your turn (Red)");
	}

	private int chooseComputerColumn() {
		int winning = findWinningColumn('Y');
		if (winning >= 0) {
			return winning;
		}

		int blocking = findWinningColumn('R');
		if (blocking >= 0) {
			return blocking;
		}

		List<Integer> available = new ArrayList<>();
		for (int column = 0; column < COLUMNS; column++) {
			if (nextOpenRow(column) >= 0) {
				available.add(column);
			}
		}
		if (available.isEmpty()) {
			return -1;
		}
		return available.get((int)(Math.random() * available.size()));
	}

	private int findWinningColumn(char token) {
		for (int column = 0; column < COLUMNS; column++) {
			int row = nextOpenRow(column);
			if (row < 0) {
				continue;
			}

			cells[row][column].token = token;
			boolean wins = getWinningCells(row, column, token) != null;
			cells[row][column].token = ' ';
			if (wins) {
				return column;
			}
		}
		return -1;
	}

	private int drop(int column, char token) {
		int row = nextOpenRow(column);
		if (row < 0) {
			return -1;
		}

		cells[row][column].token = token;
		cells[row][column].setFill(token == 'R' ? Color.RED : Color.YELLOW);
		return row;
	}

	private int nextOpenRow(int column) {
		for (int row = ROWS - 1; row >= 0; row--) {
			if (cells[row][column].token == ' ') {
				return row;
			}
		}
		return -1;
	}

	private List<int[]> getWinningCells(int row, int column, char token) {
		for (int[] direction : DIRECTIONS) {
			List<int[]> line = new ArrayList<>();
			line.add(new int[] {row, column});

			for (int sign = -1; sign <= 1; sign += 2) {
				int r = row + direction[0] * sign;
				int c = column + direction[1] * sign;
				while (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS
						&& cells[r][c].token == token) {
					line.add(new int[] {r, c});
					r += direction[0] * sign;
					c += direction[1] * sign;
				}
			}

			if (line.size() >= 4) {
				return line;
			}
		}
		return null;
	}

	private boolean isFull() {
		for (int column = 0; column < COLUMNS; column++) {
			if (nextOpenRow(column) >= 0) {
				return false;
			}
		}
		return true;
	}

	private void highlight(List<int[]> cellsToHighlight) {
		for (int[] location : cellsToHighlight) {
			Cell cell = cells[location[0]][location[1]];
			cell.setStroke(Color.GOLD);
			cell.setStrokeWidth(3);
		}
	}

	private void reset() {
		computerDelay.stop();
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				cell.token = ' ';
				cell.setFill(Color.WHITE);
				cell.setStroke(Color.BLACK);
				cell.setStrokeWidth(1);
			}
		}
		gameOver = false;
		userTurn = true;
		status.setText("Your turn (Red)");
	}

	class Cell extends Circle {
		private char token = ' ';
		private final int column;

		Cell(int row, int column) {
			super(18);
			this.column = column;
			setFill(Color.WHITE);
			setStroke(Color.BLACK);
			setOnMouseClicked(e -> userMove(column));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
