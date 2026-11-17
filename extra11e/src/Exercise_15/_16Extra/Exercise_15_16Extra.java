package Exercise_15._16Extra;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_16Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		GraphPane pane = new GraphPane();
		Scene scene = new Scene(pane, 650, 350);
		primaryStage.setTitle("Exercise_15_16Extra");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class GraphPane extends Pane {
		private static final double RADIUS = 20;
		private final List<Vertex> vertices = new ArrayList<>();
		private final List<Edge> edges = new ArrayList<>();
		private Vertex startVertex;
		private double mouseX;
		private double mouseY;

		public GraphPane() {
			setOnMouseClicked(e -> handleClick(e.getX(), e.getY(),
				e.getButton() == MouseButton.SECONDARY));
			setOnMouseMoved(e -> handleMove(e.getX(), e.getY(),
				e.isControlDown()));
			repaint();
		}

		private void handleClick(double x, double y, boolean secondary) {
			Vertex vertex = getVertexAt(x, y);

			if (secondary) {
				if (vertex != null) {
					vertices.remove(vertex);
					edges.removeIf(edge -> edge.first == vertex
						|| edge.second == vertex);
					if (startVertex == vertex) {
						startVertex = null;
					}
					repaint();
				}
				return;
			}

			if (vertex != null) {
				if (startVertex == null) {
					startVertex = vertex;
					mouseX = x;
					mouseY = y;
				}
				else if (vertex != startVertex) {
					edges.add(new Edge(startVertex, vertex));
					startVertex = null;
				}
				repaint();
			}
			else {
				if (startVertex != null) {
					startVertex = null;
					repaint();
				}
				else if (!isTooClose(x, y)) {
					vertices.add(new Vertex(x, y));
					repaint();
				}
			}
		}

		private void handleMove(double x, double y, boolean controlDown) {
			if (controlDown) {
				Vertex vertex = getVertexAt(x, y);
				if (vertex != null) {
					startVertex = null;
					vertex.x = x;
					vertex.y = y;
					repaint();
				}
			}
			else if (startVertex != null) {
				mouseX = x;
				mouseY = y;
				repaint();
			}
		}

		private Vertex getVertexAt(double x, double y) {
			for (int i = vertices.size() - 1; i >= 0; i--) {
				Vertex vertex = vertices.get(i);
				if (Math.hypot(vertex.x - x, vertex.y - y) <= RADIUS) {
					return vertex;
				}
			}
			return null;
		}

		private boolean isTooClose(double x, double y) {
			for (Vertex vertex : vertices) {
				if (Math.hypot(vertex.x - x, vertex.y - y)
						<= 2 * RADIUS + 20) {
					return true;
				}
			}
			return false;
		}

		private void repaint() {
			getChildren().clear();
			drawInstructions();

			for (Edge edge : edges) {
				getChildren().add(new Line(edge.first.x, edge.first.y,
					edge.second.x, edge.second.y));
			}

			if (startVertex != null) {
				getChildren().add(new Line(startVertex.x, startVertex.y,
					mouseX, mouseY));
			}

			for (Vertex vertex : vertices) {
				Circle circle = new Circle(vertex.x, vertex.y, RADIUS);
				circle.setFill(Color.WHITE);
				circle.setStroke(Color.BLACK);
				getChildren().add(circle);
			}
		}

		private void drawInstructions() {
			getChildren().addAll(
				new Text(5, 20, "INSTRUCTIONS"),
				new Text(5, 40, "Add a vertex: Left click mouse"),
				new Text(5, 60, "Remove a vertex: Right click mouse"),
				new Text(5, 80, "Move a vertex: Ctrl + move mouse"),
				new Text(5, 100, "Connect: Click a vertex, move, click another"));
		}
	}

	static class Vertex {
		double x;
		double y;

		Vertex(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge {
		Vertex first;
		Vertex second;

		Edge(Vertex first, Vertex second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
