package Exercise_16._05Extra;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_16_05Extra extends Application {
	private final TextField display = new TextField("0");
	private double storedValue;
	private double memory;
	private String pendingOperation;
	private boolean replaceDisplay = true;

	@Override
	public void start(Stage primaryStage) {
		display.setEditable(false);
		display.setAlignment(Pos.CENTER_RIGHT);
		display.setPrefColumnCount(14);
		display.setStyle("-fx-font-size: 16px;");

		HBox editButtons = new HBox(5,
			createButton("Back", this::backspace),
			createButton("CE", this::clearEntry),
			createButton("C", this::clearAll));
		editButtons.setAlignment(Pos.BASELINE_RIGHT);

		GridPane keys = new GridPane();
		keys.setHgap(5);
		keys.setVgap(5);
		addKey(keys, "MC", 0, 0, () -> memory = 0);
		addKey(keys, "7", 1, 0, () -> appendDigit("7"));
		addKey(keys, "8", 2, 0, () -> appendDigit("8"));
		addKey(keys, "9", 3, 0, () -> appendDigit("9"));
		addKey(keys, "/", 4, 0, () -> setOperator("/"));
		addKey(keys, "sqrt", 5, 0, this::squareRoot);

		addKey(keys, "MR", 0, 1, () -> showValue(memory));
		addKey(keys, "4", 1, 1, () -> appendDigit("4"));
		addKey(keys, "5", 2, 1, () -> appendDigit("5"));
		addKey(keys, "6", 3, 1, () -> appendDigit("6"));
		addKey(keys, "*", 4, 1, () -> setOperator("*"));
		addKey(keys, "%", 5, 1, () -> setOperator("%"));

		addKey(keys, "MS", 0, 2, () -> memory = currentValue());
		addKey(keys, "1", 1, 2, () -> appendDigit("1"));
		addKey(keys, "2", 2, 2, () -> appendDigit("2"));
		addKey(keys, "3", 3, 2, () -> appendDigit("3"));
		addKey(keys, "-", 4, 2, () -> setOperator("-"));
		addKey(keys, "1/x", 5, 2, this::reciprocal);

		addKey(keys, "M+", 0, 3, () -> memory += currentValue());
		addKey(keys, "0", 1, 3, () -> appendDigit("0"));
		addKey(keys, "+/-", 2, 3, this::toggleSign);
		addKey(keys, ".", 3, 3, this::appendDecimal);
		addKey(keys, "+", 4, 3, () -> setOperator("+"));
		addKey(keys, "=", 5, 3, this::equals);

		VBox root = new VBox(5, display, editButtons, keys);
		Scene scene = new Scene(root, 300, 240);
		primaryStage.setTitle("Exercise_16_05Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Button createButton(String text, Runnable action) {
		Button button = new Button(text);
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e -> action.run());
		return button;
	}

	private void addKey(GridPane pane, String text, int column, int row,
			Runnable action) {
		pane.add(createButton(text, action), column, row);
	}

	private void appendDigit(String digit) {
		if (replaceDisplay || "0".equals(display.getText())) {
			display.setText(digit);
			replaceDisplay = false;
		}
		else {
			display.setText(display.getText() + digit);
		}
	}

	private void appendDecimal() {
		if (replaceDisplay) {
			display.setText("0.");
			replaceDisplay = false;
		}
		else if (!display.getText().contains(".")) {
			display.setText(display.getText() + ".");
		}
	}

	private void toggleSign() {
		double value = currentValue();
		showValue(-value);
		replaceDisplay = false;
	}

	private void setOperator(String operator) {
		if ("Error".equals(display.getText())) {
			return;
		}
		if (pendingOperation != null && !replaceDisplay) {
			computePending();
		}
		else {
			storedValue = currentValue();
		}
		pendingOperation = operator;
		replaceDisplay = true;
	}

	private void equals() {
		if (pendingOperation != null) {
			computePending();
			pendingOperation = null;
		}
		replaceDisplay = true;
	}

	private void computePending() {
		double value = currentValue();
		double result;

		switch (pendingOperation) {
			case "+":
				result = storedValue + value;
				break;
			case "-":
				result = storedValue - value;
				break;
			case "*":
				result = storedValue * value;
				break;
			case "/":
				if (value == 0) {
					showError();
					return;
				}
				result = storedValue / value;
				break;
			case "%":
				if (value == 0) {
					showError();
					return;
				}
				result = storedValue % value;
				break;
			default:
				result = value;
		}

		storedValue = result;
		showValue(result);
	}

	private void squareRoot() {
		double value = currentValue();
		if (value < 0) {
			showError();
			return;
		}
		showValue(Math.sqrt(value));
		replaceDisplay = true;
	}

	private void reciprocal() {
		double value = currentValue();
		if (value == 0) {
			showError();
			return;
		}
		showValue(1 / value);
		replaceDisplay = true;
	}

	private void backspace() {
		if (replaceDisplay) {
			return;
		}
		String text = display.getText();
		if (text.length() <= 1 || (text.length() == 2 && text.startsWith("-"))) {
			display.setText("0");
			replaceDisplay = true;
		}
		else {
			display.setText(text.substring(0, text.length() - 1));
		}
	}

	private void clearEntry() {
		display.setText("0");
		replaceDisplay = true;
	}

	private void clearAll() {
		display.setText("0");
		storedValue = 0;
		memory = 0;
		pendingOperation = null;
		replaceDisplay = true;
	}

	private void showError() {
		display.setText("Error");
		pendingOperation = null;
		replaceDisplay = true;
	}

	private void showValue(double value) {
		if (value == 0) {
			display.setText("0");
		}
		else if (Math.abs(value) < 1e12
				&& value == Math.rint(value)) {
			display.setText((long)value + "");
		}
		else {
			display.setText(String.valueOf(value));
		}
	}

	private double currentValue() {
		try {
			return Double.parseDouble(display.getText());
		}
		catch (NumberFormatException ex) {
			return 0;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
