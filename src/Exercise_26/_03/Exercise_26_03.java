package Exercise_26._03;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import Exercise_26._common.AVLTree;
import Exercise_26._common.AVLTree.Node;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_26_03 extends Application {
    private final AVLTree<Integer> tree = new AVLTree<>();
    private final Pane pane = new Pane();
    private final Label status = new Label();
    private final TextField valueField = new TextField();
    private final Map<Node<Integer>, double[]> positions = new IdentityHashMap<>();
    private final Set<Integer> highlighted = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        for (int value : new int[] {25, 20, 34, 5, 30, 50}) {
            tree.insert(value);
        }

        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button search = new Button("Search");
        HBox controls = new HBox(8, new Label("Value:"), valueField, insert, delete,
                search, status);
        controls.setAlignment(Pos.CENTER);
        valueField.setPrefColumnCount(5);

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(pane);
        insert.setOnAction(event -> apply("insert"));
        delete.setOnAction(event -> apply("delete"));
        search.setOnAction(event -> apply("search"));

        refresh();
        Scene scene = new Scene(root, 760, 560);
        primaryStage.setTitle("Exercise_26_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void apply(String operation) {
        int value;
        try {
            value = Integer.parseInt(valueField.getText().trim());
        } catch (NumberFormatException ex) {
            status.setText("Enter a whole number");
            return;
        }

        highlighted.clear();
        switch (operation) {
            case "insert" -> {
                boolean added = tree.insert(value);
                highlighted.addAll(tree.pathTo(value));
                status.setText(added ? "Inserted " + value : value + " is already present");
            }
            case "delete" -> {
                highlighted.addAll(tree.pathTo(value));
                boolean removed = tree.delete(value);
                status.setText(removed ? "Deleted " + value : value + " is not present");
            }
            default -> {
                highlighted.addAll(tree.pathTo(value));
                status.setText(tree.search(value) ? "Found " + value : value + " is not present");
            }
        }
        valueField.clear();
        refresh();
    }

    private void refresh() {
        positions.clear();
        layout(tree.getRoot(), 0, new int[] {0});
        pane.getChildren().clear();
        draw(tree.getRoot());
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

        boolean active = highlighted.contains(node.element);
        Circle circle = new Circle(position[0], position[1], 20,
                active ? Color.GOLD : Color.LIGHTBLUE);
        circle.setStroke(Color.DARKBLUE);
        Text text = new Text(position[0] - 6, position[1] + 5, node.element.toString());
        pane.getChildren().addAll(circle, text);
        if (active) {
            FadeTransition fade = new FadeTransition(Duration.millis(450), circle);
            fade.setFromValue(0.25);
            fade.setToValue(1);
            fade.play();
        }
    }
}
