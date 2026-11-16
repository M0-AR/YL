package Exercise_18._35;

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

public class Exercise_18_35 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HTreePane tree = new HTreePane();

        Slider slider = new Slider(0, 5, 3);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(220);

        Label orderLabel = new Label("Order: 3");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int order = (int) Math.round(newValue.doubleValue());
            orderLabel.setText("Order: " + order);
            tree.setOrder(order);
        });

        HBox controls = new HBox(10, orderLabel, slider);
        controls.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, tree, controls);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        primaryStage.setTitle("Exercise_18_35");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        tree.setOrder(3);
    }

    static class HTreePane extends Pane {
        private int order = 3;

        HTreePane() {
            setPrefSize(600, 600);
            widthProperty().addListener(observable -> paint());
            heightProperty().addListener(observable -> paint());
        }

        void setOrder(int order) {
            this.order = order;
            paint();
        }

        private void paint() {
            getChildren().clear();

            if (getWidth() <= 0 || getHeight() <= 0) {
                return;
            }

            double length = Math.min(getWidth(), getHeight()) * 0.42;
            drawH(getWidth() / 2, getHeight() / 2, length, order);
        }

        private void drawH(double x, double y, double length, int level) {
            double half = length / 2;

            addLine(x - half, y - half, x - half, y + half);
            addLine(x + half, y - half, x + half, y + half);
            addLine(x - half, y, x + half, y);

            if (level > 0) {
                drawH(x - half, y - half, half, level - 1);
                drawH(x - half, y + half, half, level - 1);
                drawH(x + half, y - half, half, level - 1);
                drawH(x + half, y + half, half, level - 1);
            }
        }

        private void addLine(double startX, double startY,
                             double endX, double endY) {
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.DARKBLUE);
            line.setStrokeWidth(1);
            getChildren().add(line);
        }
    }
}
