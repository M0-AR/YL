package Exercise_28._22;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_28_22 extends Application {
    private static final double RADIUS = 24;
    private static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.PURPLE, Color.TEAL, Color.BROWN, Color.MAGENTA
    };
    private final Pane pane = new Pane();
    private final List<Circle> circles = new ArrayList<>();
    private Circle selected;
    private double lastX;
    private double lastY;

    @Override
    public void start(Stage stage) {
        pane.setOnMousePressed(event -> {
            selected = findCircle(event.getX(), event.getY());
            if (selected == null) {
                Circle circle = new Circle(event.getX(), event.getY(), RADIUS, Color.WHITE);
                circle.setStroke(Color.BLACK);
                circles.add(circle);
                pane.getChildren().add(circle);
                colorComponents();
            } else {
                lastX = event.getX();
                lastY = event.getY();
            }
        });
        pane.setOnMouseDragged(event -> {
            if (selected != null) {
                selected.setCenterX(selected.getCenterX() + event.getX() - lastX);
                selected.setCenterY(selected.getCenterY() + event.getY() - lastY);
                lastX = event.getX();
                lastY = event.getY();
                colorComponents();
            }
        });
        pane.setOnMouseReleased(event -> selected = null);

        stage.setTitle("Exercise 28.22: Move a circle");
        stage.setScene(new Scene(pane, 760, 480));
        stage.show();
    }

    private Circle findCircle(double x, double y) {
        for (int i = circles.size() - 1; i >= 0; i--) {
            Circle circle = circles.get(i);
            if (Math.hypot(circle.getCenterX() - x, circle.getCenterY() - y) <= circle.getRadius()) {
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
            Color color = COLORS[colorIndex % COLORS.length];
            colorIndex++;
            List<Integer> queue = new ArrayList<>();
            queue.add(start);
            assigned[start] = true;
            for (int head = 0; head < queue.size(); head++) {
                int current = queue.get(head);
                circles.get(current).setFill(color);
                for (int other = 0; other < count; other++) {
                    if (!assigned[other] && connected(circles.get(current), circles.get(other))) {
                        assigned[other] = true;
                        queue.add(other);
                    }
                }
            }
        }
    }

    private boolean connected(Circle first, Circle second) {
        double dx = first.getCenterX() - second.getCenterX();
        double dy = first.getCenterY() - second.getCenterY();
        return Math.hypot(dx, dy) <= first.getRadius() + second.getRadius();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
