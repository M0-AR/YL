package Exercise_22._17;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise_22_17 extends Application {
    private final Pane pane = new Pane();
    private final List<double[]> points = new ArrayList<>();
    private final Label status = new Label("Left-click to add a point, right-click to remove");

    @Override
    public void start(Stage primaryStage) {
        pane.setPrefSize(640, 460);
        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                points.add(new double[] {event.getX(), event.getY()});
            } else if (event.getButton() == MouseButton.SECONDARY) {
                removeNearest(event.getX(), event.getY());
            }
            draw();
        });

        HBox header = new HBox(status);
        header.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(pane);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Exercise_22_17");
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
        for (double[] point : points) {
            pane.getChildren().add(new Circle(point[0], point[1], 4, Color.DARKBLUE));
        }
        if (points.size() < 2) {
            status.setText("Left-click to add a point, right-click to remove");
            return;
        }

        double[] first = points.get(0);
        double[] second = points.get(1);
        double best = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double distance = Math.hypot(
                        points.get(i)[0] - points.get(j)[0],
                        points.get(i)[1] - points.get(j)[1]);
                if (distance < best) {
                    best = distance;
                    first = points.get(i);
                    second = points.get(j);
                }
            }
        }

        Line line = new Line(first[0], first[1], second[0], second[1]);
        line.setStroke(Color.CRIMSON);
        line.setStrokeWidth(2);
        pane.getChildren().add(0, line);
        status.setText(String.format("Closest distance: %.2f", best));
    }
}
