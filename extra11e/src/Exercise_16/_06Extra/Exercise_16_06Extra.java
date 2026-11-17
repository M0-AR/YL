package Exercise_16._06Extra;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_16_06Extra extends Application {
	private static final DateTimeFormatter FORMAT =
		DateTimeFormatter.ofPattern("HH:mm:ss");
	private final Label timeLabel = new Label();
	private final Label alarmLabel = new Label("No alarm set");
	private final CheckBox alarmCheck = new CheckBox("Alarm");
	private int alarmHour;
	private int alarmMinute;
	private int alarmSecond;
	private boolean alarmSet;
	private boolean alarmTriggered;

	@Override
	public void start(Stage primaryStage) {
		timeLabel.setStyle(
			"-fx-font-family: monospace; -fx-font-size: 48px;");
		timeLabel.setText(LocalTime.now().format(FORMAT));

		Button setAlarm = new Button("Set Alarm");
		setAlarm.setOnAction(e -> showAlarmDialog(primaryStage));
		alarmCheck.setOnAction(e -> alarmTriggered = false);

		HBox controls = new HBox(10, alarmCheck, setAlarm);
		controls.setAlignment(Pos.CENTER);

		VBox root = new VBox(15, timeLabel, controls, alarmLabel);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(20));

		Timeline clock = new Timeline(
			new KeyFrame(Duration.seconds(1), e -> update(primaryStage)));
		clock.setCycleCount(Timeline.INDEFINITE);
		clock.play();

		Scene scene = new Scene(root, 380, 220);
		primaryStage.setTitle("Exercise_16_06Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void update(Stage owner) {
		LocalTime now = LocalTime.now();
		timeLabel.setText(now.format(FORMAT));

		if (alarmCheck.isSelected() && alarmSet && !alarmTriggered
				&& now.getHour() == alarmHour
				&& now.getMinute() == alarmMinute
				&& now.getSecond() >= alarmSecond) {
			alarmTriggered = true;
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(owner);
			alert.setTitle("Alarm");
			alert.setHeaderText("Alarm");
			alert.setContentText("The alarm time has arrived.");
			alert.show();
		}
	}

	private void showAlarmDialog(Stage owner) {
		Stage dialog = new Stage();
		dialog.initOwner(owner);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("Set Alarm");

		Spinner<Integer> hour = new Spinner<>(0, 23, alarmSet ? alarmHour : 0);
		Spinner<Integer> minute = new Spinner<>(0, 59,
			alarmSet ? alarmMinute : 0);
		Spinner<Integer> second = new Spinner<>(0, 59,
			alarmSet ? alarmSecond : 0);

		GridPane grid = new GridPane();
		grid.setHgap(8);
		grid.setVgap(8);
		grid.add(new Label("Hour"), 0, 0);
		grid.add(hour, 1, 0);
		grid.add(new Label("Minute"), 0, 1);
		grid.add(minute, 1, 1);
		grid.add(new Label("Second"), 0, 2);
		grid.add(second, 1, 2);

		Button ok = new Button("OK");
		Button cancel = new Button("Cancel");
		ok.setOnAction(e -> {
			alarmHour = hour.getValue();
			alarmMinute = minute.getValue();
			alarmSecond = second.getValue();
			alarmSet = true;
			alarmTriggered = false;
			alarmCheck.setSelected(true);
			alarmLabel.setText(String.format("Alarm set for %02d:%02d:%02d",
				alarmHour, alarmMinute, alarmSecond));
			dialog.close();
		});
		cancel.setOnAction(e -> dialog.close());

		HBox buttons = new HBox(8, ok, cancel);
		buttons.setAlignment(Pos.CENTER_RIGHT);

		VBox root = new VBox(10, new Label("Enter alarm time"), grid, buttons);
		root.setPadding(new Insets(10));

		dialog.setScene(new Scene(root, 260, 200));
		dialog.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
