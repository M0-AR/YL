package Exercise_28._24;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_28_24 extends Application {
    private static final double RADIUS = 28;
    private static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.PURPLE, Color.TEAL, Color.BROWN, Color.MAGENTA
    };
    private final Pane pane = new Pane();
    private final List<Circle> circles = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        pane.setOnMouseClicked(event -> {
            Circle hit = circleAt(event.getX(), event.getY());
            if (hit != null) {
                circles.remove(hit);
                pane.getChildren().remove(hit);
            } else {
                Circle circle = new Circle(event.getX(), event.getY(), RADIUS);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                circles.add(circle);
                pane.getChildren().add(circle);
            }
            colorComponents();
        });

        stage.setTitle("Exercise 28.24: Connected circles with removal");
        stage.setScene(new Scene(pane, 760, 480));
        stage.show();
    }

    private Circle circleAt(double x, double y) {
        for (int i = circles.size() - 1; i >= 0; i--) {
            Circle circle = circles.get(i);
            if (Math.hypot(circle.getCenterX() - x, circle.getCenterY() - y)
                    <= circle.getRadius()) {
                return circle;
            }
        }
        return null;
    }

    private void colorComponents() {
        int count = circles.size();
        boolean[] assigned = new boolean[count];
        int colorIndex = 0;
        for (int start = 0; start < count; start++) {
            if (assigned[start]) {
                continue;
            }
            List<Integer> component = new ArrayList<>();
            List<Integer> queue = new ArrayList<>();
            queue.add(start);
            assigned[start] = true;
            for (int head = 0; head < queue.size(); head++) {
                int current = queue.get(head);
                component.add(current);
                for (int other = 0; other < count; other++) {
                    if (!assigned[other] && intersect(circles.get(current), circles.get(other))) {
                        assigned[other] = true;
                        queue.add(other);
                    }
                }
            }
            Color color = component.size() > 1
                    ? COLORS[colorIndex++ % COLORS.length] : Color.WHITE;
            for (int index : component) {
                circles.get(index).setFill(color);
            }
        }
    }

    private boolean intersect(Circle first, Circle second) {
        double distance = Math.hypot(
                first.getCenterX() - second.getCenterX(),
                first.getCenterY() - second.getCenterY());
        return distance <= first.getRadius() + second.getRadius();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
