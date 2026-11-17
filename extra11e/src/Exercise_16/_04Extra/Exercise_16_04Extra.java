package Exercise_16._04Extra;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Exercise_16_04Extra extends Application {
	private static final int[][] BRACKETS = {
		{27050, 65550, 136750, 297350},
		{45200, 109250, 166500, 297350},
		{22600, 54625, 83250, 148675},
		{36250, 93650, 151650, 297350}};
	private static final double[] RATES =
		{0.15, 0.275, 0.305, 0.355, 0.391};
	private static final String[] DESCRIPTIONS = {
		"Single Filers\nUp to $27,050\t15%\n$27,051 - $65,550\t27.5%\n"
			+ "$65,551 - $136,750\t30.5%\n$136,751 - $297,350\t35.5%\n"
			+ "$297,351 or more\t39.1%",
		"Married Filing Jointly\nUp to $45,200\t15%\n"
			+ "$45,201 - $109,250\t27.5%\n$109,251 - $166,500\t30.5%\n"
			+ "$166,501 - $297,350\t35.5%\n$297,351 or more\t39.1%",
		"Married Filing Separately\nUp to $22,600\t15%\n"
			+ "$22,601 - $54,625\t27.5%\n$54,626 - $83,250\t30.5%\n"
			+ "$83,251 - $148,675\t35.5%\n$148,676 or more\t39.1%",
		"Head of Household\nUp to $36,250\t15%\n"
			+ "$36,251 - $93,650\t27.5%\n$93,651 - $151,650\t30.5%\n"
			+ "$151,651 - $297,350\t35.5%\n$297,351 or more\t39.1%"};

	private final TextField incomeField = new TextField();
	private final TextField taxField = new TextField();
	private final Label description = new Label(DESCRIPTIONS[0]);

	@Override
	public void start(Stage primaryStage) {
		RadioButton[] choices = {
			new RadioButton("Single filers"),
			new RadioButton("Married filing jointly or qualifying widow(er)"),
			new RadioButton("Married filing separately"),
			new RadioButton("Head of Household")};
		ToggleGroup group = new ToggleGroup();
		VBox statusBox = new VBox(5);
		for (int i = 0; i < choices.length; i++) {
			choices[i].setToggleGroup(group);
			choices[i].setWrapText(true);
			choices[i].setMaxWidth(230);
			final int index = i;
			choices[i].setOnAction(e ->
				description.setText(DESCRIPTIONS[index]));
			statusBox.getChildren().add(choices[i]);
		}
		choices[0].setSelected(true);

		description.setStyle("-fx-font-family: monospace; -fx-font-size: 11px;");
		HBox choicesBox = new HBox(20, statusBox, description);
		choicesBox.setAlignment(Pos.CENTER_LEFT);

		incomeField.setPrefColumnCount(10);
		taxField.setPrefColumnCount(10);
		taxField.setEditable(false);

		GridPane inputPane = new GridPane();
		inputPane.setHgap(8);
		inputPane.setVgap(8);
		inputPane.add(new Label("Taxable income"), 0, 0);
		inputPane.add(incomeField, 1, 0);
		inputPane.add(new Label("Tax"), 0, 1);
		inputPane.add(taxField, 1, 1);

		Button compute = new Button("Compute Tax");
		compute.setOnAction(e -> computeTax(choices));
		inputPane.add(compute, 1, 2);

		VBox root = new VBox(10, new Label("Select Tax Status"), choicesBox,
			inputPane);
		root.setPadding(new Insets(10));

		Scene scene = new Scene(root, 620, 320);
		primaryStage.setTitle("Exercise_16_04Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void computeTax(RadioButton[] choices) {
		int status = 0;
		for (int i = 0; i < choices.length; i++) {
			if (choices[i].isSelected()) {
				status = i;
			}
		}

		double income;
		try {
			income = Double.parseDouble(incomeField.getText().trim());
		}
		catch (NumberFormatException ex) {
			taxField.setText("Invalid income");
			return;
		}

		if (income < 0) {
			taxField.setText("Invalid income");
			return;
		}

		taxField.setText(String.format("%.2f", findTax(status, income)));
	}

	private double findTax(int status, double income) {
		if (income <= BRACKETS[status][0]) {
			return income * RATES[0];
		}

		double tax = BRACKETS[status][0] * RATES[0];
		for (int i = 1; i < BRACKETS[status].length; i++) {
			if (income <= BRACKETS[status][i]) {
				return tax + (income - BRACKETS[status][i - 1]) * RATES[i];
			}
			tax += (BRACKETS[status][i] - BRACKETS[status][i - 1]) * RATES[i];
		}
		return tax + (income - BRACKETS[status][3]) * RATES[4];
	}

	public static void main(String[] args) {
		launch(args);
	}
}
