package Exercise_25._02Extra;

import Exercise_25._commonExtra.BST;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.util.ArrayList;
import java.util.List;

public class Exercise_25_02Extra extends Application {
	private final Label status = new Label(
		"Enter a key and click Search");
	private final TreeView view = new TreeView();

	@Override
	public void start(Stage primaryStage) {
		BST<Integer> tree = new BST<>();
		int[] values = {50, 25, 75, 12, 30, 70, 80, 110, 10};
		for (int value : values) {
			tree.insert(value);
		}
		view.setTree(tree);

		TextField keyField = new TextField();
		keyField.setPrefColumnCount(4);
		keyField.setAlignment(Pos.BASELINE_RIGHT);
		Button search = new Button("Search");
		HBox controls = new HBox(5, new Label("Enter a key: "),
			keyField, search);
		controls.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		root.setTop(status);
		BorderPane.setAlignment(status, Pos.CENTER);
		root.setCenter(view);
		root.setBottom(controls);
		BorderPane.setAlignment(controls, Pos.CENTER);

		search.setOnAction(e -> {
			try {
				int key = Integer.parseInt(keyField.getText().trim());
				boolean found = tree.search(key);
				view.animate(findPath(tree, key), key,
					found ? key + " is in the tree"
						: key + " is not in the tree");
			}
			catch (NumberFormatException ex) {
				status.setText("Enter an integer key");
			}
		});

		view.repaint();
		Scene scene = new Scene(root, 520, 360);
		primaryStage.setTitle("BST Search Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private List<BST.TreeNode<Integer>> findPath(BST<Integer> tree,
			int key) {
		List<BST.TreeNode<Integer>> path = new ArrayList<>();
		BST.TreeNode<Integer> current = tree.getRoot();
		while (current != null) {
			path.add(current);
			int comparison = key - current.getElement();
			if (comparison == 0) {
				break;
			}
			current = comparison < 0 ? current.getLeft()
				: current.getRight();
		}
		return path;
	}

	private class TreeView extends Pane {
		private static final double RADIUS = 15;
		private static final double V_GAP = 50;
		private BST<Integer> tree;
		private List<BST.TreeNode<Integer>> path = new ArrayList<>();
		private int highlighted;
		private String finalStatus;
		private final Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(700), e -> advance()));

		private TreeView() {
			animation.setCycleCount(Timeline.INDEFINITE);
		}

		private void setTree(BST<Integer> tree) {
			this.tree = tree;
		}

		private void animate(List<BST.TreeNode<Integer>> path, int key,
				String finalStatus) {
			animation.stop();
			this.path = path;
			this.finalStatus = finalStatus;
			this.highlighted = path.isEmpty() ? 0 : 1;
			repaint();
			if (!path.isEmpty()) {
				animation.play();
			}
			else {
				status.setText(finalStatus);
			}
		}

		private void advance() {
			if (highlighted < path.size()) {
				highlighted++;
				repaint();
			}
			else {
				animation.stop();
				status.setText(finalStatus);
			}
		}

		private void repaint() {
			getChildren().clear();
			if (tree == null || tree.getRoot() == null) {
				return;
			}
			double width = getWidth() > 0 ? getWidth() : 520;
			display(tree.getRoot(), width / 2, V_GAP, width / 4, 0);
		}

		private void display(BST.TreeNode<Integer> node, double x,
				double y, double hGap, int level) {
			if (node.getLeft() != null) {
				getChildren().add(new Line(x - hGap, y + V_GAP, x, y));
				display(node.getLeft(), x - hGap, y + V_GAP,
					hGap / 2, level + 1);
			}
			if (node.getRight() != null) {
				getChildren().add(new Line(x + hGap, y + V_GAP, x, y));
				display(node.getRight(), x + hGap, y + V_GAP,
					hGap / 2, level + 1);
			}

			Circle circle = new Circle(x, y, RADIUS);
			boolean isHighlighted = path.indexOf(node) >= 0
				&& path.indexOf(node) < highlighted;
			circle.setFill(isHighlighted ? Color.RED : Color.WHITE);
			circle.setStroke(Color.BLACK);
			String text = node.getElement() + "";
			getChildren().addAll(circle,
				new Text(x - text.length() * 3.5, y + 4, text));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
