package Exercise_22._13;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise_22_13 extends Application {
    private final Pane pane = new Pane();
    private final List<double[]> points = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        pane.setPrefSize(640, 480);
        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                points.add(new double[] {event.getX(), event.getY()});
            } else if (event.getButton() == MouseButton.SECONDARY) {
                removeNearest(event.getX(), event.getY());
            }
            draw();
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise_22_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void removeNearest(double x, double y) {
        int nearest = -1;
        double best = 12;
        for (int i = 0; i < points.size(); i++) {
            double distance = Math.hypot(points.get(i)[0] - x, points.get(i)[1] - y);
            if (distance < best) {
                best = distance;
                nearest = i;
            }
        }
        if (nearest >= 0) {
            points.remove(nearest);
        }
    }

    private void draw() {
        pane.getChildren().clear();
        List<double[]> hull = convexHull();
        if (hull.size() >= 3) {
            Polygon polygon = new Polygon();
            for (double[] point : hull) {
                polygon.getPoints().addAll(point[0], point[1]);
            }
            polygon.setFill(Color.color(0.13, 0.83, 0.93, 0.12));
            polygon.setStroke(Color.DARKBLUE);
            pane.getChildren().add(polygon);
        }
        for (double[] point : points) {
            Circle circle = new Circle(point[0], point[1], 4, Color.DARKBLUE);
            pane.getChildren().add(circle);
        }
    }

    private List<double[]> convexHull() {
        List<double[]> ordered = new ArrayList<>(points);
        if (ordered.size() <= 2) {
            return ordered;
        }
        double[] pivot = ordered.get(0);
        for (double[] point : ordered) {
            if (point[0] > pivot[0] || (point[0] == pivot[0] && point[1] < pivot[1])) {
                pivot = point;
            }
        }
        final double[] center = pivot;
        ordered.sort((first, second) -> {
            double turn = (first[0] - center[0]) * (second[1] - center[1])
                    - (first[1] - center[1]) * (second[0] - center[0]);
            if (turn > 0) {
                return -1;
            }
            if (turn < 0) {
                return 1;
            }
            return Double.compare(
                    Math.hypot(first[0] - center[0], first[1] - center[1]),
                    Math.hypot(second[0] - center[0], second[1] - center[1]));
        });

        List<double[]> hull = new ArrayList<>();
        for (double[] point : ordered) {
            while (hull.size() >= 2
                    && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(point);
        }
        return hull;
    }

    private double cross(double[] first, double[] second, double[] third) {
        return (second[0] - first[0]) * (third[1] - first[1])
                - (second[1] - first[1]) * (third[0] - first[0]);
    }
}
