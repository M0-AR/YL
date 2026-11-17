package Exercise_25._17;

import Exercise_25._16.HuffmanTree;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.LinkedHashMap;
import java.util.Map;

public class HuffmanTreeView extends Pane {
    private static final double LEAF_GAP = 72;
    private static final double LEVEL_GAP = 82;
    private static final double RADIUS = 22;

    private HuffmanTree tree;
    private double nextLeafX;
    private int maxDepth;

    public HuffmanTreeView() {
        setStyle("-fx-background-color: #f8fafc;");
    }

    public void setTree(HuffmanTree tree) {
        this.tree = tree;
        refresh();
    }

    public void clear() {
        tree = null;
        refresh();
    }

    public void refresh() {
        getChildren().clear();
        nextLeafX = 40;
        maxDepth = 0;

        if (tree == null || tree.getRoot() == null) {
            setPrefSize(420, 180);
            Label empty = new Label("Enter text to build a Huffman tree.");
            empty.setLayoutX(24);
            empty.setLayoutY(24);
            getChildren().add(empty);
            return;
        }

        Map<HuffmanTree.Node, Point2D> positions = new LinkedHashMap<>();
        layout(tree.getRoot(), 0, positions);
        double width = Math.max(420, nextLeafX + 40);
        double height = 80 + (maxDepth + 1) * LEVEL_GAP;
        setPrefSize(width, height);

        drawEdges(tree.getRoot(), positions);
        for (Map.Entry<HuffmanTree.Node, Point2D> entry : positions.entrySet()) {
            drawNode(entry.getKey(), entry.getValue());
        }
    }

    private double layout(HuffmanTree.Node node, int depth, Map<HuffmanTree.Node, Point2D> positions) {
        maxDepth = Math.max(maxDepth, depth);
        double x;
        if (node.isLeaf()) {
            x = nextLeafX;
            nextLeafX += LEAF_GAP;
        } else {
            double left = layout(node.getLeft(), depth + 1, positions);
            double right = layout(node.getRight(), depth + 1, positions);
            x = (left + right) / 2;
        }
        positions.put(node, new Point2D(x, 40 + depth * LEVEL_GAP));
        return x;
    }

    private void drawEdges(HuffmanTree.Node node, Map<HuffmanTree.Node, Point2D> positions) {
        if (node == null || node.isLeaf()) {
            return;
        }
        Point2D parent = positions.get(node);
        Point2D left = positions.get(node.getLeft());
        Point2D right = positions.get(node.getRight());
        addLine(parent, left);
        addLine(parent, right);
        drawEdges(node.getLeft(), positions);
        drawEdges(node.getRight(), positions);
    }

    private void addLine(Point2D from, Point2D to) {
        Line line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
        line.setStroke(Color.web("#94a3b8"));
        getChildren().add(line);
    }

    private void drawNode(HuffmanTree.Node node, Point2D point) {
        Circle circle = new Circle(point.getX(), point.getY(), RADIUS);
        circle.setFill(node.isLeaf() ? Color.web("#dcfce7") : Color.web("#e0f2fe"));
        circle.setStroke(Color.web("#334155"));
        circle.setStrokeWidth(1.5);
        getChildren().add(circle);

        Label weight = new Label(String.valueOf(node.getWeight()));
        weight.setAlignment(Pos.CENTER);
        weight.setPrefSize(RADIUS * 2, 16);
        weight.setLayoutX(point.getX() - RADIUS);
        weight.setLayoutY(point.getY() - (node.isLeaf() ? 13 : 8));
        weight.setStyle("-fx-font-size: 11px; -fx-font-weight: bold;");
        weight.setMouseTransparent(true);
        getChildren().add(weight);

        if (node.isLeaf()) {
            Label symbol = new Label(describe(node.getSymbol()));
            symbol.setAlignment(Pos.CENTER);
            symbol.setPrefSize(RADIUS * 2, 16);
            symbol.setLayoutX(point.getX() - RADIUS);
            symbol.setLayoutY(point.getY() + 4);
            symbol.setStyle("-fx-font-size: 10px;");
            symbol.setMouseTransparent(true);
            getChildren().add(symbol);
        }
    }

    private String describe(Character symbol) {
        if (symbol == null) {
            return "";
        }
        return switch (symbol) {
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            case ' ' -> "sp";
            default -> String.valueOf(symbol);
        };
    }
}
