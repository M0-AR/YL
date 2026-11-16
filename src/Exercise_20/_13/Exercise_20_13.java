// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_20._13;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.*;
import java.io.*;

public class Exercise_20_13 extends Application {
	protected Button btShuffle = new Button("Shuffle");
	protected Button btVerify = new Button("Verify");
	protected TextField textField = new TextField();
	protected Label lblStatus = new Label();
	protected ArrayList<ImageView> cards = new ArrayList<>();
	protected ArrayList<String> cardNumbers = new ArrayList<>();
	protected HBox paneForCards = new HBox(5);

	@Override
	public void start(Stage primaryStage) {

		HBox paneForStatus = new HBox(5);
		paneForStatus.getChildren().addAll(lblStatus, btShuffle);
		paneForStatus.setAlignment(Pos.CENTER_RIGHT);


		HBox paneForExpression = new HBox(5);
		paneForExpression.getChildren().addAll(
			new Label("Enter an expression: "), textField, btVerify);
		paneForExpression.setAlignment(Pos.CENTER);


		shuffle();


		BorderPane pane = new BorderPane();
		pane.setTop(paneForStatus);
		pane.setCenter(paneForCards);
		pane.setBottom(paneForExpression);
		pane.setPadding(new Insets(5, 5, 5, 5));


		btShuffle.setOnAction(e -> shuffle());
		btVerify.setOnAction(e -> verify());


		Scene scene = new Scene(pane, 400, 180);
		primaryStage.setTitle("Exercise_20_13");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void shuffle() {
		lblStatus.setText("");
		textField.setText("");
		cards.clear();
		cardNumbers.clear();
		paneForCards.getChildren().clear();

		for (int i = 0; i < 4; i++) {
			ImageView card;
			int cardNum;
			do {
				cardNum = (int)(1 + Math.random() * 52);
				card = new ImageView(new Image("image/card/" +
					cardNum + ".png"));
			} while (Collections.frequency(cards, card) > 1);
			cards.add(card);
			cardNumbers.add(String.valueOf(
				(cardNum % 13 == 0 ? 13 : cardNum % 13)));
		}
		paneForCards.getChildren().addAll(cards);
		paneForCards.setAlignment(Pos.CENTER);
	}



	private void verify() {
		if (expressionMatch() && correctResult()) {
			lblStatus.setText("Correct");
		}
		else if (!expressionMatch()) {
			lblStatus.setText("The numbers in the expression don't "
				+ "\nmatch the numbers in the set");
		}
		else if (!correctResult())
			lblStatus.setText("Incorrect result");
	}



	private boolean expressionMatch() {
		ArrayList<String> values = new ArrayList<>(
			Arrays.asList(textField.getText().split("[ ,(,),+,-,/,*]")));
		values.removeAll(Arrays.asList("+","-","/","*"," ","(",")",""));
		return values.containsAll(cardNumbers);
	}


	private boolean correctResult() {
		return evaluateExpression() == 24;
	}


	private int evaluateExpression() {

		Stack<Integer> operandStack = new Stack<>();


		Stack<Character> operatorStack = new Stack<>();


		String expression = insertBlanks(textField.getText());


		String[] tokens = expression.split(" ");


		for (String token: tokens) {
			if (token. length() == 0)
				continue;
			else if (token.charAt(0) == '+' || token.charAt(0) == '-') {

				while (!operatorStack.isEmpty() &&
					(operatorStack.peek() == '+' ||
					 operatorStack.peek() == '-' ||
					 operatorStack.peek() == '*' ||
					 operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}


				operatorStack.push(token.charAt(0));
			}
			else if (token.charAt(0) == '*' || token.charAt(0) == '/') {

				while (!operatorStack.isEmpty() &&
					(operatorStack.peek() == '*') ||
					 operatorStack.peek() == '/') {
					processAnOperator(operandStack, operatorStack);
				}


				operatorStack.push(token.charAt(0));
			}
			else if (token.trim().charAt(0) == '(') {
				operatorStack.push('(');
			}
			else if (token.trim().charAt(0) == ')') {

				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.pop();
			}
			else {

				operandStack.push(Integer.parseInt(token));
			}
		}


		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}


		return operandStack.pop();
	}



	private void processAnOperator(
		Stack<Integer> operandStack, Stack<Character> operatorStack) {
		char op = operatorStack.pop();
		int op1 = operandStack.pop();
		int op2 = operandStack.pop();

		if (op == '+')
			operandStack.push(op2 + op1);
		else if (op == '-')
			operandStack.push(op2 - op1);
		else if (op == '*')
			operandStack.push(op2 * op1);
		else if (op == '/')
			operandStack.push(op2 / op1);
	}

	private String insertBlanks(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
				 s.charAt(i) == '+' || s.charAt(i) == '-' ||
				 s.charAt(i) == '*' || s.charAt(i) == '/')
				result += " " + s.charAt(i) + " ";
			else
				result += s.charAt(i);
		}

		return result;
	}
}
