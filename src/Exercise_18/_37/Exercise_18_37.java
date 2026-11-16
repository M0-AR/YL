package Exercise_18._37;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Exercise_18_37 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HilbertCurvePane curvePane = new HilbertCurvePane();

        Slider slider = new Slider(1, 7, 4);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(220);

        Label orderLabel = new Label("Order: 4");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int order = (int) Math.round(newValue.doubleValue());
            orderLabel.setText("Order: " + order);
            curvePane.setOrder(order);
        });

        HBox controls = new HBox(10, orderLabel, slider);
        controls.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, curvePane, controls);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        primaryStage.setTitle("Exercise_18_37");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        curvePane.setOrder(4);
    }

    static class HilbertCurvePane extends Pane {
        private final Polyline curve = new Polyline();
        private final List<double[]> points = new ArrayList<>();
        private int order = 4;
        private double x;
        private double y;
        private double angle;

        HilbertCurvePane() {
            setPrefSize(600, 600);
            curve.setStroke(Color.DARKBLUE);
            curve.setStrokeWidth(1.5);
            curve.setFill(Color.TRANSPARENT);
            getChildren().add(curve);

            widthProperty().addListener(observable -> paint());
            heightProperty().addListener(observable -> paint());
        }

        void setOrder(int order) {
            this.order = order;
            paint();
        }

        private void paint() {
            if (getWidth() <= 0 || getHeight() <= 0) {
                return;
            }

            points.clear();
            x = 0;
            y = 0;
            angle = 0;
            points.add(new double[]{x, y});
            hilbert(order, 90);

            double minX = Double.MAX_VALUE;
            double maxX = -Double.MAX_VALUE;
            double minY = Double.MAX_VALUE;
            double maxY = -Double.MAX_VALUE;

            for (double[] point : points) {
                minX = Math.min(minX, point[0]);
                maxX = Math.max(maxX, point[0]);
                minY = Math.min(minY, point[1]);
                maxY = Math.max(maxY, point[1]);
            }

            double spanX = maxX - minX;
            double spanY = maxY - minY;
            double margin = 20;
            double scale = Math.min(
                    (getWidth() - 2 * margin) / Math.max(spanX, 1),
                    (getHeight() - 2 * margin) / Math.max(spanY, 1));
            double offsetX = (getWidth() - spanX * scale) / 2 - minX * scale;
            double offsetY = (getHeight() - spanY * scale) / 2 - minY * scale;

            curve.getPoints().clear();
            for (double[] point : points) {
                curve.getPoints().addAll(
                        point[0] * scale + offsetX,
                        point[1] * scale + offsetY);
            }
        }

        private void hilbert(int level, double turnAngle) {
            if (level == 0) {
                return;
            }

            right(turnAngle);
            hilbert(level - 1, -turnAngle);
            forward();
            left(turnAngle);
            hilbert(level - 1, turnAngle);
            forward();
            hilbert(level - 1, turnAngle);
            left(turnAngle);
            forward();
            hilbert(level - 1, -turnAngle);
            right(turnAngle);
        }

        private void forward() {
            x += Math.cos(Math.toRadians(angle));
            y += Math.sin(Math.toRadians(angle));
            points.add(new double[]{x, y});
        }

        private void right(double degrees) {
            angle -= degrees;
        }

        private void left(double degrees) {
            angle += degrees;
        }
    }
}
