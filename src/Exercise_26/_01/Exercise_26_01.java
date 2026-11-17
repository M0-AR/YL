package Exercise_26._01;

import java.util.IdentityHashMap;
import java.util.Map;

import Exercise_26._common.AVLTree;
import Exercise_26._common.AVLTree.Node;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_26_01 extends Application {
    private final Pane pane = new Pane();
    private final Map<Node<Integer>, double[]> positions = new IdentityHashMap<>();

    @Override
    public void start(Stage primaryStage) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int value : new int[] {25, 20, 34, 5, 30, 50}) {
            tree.insert(value);
        }

        layout(tree.getRoot(), 0, new int[] {0});
        draw(tree.getRoot());

        pane.setPrefSize(520, 420);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise_26_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void layout(Node<Integer> node, int depth, int[] next) {
        if (node == null) {
            return;
        }
        layout(node.left, depth + 1, next);
        positions.put(node, new double[] {45 + next[0]++ * 78, 45 + depth * 82});
        layout(node.right, depth + 1, next);
    }

    private void draw(Node<Integer> node) {
        if (node == null) {
            return;
        }
        double[] position = positions.get(node);
        if (node.left != null) {
            double[] child = positions.get(node.left);
            pane.getChildren().add(new Line(position[0], position[1], child[0], child[1]));
            draw(node.left);
        }
        if (node.right != null) {
            double[] child = positions.get(node.right);
            pane.getChildren().add(new Line(position[0], position[1], child[0], child[1]));
            draw(node.right);
        }

        Circle circle = new Circle(position[0], position[1], 20, Color.LIGHTBLUE);
        circle.setStroke(Color.DARKBLUE);
        Text value = new Text(position[0] - 6, position[1] + 5, node.element.toString());
        int balance = AVLTree.heightOf(node.left) - AVLTree.heightOf(node.right);
        Text factor = new Text(position[0] + 22, position[1] - 12, "bf=" + balance);
        pane.getChildren().addAll(circle, value, factor);
    }
}
