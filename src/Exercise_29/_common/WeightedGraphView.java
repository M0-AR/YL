package Exercise_29._common;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedGraphView extends Pane {
    private final WeightedGraph<Integer> graph;
    private final Map<Integer, double[]> positions;
    private final String[] names;
    private List<Integer> path;
    private List<WeightedGraph.WeightedEdge> mstEdges;

    public WeightedGraphView(WeightedGraph<Integer> graph,
                             Map<Integer, double[]> positions, String[] names) {
        this.graph = graph;
        this.positions = positions;
        this.names = names;
        setPrefSize(760, 440);
        draw();
    }

    public void setPath(List<Integer> path) {
        this.path = path;
        this.mstEdges = null;
        draw();
    }

    public void setMST(List<WeightedGraph.WeightedEdge> edges) {
        this.mstEdges = edges;
        this.path = null;
        draw();
    }

    public void clearPath() {
        this.path = null;
        this.mstEdges = null;
        draw();
    }

    public void refresh() {
        draw();
    }

    private void draw() {
        getChildren().clear();
        Set<Long> drawn = new HashSet<>();
        for (int u = 0; u < graph.getSize(); u++) {
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                long key = Math.min(edge.u, edge.v) * 1000L + Math.max(edge.u, edge.v);
                if (edge.u < edge.v && drawn.add(key)) {
                    drawEdge(edge.u, edge.v, Color.LIGHTGRAY, 1, String.valueOf(edge.weight));
                }
            }
        }
        if (path != null) {
            for (int i = 0; i + 1 < path.size(); i++) {
                drawEdge(path.get(i), path.get(i + 1), Color.RED, 3, null);
                drawEdge(path.get(i + 1), path.get(i), Color.RED, 3, null);
            }
        }
        if (mstEdges != null) {
            for (WeightedGraph.WeightedEdge edge : mstEdges) {
                drawEdge(edge.u, edge.v, Color.RED, 3, null);
                drawEdge(edge.v, edge.u, Color.RED, 3, null);
            }
        }
        for (int i = 0; i < graph.getSize(); i++) {
            double[] point = positions.get(i);
            Circle circle = new Circle(point[0], point[1], 17, Color.LIGHTYELLOW);
            circle.setStroke(Color.DARKSLATEGRAY);
            Text text = new Text(point[0] - label(i).length() * 2.8,
                    point[1] + 4, label(i));
            getChildren().addAll(circle, text);
        }
    }

    private void drawEdge(int u, int v, Color color, double width, String label) {
        double[] first = positions.get(u);
        double[] second = positions.get(v);
        Line line = new Line(first[0], first[1], second[0], second[1]);
        line.setStroke(color);
        line.setStrokeWidth(width);
        getChildren().add(line);
        if (label != null && u < v) {
            Text text = new Text((first[0] + second[0]) / 2 + 3,
                    (first[1] + second[1]) / 2 - 3, label);
            text.setFill(Color.DIMGRAY);
            getChildren().add(text);
        }
    }

    private String label(int index) {
        return names != null && index < names.length ? names[index] : String.valueOf(index);
    }
}
