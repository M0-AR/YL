package Exercise_18._39;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise_18_39 extends Application {
    @Override
    public void start(Stage primaryStage) {
        RecursiveTreePane tree = new RecursiveTreePane();

        Slider slider = new Slider(0, 10, 8);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(240);

        Label depthLabel = new Label("Depth: 8");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int depth = (int) Math.round(newValue.doubleValue());
            depthLabel.setText("Depth: " + depth);
            tree.setDepth(depth);
        });

        Label instructions = new Label("Drag the mouse to move the tree.");
        HBox controls = new HBox(10, depthLabel, slider, instructions);
        controls.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, tree, controls);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        primaryStage.setTitle("Exercise_18_39");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        tree.setDepth(8);
    }

    static class RecursiveTreePane extends Pane {
        private int depth = 8;
        private double rootX = Double.NaN;
        private double rootY = Double.NaN;

        RecursiveTreePane() {
            setPrefSize(600, 600);
            setPickOnBounds(true);
            widthProperty().addListener(observable -> paint());
            heightProperty().addListener(observable -> paint());

            setOnMousePressed(e -> {
                rootX = e.getX();
                rootY = e.getY();
                paint();
            });
            setOnMouseDragged(e -> {
                rootX = e.getX();
                rootY = e.getY();
                paint();
            });
        }

        void setDepth(int depth) {
            this.depth = depth;
            paint();
        }

        private void paint() {
            getChildren().clear();

            if (getWidth() <= 0 || getHeight() <= 0) {
                return;
            }

            if (Double.isNaN(rootX) || Double.isNaN(rootY)) {
                rootX = getWidth() / 2;
                rootY = getHeight() - 30;
            }

            double length = Math.min(getWidth(), getHeight()) * 0.25;
            drawBranch(rootX, rootY, -90, length, depth);
        }

        private void drawBranch(double x, double y, double angle,
                                double length, int level) {
            if (level < 0) {
                return;
            }

            double endX = x + length * Math.cos(Math.toRadians(angle));
            double endY = y + length * Math.sin(Math.toRadians(angle));

            Line branch = new Line(x, y, endX, endY);
            branch.setStroke(Color.SADDLEBROWN);
            branch.setStrokeWidth(Math.max(1, level * 0.45 + 0.8));
            branch.setMouseTransparent(true);
            getChildren().add(branch);

            if (level > 0) {
                drawBranch(endX, endY, angle - 30, length * 0.7, level - 1);
                drawBranch(endX, endY, angle + 30, length * 0.7, level - 1);
            }
        }
    }
}
