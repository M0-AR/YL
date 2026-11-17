package Exercise_16._16Extra;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Exercise_16_16Extra extends Application {
	@Override
	public void start(Stage stage) {
		ObservableList<String> leftItems = FXCollections.observableArrayList(
				"Java", "Python", "C++", "JavaScript");
		ObservableList<String> rightItems = FXCollections.observableArrayList();
		ListView<String> left = new ListView<>(leftItems);
		ListView<String> right = new ListView<>(rightItems);
		left.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		right.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Button add = new Button("Add >");
		Button addAll = new Button("Add All >>");
		Button remove = new Button("< Remove");
		Button removeAll = new Button("<< Remove All");
		add.setOnAction(e -> moveSelected(left, right));
		addAll.setOnAction(e -> moveAll(left, right));
		remove.setOnAction(e -> moveSelected(right, left));
		removeAll.setOnAction(e -> moveAll(right, left));
		VBox buttons = new VBox(8, add, addAll, remove, removeAll);
		buttons.setAlignment(Pos.CENTER);
		HBox lists = new HBox(10,
				new VBox(5, new Label("Available"), left),
				buttons,
				new VBox(5, new Label("Selected"), right));
		lists.setAlignment(Pos.CENTER);
		lists.setPadding(new Insets(10));
		stage.setTitle("Exercise_16_16Extra");
		stage.setScene(new Scene(new BorderPane(lists), 560, 300));
		stage.show();
	}

	private void moveSelected(ListView<String> source, ListView<String> target) {
		var selected = List.copyOf(source.getSelectionModel().getSelectedItems());
		source.getItems().removeAll(selected);
		target.getItems().addAll(selected);
	}

	private void moveAll(ListView<String> source, ListView<String> target) {
		target.getItems().addAll(source.getItems());
		source.getItems().clear();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
