package Exercise_15._17;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_15_17 extends Application {
    private static final double RADIUS = 10;

    private final Pane canvas = new Pane();
    private final Rectangle bounds = new Rectangle();
    private final List<Circle> points = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        bounds.setFill(Color.TRANSPARENT);
        bounds.setStroke(Color.BLACK);
        bounds.setStrokeWidth(1.5);
        bounds.setVisible(false);
        canvas.getChildren().add(bounds);

        Label instructions = new Label("Left-click to add a point  |  Right-click a point to remove it");
        instructions.setPadding(new Insets(8));

        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            if (e.getButton() == MouseButton.PRIMARY) {
                addPoint(x, y);
            } else if (e.getButton() == MouseButton.SECONDARY) {
                removePoint(x, y);
            }
        });

        BorderPane root = new BorderPane(canvas);
        root.setTop(instructions);

        Scene scene = new Scene(root, 600, 460);
        primaryStage.setTitle("Exercise_15_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addPoint(double x, double y) {
        Circle point = new Circle(x, y, RADIUS);
        point.setFill(Color.TRANSPARENT);
        point.setStroke(Color.BLACK);
        points.add(point);
        canvas.getChildren().add(point);
        updateBounds();
    }

    private void removePoint(double x, double y) {
        for (int i = points.size() - 1; i >= 0; i--) {
            Circle point = points.get(i);
            if (Math.hypot(x - point.getCenterX(), y - point.getCenterY()) <= RADIUS) {
                points.remove(i);
                canvas.getChildren().remove(point);
                updateBounds();
                return;
            }
        }
    }

    private void updateBounds() {
        if (points.isEmpty()) {
            bounds.setVisible(false);
            return;
        }

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        for (Circle point : points) {
            minX = Math.min(minX, point.getCenterX() - RADIUS);
            minY = Math.min(minY, point.getCenterY() - RADIUS);
            maxX = Math.max(maxX, point.getCenterX() + RADIUS);
            maxY = Math.max(maxY, point.getCenterY() + RADIUS);
        }

        bounds.setX(minX);
        bounds.setY(minY);
        bounds.setWidth(maxX - minX);
        bounds.setHeight(maxY - minY);
        bounds.setVisible(true);
        bounds.toBack();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
