package Exercise_28._19;

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
    private final String[] names;
    private AbstractGraph.Tree tree;

    public GraphView(UnweightedGraph<Integer> graph, Map<Integer, double[]> positions, String[] names) {
        this.graph = graph;
        this.positions = positions;
        this.names = names;
        setPrefSize(760, 420);
        draw();
    }

    public void setTree(AbstractGraph.Tree tree) {
        this.tree = tree;
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
                    drawLine(u, v, Color.LIGHTGRAY, 1);
                }
            }
        }
        if (tree != null) {
            for (int v = 0; v < graph.getSize(); v++) {
                int parent = tree.getParent(v);
                if (parent != -1) {
                    drawLine(parent, v, Color.RED, 2.5);
                }
            }
        }
        for (int i = 0; i < graph.getSize(); i++) {
            double[] point = positions.get(i);
            Circle circle = new Circle(point[0], point[1], 16, Color.LIGHTYELLOW);
            circle.setStroke(Color.DARKSLATEGRAY);
            Text text = new Text(point[0] - label(i).length() * 2.8,
                    point[1] + 4, label(i));
            getChildren().addAll(circle, text);
        }
    }

    private void drawLine(int u, int v, Color color, double width) {
        double[] first = positions.get(u);
        double[] second = positions.get(v);
        Line line = new Line(first[0], first[1], second[0], second[1]);
        line.setStroke(color);
        line.setStrokeWidth(width);
        getChildren().add(line);
    }

    private String label(int index) {
        return names != null && index < names.length ? names[index] : String.valueOf(index);
    }
}
