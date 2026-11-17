package Exercise_25._11;

import Exercise_25._common.BST;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.LinkedHashMap;
import java.util.Map;

public class BSTView extends Pane {
    private static final double HORIZONTAL_GAP = 70;
    private static final double VERTICAL_GAP = 40;
    private static final double RADIUS = 18;

    private BST<?> tree;

    public BSTView() {
        setStyle("-fx-background-color: #f8fafc;");
    }

    public void setTree(BST<?> tree) {
        this.tree = tree;
        refresh();
    }

    public void refresh() {
        getChildren().clear();
        if (tree == null || tree.getRoot() == null) {
            setPrefSize(320, 120);
            Label empty = new Label("Empty tree");
            empty.setLayoutX(20);
            empty.setLayoutY(20);
            getChildren().add(empty);
            return;
        }

        Map<BST.TreeNode<?>, Point2D> positions = new LinkedHashMap<>();
        int[] row = {0};
        int[] maxDepth = {0};
        layout(tree.getRoot(), 0, positions, row, maxDepth);

        double width = 80 + maxDepth[0] * HORIZONTAL_GAP;
        double height = 60 + positions.size() * VERTICAL_GAP;
        setPrefSize(width, height);

        for (Map.Entry<BST.TreeNode<?>, Point2D> entry : positions.entrySet()) {
            BST.TreeNode<?> node = entry.getKey();
            Point2D point = entry.getValue();
            if (node.getLeft() != null) {
                addLine(point, positions.get(node.getLeft()));
            }
            if (node.getRight() != null) {
                addLine(point, positions.get(node.getRight()));
            }
        }

        for (Map.Entry<BST.TreeNode<?>, Point2D> entry : positions.entrySet()) {
            BST.TreeNode<?> node = entry.getKey();
            Point2D point = entry.getValue();
            Circle circle = new Circle(point.getX(), point.getY(), RADIUS);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.web("#334155"));
            circle.setStrokeWidth(1.5);
            getChildren().add(circle);

            String text = String.valueOf(node.getElement());
            Label label = new Label(text);
            label.setAlignment(Pos.CENTER);
            label.setPrefSize(RADIUS * 2, RADIUS * 2);
            label.setLayoutX(point.getX() - RADIUS);
            label.setLayoutY(point.getY() - RADIUS);
            label.setStyle("-fx-font-size: " + (text.length() > 3 ? 9 : 12) + "px;");
            label.setMouseTransparent(true);
            getChildren().add(label);
        }
    }

    private void layout(BST.TreeNode<?> node, int depth, Map<BST.TreeNode<?>, Point2D> positions,
                        int[] row, int[] maxDepth) {
        if (node == null) {
            return;
        }
        maxDepth[0] = Math.max(maxDepth[0], depth);
        layout(node.getLeft(), depth + 1, positions, row, maxDepth);
        positions.put(node, new Point2D(40 + depth * HORIZONTAL_GAP, 30 + row[0] * VERTICAL_GAP));
        row[0]++;
        layout(node.getRight(), depth + 1, positions, row, maxDepth);
    }

    private void addLine(Point2D from, Point2D to) {
        Line line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
        line.setStroke(Color.web("#94a3b8"));
        getChildren().add(line);
    }
}
