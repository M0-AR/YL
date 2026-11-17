package Exercise_16._13Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise_16_13Extra extends Application {
	private final Cell[][] cells = new Cell[3][3];
	private final Label status = new Label("X's turn to play");
	private char whoseTurn = 'X';

	@Override
	public void start(Stage primaryStage) {
		GridPane board = new GridPane();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j] = new Cell();
				board.add(cells[i][j], j, i);
			}
		}

		Button newGame = new Button("New Game");
		newGame.setOnAction(e -> clear());

		BorderPane root = new BorderPane();
		root.setTop(newGame);
		root.setCenter(board);
		root.setBottom(status);
		BorderPane.setAlignment(newGame, Pos.CENTER);
		BorderPane.setAlignment(status, Pos.CENTER);

		Scene scene = new Scene(root, 360, 400);
		primaryStage.setTitle("Exercise_16_13Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void clear() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				cell.setToken(' ');
			}
		}
		whoseTurn = 'X';
		status.setText("X's turn to play");
	}

	private boolean isFull() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.getToken() == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isWon(char token) {
		for (int i = 0; i < 3; i++) {
			if (cells[i][0].getToken() == token
					&& cells[i][1].getToken() == token
					&& cells[i][2].getToken() == token) {
				return true;
			}
			if (cells[0][i].getToken() == token
					&& cells[1][i].getToken() == token
					&& cells[2][i].getToken() == token) {
				return true;
			}
		}

		return cells[0][0].getToken() == token
			&& cells[1][1].getToken() == token
			&& cells[2][2].getToken() == token
			|| cells[0][2].getToken() == token
			&& cells[1][1].getToken() == token
			&& cells[2][0].getToken() == token;
	}

	class Cell extends Pane {
		private char token = ' ';

		Cell() {
			setPrefSize(110, 110);
			setStyle("-fx-border-color: black;");
			setOnMouseClicked(e -> handleClick());
		}

		char getToken() {
			return token;
		}

		void setToken(char token) {
			this.token = token;
			getChildren().clear();

			if (token == 'X') {
				Line first = new Line();
				first.startXProperty().set(10);
				first.startYProperty().set(10);
				first.endXProperty().bind(widthProperty().subtract(10));
				first.endYProperty().bind(heightProperty().subtract(10));

				Line second = new Line();
				second.startXProperty().set(10);
				second.startYProperty().bind(heightProperty().subtract(10));
				second.endXProperty().bind(widthProperty().subtract(10));
				second.endYProperty().set(10);

				getChildren().addAll(first, second);
			}
			else if (token == 'O') {
				Ellipse ellipse = new Ellipse();
				ellipse.centerXProperty().bind(widthProperty().divide(2));
				ellipse.centerYProperty().bind(heightProperty().divide(2));
				ellipse.radiusXProperty().bind(
					widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(
					heightProperty().divide(2).subtract(10));
				ellipse.setFill(Color.WHITE);
				ellipse.setStroke(Color.BLACK);
				getChildren().add(ellipse);
			}
		}

		private void handleClick() {
			if (token != ' ' || whoseTurn == ' ') {
				return;
			}

			setToken(whoseTurn);
			if (isWon(whoseTurn)) {
				status.setText(whoseTurn + " won! The game is over");
				whoseTurn = ' ';
			}
			else if (isFull()) {
				status.setText("Draw! The game is over");
				whoseTurn = ' ';
			}
			else {
				whoseTurn = whoseTurn == 'X' ? 'O' : 'X';
				status.setText(whoseTurn + "'s turn");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
