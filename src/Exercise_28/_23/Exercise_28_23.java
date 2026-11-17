package Exercise_28._23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_28_23 extends Application {
    private static final double WIDTH = 64;
    private static final double HEIGHT = 44;
    private static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.PURPLE, Color.TEAL, Color.BROWN, Color.MAGENTA
    };
    private final Pane pane = new Pane();
    private final List<Rectangle> rectangles = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        pane.setOnMouseClicked(event -> {
            if (covered(event.getX(), event.getY())) {
                return;
            }
            Rectangle rectangle = new Rectangle(
                    event.getX() - WIDTH / 2, event.getY() - HEIGHT / 2, WIDTH, HEIGHT);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            rectangles.add(rectangle);
            pane.getChildren().add(rectangle);
            colorComponents();
        });

        stage.setTitle("Exercise 28.23: Connected rectangles");
        stage.setScene(new Scene(pane, 760, 480));
        stage.show();
    }

    private boolean covered(double x, double y) {
        for (Rectangle rectangle : rectangles) {
            if (rectangle.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    private void colorComponents() {
        int count = rectangles.size();
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
                    if (!assigned[other] && intersect(rectangles.get(current), rectangles.get(other))) {
                        assigned[other] = true;
                        queue.add(other);
                    }
                }
            }
            Color color = component.size() > 1
                    ? COLORS[colorIndex++ % COLORS.length] : Color.WHITE;
            for (int index : component) {
                rectangles.get(index).setFill(color);
            }
        }
    }

    private boolean intersect(Rectangle first, Rectangle second) {
        return first.getX() < second.getX() + second.getWidth()
                && second.getX() < first.getX() + first.getWidth()
                && first.getY() < second.getY() + second.getHeight()
                && second.getY() < first.getY() + first.getHeight();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
