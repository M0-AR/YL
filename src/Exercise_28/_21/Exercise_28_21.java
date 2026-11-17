package Exercise_28._21;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_28_21 extends Application {
    private static final double RADIUS = 24;
    private static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.PURPLE, Color.TEAL, Color.BROWN, Color.MAGENTA
    };
    private final Pane pane = new Pane();
    private final List<Circle> circles = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        pane.setOnMouseClicked(event -> {
            Circle circle = new Circle(event.getX(), event.getY(), RADIUS, Color.WHITE);
            circle.setStroke(Color.BLACK);
            circles.add(circle);
            pane.getChildren().add(circle);
            colorComponents();
        });

        stage.setTitle("Exercise 28.21: Connected circles");
        stage.setScene(new Scene(pane, 760, 480));
        stage.show();
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
