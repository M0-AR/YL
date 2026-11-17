package Exercise_21._03Extra;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_21_03Extra extends Application {
	private static final Map<String, Integer> ELECTORAL_VOTES =
		new HashMap<>();

	static {
		String[] data = {
			"Alabama", "9", "Alaska", "3", "Arizona", "11",
			"Arkansas", "6", "California", "55", "Colorado", "9",
			"Connecticut", "7", "Delaware", "3",
			"District of Columbia", "3", "Florida", "29",
			"Georgia", "16", "Hawaii", "4", "Idaho", "4",
			"Illinois", "20", "Indiana", "11", "Iowa", "6",
			"Kansas", "6", "Kentucky", "8", "Louisiana", "8",
			"Maine", "4", "Maryland", "10", "Massachusetts", "11",
			"Michigan", "16", "Minnesota", "10", "Mississippi", "6",
			"Missouri", "10", "Montana", "3", "Nebraska", "5",
			"Nevada", "6", "New Hampshire", "4", "New Jersey", "14",
			"New Mexico", "5", "New York", "29", "North Carolina", "15",
			"North Dakota", "3", "Ohio", "18", "Oklahoma", "7",
			"Oregon", "7", "Pennsylvania", "20", "Rhode Island", "4",
			"South Carolina", "9", "South Dakota", "3", "Tennessee", "11",
			"Texas", "38", "Utah", "6", "Vermont", "3", "Virginia", "13",
			"Washington", "12", "West Virginia", "5", "Wisconsin", "10",
			"Wyoming", "3"};
		for (int i = 0; i < data.length; i += 2) {
			ELECTORAL_VOTES.put(data[i], Integer.parseInt(data[i + 1]));
		}
	}

	private final Label blueLabel = new Label("Blue: 0");
	private final Label redLabel = new Label("Red: 0");
	private int blueVotes;
	private int redVotes;

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setCenter(buildMap());

		blueLabel.setTextFill(Color.BLUE);
		redLabel.setTextFill(Color.RED);
		blueLabel.setFont(Font.font(24));
		redLabel.setFont(Font.font(24));
		HBox counts = new HBox(30, blueLabel, redLabel);
		counts.setAlignment(Pos.CENTER);
		counts.setStyle("-fx-padding: 10;");
		root.setBottom(counts);

		Scene scene = new Scene(root, 1200, 800);
		primaryStage.setTitle("Electoral Map");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Group buildMap() {
		List<List<Point2D>> pointLists = new ArrayList<>();
		List<String> states = new ArrayList<>();
		loadPolygons(pointLists, states);

		List<Polygon> polygons = new ArrayList<>();
		for (int i = 0; i < pointLists.size(); i++) {
			Polygon polygon = new Polygon();
			for (Point2D point : pointLists.get(i)) {
				polygon.getPoints().addAll(point.getX(), point.getY());
			}
			polygons.add(polygon);
		}

		Group group = new Group();
		group.getChildren().addAll(polygons);

		double minX = Double.MAX_VALUE;
		double maxX = -Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = -Double.MAX_VALUE;
		for (Polygon polygon : polygons) {
			for (int i = 0; i < polygon.getPoints().size(); i += 2) {
				double x = polygon.getPoints().get(i);
				double y = polygon.getPoints().get(i + 1);
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			}
		}

		double scale = Math.min(1100 / (maxX - minX), 720 / (maxY - minY));
		group.setScaleX(scale);
		group.setScaleY(scale);
		group.setTranslateX(-minX * scale
			+ (1200 - (maxX - minX) * scale) / 2);
		group.setTranslateY(-minY * scale
			+ (760 - (maxY - minY) * scale) / 2);

		for (int i = 0; i < polygons.size(); i++) {
			Polygon polygon = polygons.get(i);
			int votes = ELECTORAL_VOTES.getOrDefault(states.get(i), 0);

			polygon.setFill(Color.WHITE);
			polygon.setStroke(Color.BLACK);
			polygon.setOnMouseClicked(e -> {
				if (e.getButton() == MouseButton.PRIMARY) {
					if (polygon.getFill().equals(Color.BLUE)) {
						blueVotes -= votes;
					}
					redVotes += votes;
					polygon.setFill(Color.RED);
				}
				else if (e.getButton() == MouseButton.SECONDARY) {
					if (polygon.getFill().equals(Color.RED)) {
						redVotes -= votes;
					}
					blueVotes += votes;
					polygon.setFill(Color.BLUE);
				}
				else {
					if (polygon.getFill().equals(Color.BLUE)) {
						blueVotes -= votes;
					}
					else if (polygon.getFill().equals(Color.RED)) {
						redVotes -= votes;
					}
					polygon.setFill(Color.WHITE);
				}
				updateCounts();
			});
		}

		return group;
	}

	private void updateCounts() {
		blueLabel.setText("Blue: " + blueVotes);
		redLabel.setText("Red: " + redVotes);
	}

	private void loadPolygons(List<List<Point2D>> pointLists,
			List<String> states) {
		String state = null;
		List<Point2D> current = null;

		try (InputStream in = getClass().getResourceAsStream(
				"/Exercise_21/_03Extra/usmap.txt")) {
			if (in == null) {
				return;
			}
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) {
					continue;
				}

				if (Character.isLetter(line.charAt(0))) {
					if (line.equalsIgnoreCase("USA")) {
						if (current != null && !current.isEmpty()) {
							current = new ArrayList<>();
							pointLists.add(current);
							states.add(state);
						}
					}
					else {
						state = line;
						current = new ArrayList<>();
						pointLists.add(current);
						states.add(state);
					}
					continue;
				}

				String[] parts = line.split("\\s+");
				if (parts.length < 2) {
					continue;
				}
				try {
					double longitude = Double.parseDouble(parts[0]);
					double latitude = Double.parseDouble(parts[1]);
					if (current != null) {
						current.add(new Point2D(longitude, -latitude));
					}
				}
				catch (NumberFormatException ex) {
					// Skip non-coordinate lines.
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
