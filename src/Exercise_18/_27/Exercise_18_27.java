package Exercise_18._27;

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

public class Exercise_18_27 extends Application {
    @Override
    public void start(Stage primaryStage) {
        KochSnowflakePane snowflake = new KochSnowflakePane();

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
            snowflake.setOrder(order);
        });

        HBox controls = new HBox(10, orderLabel, slider);
        controls.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, snowflake, controls);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        primaryStage.setTitle("Exercise_18_27");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        snowflake.setOrder(3);
    }

    static class KochSnowflakePane extends Pane {
        private final Polyline snowflake = new Polyline();
        private int order = 3;

        KochSnowflakePane() {
            setPrefSize(600, 600);
            snowflake.setStroke(Color.DARKBLUE);
            snowflake.setStrokeWidth(1.2);
            snowflake.setFill(Color.TRANSPARENT);
            getChildren().add(snowflake);

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

            snowflake.getPoints().clear();

            double centerX = getWidth() / 2;
            double centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.42;
            double[] x = new double[3];
            double[] y = new double[3];

            for (int i = 0; i < 3; i++) {
                double angle = Math.toRadians(-90 + i * 120);
                x[i] = centerX + radius * Math.cos(angle);
                y[i] = centerY + radius * Math.sin(angle);
            }

            addKoch(x[0], y[0], x[1], y[1], order);
            addKoch(x[1], y[1], x[2], y[2], order);
            addKoch(x[2], y[2], x[0], y[0], order);
            snowflake.getPoints().addAll(x[0], y[0]);
        }

        private void addKoch(double x1, double y1, double x2, double y2,
                             int level) {
            if (level == 0) {
                snowflake.getPoints().addAll(x1, y1);
                return;
            }

            double dx = x2 - x1;
            double dy = y2 - y1;
            double xA = x1 + dx / 3;
            double yA = y1 + dy / 3;
            double xB = x1 + 2 * dx / 3;
            double yB = y1 + 2 * dy / 3;

            double angle = Math.toRadians(-60);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double vx = dx / 3;
            double vy = dy / 3;
            double xC = xA + vx * cos - vy * sin;
            double yC = yA + vx * sin + vy * cos;

            addKoch(x1, y1, xA, yA, level - 1);
            addKoch(xA, yA, xC, yC, level - 1);
            addKoch(xC, yC, xB, yB, level - 1);
            addKoch(xB, yB, x2, y2, level - 1);
        }
    }
}
