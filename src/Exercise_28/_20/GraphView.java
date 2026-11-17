package Exercise_28._20;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphView extends Pane {
    private final UnweightedGraph<Integer> graph;
    private final Map<Integer, double[]> positions;

    public GraphView(UnweightedGraph<Integer> graph, Map<Integer, double[]> positions) {
        this.graph = graph;
        this.positions = positions;
        setPrefSize(760, 460);
        draw();
    }

    @SuppressWarnings("unchecked")
    private void draw() {
        getChildren().clear();
        Set<Long> drawnEdges = new HashSet<>();
        for (int u = 0; u < graph.getSize(); u++) {
            for (int v : (List<Integer>) graph.getNeighbors(u)) {
                long key = Math.min(u, v) * 1000L + Math.max(u, v);
                if (u < v && drawnEdges.add(key)) {
                    double[] first = positions.get(u);
                    double[] second = positions.get(v);
                    Line line = new Line(first[0], first[1], second[0], second[1]);
                    line.setStroke(Color.GRAY);
                    getChildren().add(line);
                }
            }
        }
        for (int i = 0; i < graph.getSize(); i++) {
            double[] point = positions.get(i);
            Circle circle = new Circle(point[0], point[1], 16, Color.LIGHTYELLOW);
            circle.setStroke(Color.DARKSLATEGRAY);
            Text text = new Text(point[0] - 6, point[1] + 4, String.valueOf(i));
            getChildren().addAll(circle, text);
        }
    }
}
