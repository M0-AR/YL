package Exercise_15._21;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_21 extends Application {
    private static final double CENTER_X = 200;
    private static final double CENTER_Y = 200;
    private static final double RADIUS = 120;
    private static final double POINT_RADIUS = 8;

    private final Pane pane = new Pane();
    private final Circle[] points = new Circle[3];
    private final Line[] sides = new Line[3];
    private final Text[] angleLabels = new Text[3];

    @Override
    public void start(Stage primaryStage) {
        Circle orbit = new Circle(CENTER_X, CENTER_Y, RADIUS);
        orbit.setFill(Color.TRANSPARENT);
        orbit.setStroke(Color.GRAY);
        pane.getChildren().add(orbit);

        double[] initialAngles = {Math.toRadians(-90), Math.toRadians(30), Math.toRadians(150)};
        for (int i = 0; i < points.length; i++) {
            points[i] = new Circle(
                    CENTER_X + RADIUS * Math.cos(initialAngles[i]),
                    CENTER_Y + RADIUS * Math.sin(initialAngles[i]),
                    POINT_RADIUS,
                    Color.CRIMSON);
            final int index = i;
            points[i].setOnMouseDragged(e -> {
                Point2D mouse = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
                double angle = Math.atan2(mouse.getY() - CENTER_Y, mouse.getX() - CENTER_X);
                points[index].setCenterX(CENTER_X + RADIUS * Math.cos(angle));
                points[index].setCenterY(CENTER_Y + RADIUS * Math.sin(angle));
                update();
            });
            pane.getChildren().add(points[i]);
        }

        for (int i = 0; i < sides.length; i++) {
            sides[i] = new Line();
            sides[i].setStroke(Color.BLACK);
            angleLabels[i] = new Text();
            angleLabels[i].setFont(Font.font(13));
            pane.getChildren().addAll(sides[i], angleLabels[i]);
        }

        update();

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Exercise_15_21");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update() {
        for (int i = 0; i < 3; i++) {
            Circle from = points[i];
            Circle to = points[(i + 1) % 3];
            sides[i].setStartX(from.getCenterX());
            sides[i].setStartY(from.getCenterY());
            sides[i].setEndX(to.getCenterX());
            sides[i].setEndY(to.getCenterY());
        }

        double side0 = distance(points[1], points[2]);
        double side1 = distance(points[2], points[0]);
        double side2 = distance(points[0], points[1]);
        double[] opposite = {side0, side1, side2};

        double centerX = (points[0].getCenterX() + points[1].getCenterX() + points[2].getCenterX()) / 3;
        double centerY = (points[0].getCenterY() + points[1].getCenterY() + points[2].getCenterY()) / 3;

        for (int i = 0; i < 3; i++) {
            double a = opposite[i];
            double b = opposite[(i + 1) % 3];
            double c = opposite[(i + 2) % 3];
            double cosine = (b * b + c * c - a * a) / (2 * b * c);
            double angle = Math.toDegrees(Math.acos(clamp(cosine)));
            angleLabels[i].setText(String.format("%.1f°", angle));

            double dx = centerX - points[i].getCenterX();
            double dy = centerY - points[i].getCenterY();
            double length = Math.hypot(dx, dy);
            angleLabels[i].setX(points[i].getCenterX() + dx / length * 24 - 8);
            angleLabels[i].setY(points[i].getCenterY() + dy / length * 24 + 4);
        }
    }

    private double distance(Circle a, Circle b) {
        return Math.hypot(a.getCenterX() - b.getCenterX(), a.getCenterY() - b.getCenterY());
    }

    private double clamp(double value) {
        return Math.max(-1, Math.min(1, value));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
