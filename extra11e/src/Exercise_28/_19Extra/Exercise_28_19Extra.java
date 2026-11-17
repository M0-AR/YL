package Exercise_28._19Extra;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_28_19Extra extends Application {
	private static final double[][] POSITIONS = {
		{80, 60}, {260, 40}, {440, 80}, {120, 240}, {300, 220}, {480, 260}
	};
	private static final int[][] EDGES = {
		{0, 1}, {0, 3}, {1, 2}, {1, 4}, {2, 5}, {3, 4}, {4, 5}
	};
	private final List<Line> lines = new ArrayList<>();
	private final Label status = new Label(
			"Click to find the shortest path from 0 to 5");

	@Override
	public void start(Stage stage) {
		Pane pane = new Pane();
		for (int[] edge : EDGES) {
			Line line = new Line(
					POSITIONS[edge[0]][0], POSITIONS[edge[0]][1],
					POSITIONS[edge[1]][0], POSITIONS[edge[1]][1]);
			line.setStroke(Color.GRAY);
			line.setStrokeWidth(2);
			lines.add(line);
			pane.getChildren().add(line);
		}
		for (int i = 0; i < POSITIONS.length; i++) {
			Circle circle = new Circle(
					POSITIONS[i][0], POSITIONS[i][1], 16, Color.LIGHTBLUE);
			circle.setStroke(Color.DARKBLUE);
			Text text = new Text(
					POSITIONS[i][0] - 5, POSITIONS[i][1] + 5, String.valueOf(i));
			pane.getChildren().addAll(circle, text);
		}
		Button find = new Button("Find Shortest Path");
		find.setOnAction(e -> findPath());
		BorderPane root = new BorderPane(pane);
		root.setTop(status);
		root.setBottom(find);
		BorderPane.setAlignment(status, Pos.CENTER);
		BorderPane.setAlignment(find, Pos.CENTER);
		stage.setTitle("Exercise_28_19Extra");
		stage.setScene(new Scene(root, 560, 360));
		stage.show();
	}

	private void findPath() {
		for (Line line : lines) {
			line.setStroke(Color.GRAY);
			line.setStrokeWidth(2);
		}
		int[] parent = new int[POSITIONS.length];
		Arrays.fill(parent, -1);
		boolean[] visited = new boolean[POSITIONS.length];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int current = queue.remove();
			for (int[] edge : EDGES) {
				int next = edge[0] == current ? edge[1]
						: edge[1] == current ? edge[0] : -1;
				if (next >= 0 && !visited[next]) {
					visited[next] = true;
					parent[next] = current;
					queue.add(next);
				}
			}
		}
		List<Integer> path = new ArrayList<>();
		for (int at = 5; at != -1; at = parent[at]) {
			path.add(0, at);
		}
		for (int i = 0; i + 1 < path.size(); i++) {
			int a = path.get(i);
			int b = path.get(i + 1);
			for (int j = 0; j < EDGES.length; j++) {
				if ((EDGES[j][0] == a && EDGES[j][1] == b)
						|| (EDGES[j][0] == b && EDGES[j][1] == a)) {
					lines.get(j).setStroke(Color.RED);
					lines.get(j).setStrokeWidth(4);
				}
			}
		}
		status.setText("Shortest path: " + path);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
