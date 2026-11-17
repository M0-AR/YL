package Exercise_14._09Extra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_09Extra extends Application {
	private int[][] positions;
	private List<List<Integer>> edges;

	@Override
	public void start(Stage primaryStage) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name for the graph: ");
		String filename = input.nextLine();

		try (Scanner file = new Scanner(new File(filename))) {
			readGraph(file);
		}
		catch (Exception ex) {
			System.out.println("Unable to read " + filename);
			return;
		}

		Scene scene = new Scene(new GraphView(positions, edges), 570, 310);
		primaryStage.setTitle("Exercise_14_09Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void readGraph(Scanner file) {
		int numberOfVertices = Integer.parseInt(file.nextLine().trim());
		positions = new int[numberOfVertices][2];
		edges = new ArrayList<>();

		for (int i = 0; i < numberOfVertices; i++) {
			String[] tokens = file.nextLine().trim().split("\\s+");
			positions[i][0] = Integer.parseInt(tokens[1]);
			positions[i][1] = Integer.parseInt(tokens[2]);

			List<Integer> adjacent = new ArrayList<>();
			for (int k = 3; k < tokens.length; k++) {
				adjacent.add(Integer.parseInt(tokens[k]));
			}
			edges.add(adjacent);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class GraphView extends Pane {
	private final int[][] positions;
	private final List<List<Integer>> edges;

	public GraphView(int[][] positions, List<List<Integer>> edges) {
		this.positions = positions;
		this.edges = edges;
		paint();
	}

	private void paint() {
		for (int i = 0; i < edges.size(); i++) {
			for (int vertex : edges.get(i)) {
				getChildren().add(new Line(
					positions[i][0], positions[i][1],
					positions[vertex][0], positions[vertex][1]));
			}
		}

		for (int i = 0; i < positions.length; i++) {
			Circle circle = new Circle(positions[i][0], positions[i][1], 12);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			getChildren().addAll(circle,
				new Text(positions[i][0] - 4, positions[i][1] + 4, i + ""));
		}
	}
}
