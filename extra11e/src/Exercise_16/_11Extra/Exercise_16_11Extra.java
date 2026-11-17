package Exercise_16._11Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_16_11Extra extends Application {
	private final Label question = new Label();
	private final Label status = new Label();
	private final Label score = new Label("Correct: 0 / 0");
	private final TextField answer = new TextField();
	private final RadioButton add = new RadioButton("Add");
	private final RadioButton subtract = new RadioButton("Subtract");
	private final RadioButton multiply = new RadioButton("Multiply");
	private final RadioButton divide = new RadioButton("Divide");
	private final RadioButton level1 = new RadioButton("Numbers from 0 to 5");
	private final RadioButton level2 = new RadioButton("Numbers from 3 to 9");
	private final RadioButton level3 = new RadioButton("Numbers from 0 to 20");
	private final RadioButton level4 = new RadioButton("Two-digit numbers");
	private double correctAnswer;
	private int correctCount;
	private int totalCount;

	@Override
	public void start(Stage primaryStage) {
		ToggleGroup operations = new ToggleGroup();
		add.setToggleGroup(operations);
		subtract.setToggleGroup(operations);
		multiply.setToggleGroup(operations);
		divide.setToggleGroup(operations);
		add.setSelected(true);
		VBox operationBox = new VBox(5, new Label("Choose a type"),
			add, subtract, multiply, divide);

		ToggleGroup levels = new ToggleGroup();
		level1.setToggleGroup(levels);
		level2.setToggleGroup(levels);
		level3.setToggleGroup(levels);
		level4.setToggleGroup(levels);
		level1.setSelected(true);
		VBox levelBox = new VBox(5, new Label("Choose a level"),
			level1, level2, level3, level4);

		HBox choices = new HBox(20, operationBox, levelBox);
		choices.setAlignment(Pos.CENTER);

		GridPane questionPane = new GridPane();
		questionPane.setHgap(8);
		questionPane.setVgap(8);
		questionPane.add(new Label("Question:"), 0, 0);
		questionPane.add(question, 1, 0);
		questionPane.add(new Label("Answer:"), 0, 1);
		questionPane.add(answer, 1, 1);

		Button generate = new Button("Generate Question");
		generate.setOnAction(e -> generateQuestion());
		HBox bottom = new HBox(10, generate, score, status);
		bottom.setAlignment(Pos.CENTER_LEFT);

		answer.setOnAction(e -> checkAnswer());

		VBox root = new VBox(15, choices, questionPane, bottom);
		root.setAlignment(Pos.TOP_CENTER);

		Scene scene = new Scene(root, 520, 300);
		primaryStage.setTitle("Exercise_16_11Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void generateQuestion() {
		int min;
		int max;
		if (level1.isSelected()) {
			min = 0;
			max = 5;
		}
		else if (level2.isSelected()) {
			min = 3;
			max = 9;
		}
		else if (level3.isSelected()) {
			min = 0;
			max = 20;
		}
		else {
			min = 10;
			max = 99;
		}

		int first = random(min, max);
		int second = random(min, max);
		String operator;

		if (add.isSelected()) {
			operator = "+";
			correctAnswer = first + second;
		}
		else if (subtract.isSelected()) {
			operator = "-";
			if (first < second) {
				int temp = first;
				first = second;
				second = temp;
			}
			correctAnswer = first - second;
		}
		else if (multiply.isSelected()) {
			operator = "*";
			correctAnswer = first * second;
		}
		else {
			operator = "/";
			if (second == 0) {
				second = 1;
			}
			correctAnswer = (double)first / second;
		}

		question.setText(first + " " + operator + " " + second + " =");
		answer.clear();
		answer.requestFocus();
		status.setText("");
	}

	private void checkAnswer() {
		if (question.getText().isEmpty()) {
			return;
		}

		double response;
		try {
			response = Double.parseDouble(answer.getText().trim());
		}
		catch (NumberFormatException ex) {
			status.setText("Enter a number");
			return;
		}

		totalCount++;
		if (Math.abs(response - correctAnswer) < 1e-9) {
			correctCount++;
			status.setText("Correct!");
		}
		else {
			status.setText("The correct answer is " + format(correctAnswer));
		}
		score.setText("Correct: " + correctCount + " / " + totalCount);
	}

	private static int random(int min, int max) {
		return min + (int)(Math.random() * (max - min + 1));
	}

	private static String format(double value) {
		if (value == Math.rint(value)) {
			return (long)value + "";
		}
		return String.valueOf(value);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
