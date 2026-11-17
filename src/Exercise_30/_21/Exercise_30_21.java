package Exercise_30._21;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Exercise_30_21 extends Application {
    @Override
    public void start(Stage stage) {
        MultipleBallPane ballPane = new MultipleBallPane();
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button add = new Button("+");
        Button subtract = new Button("-");
        start.setOnAction(event -> ballPane.start());
        stop.setOnAction(event -> ballPane.stop());
        add.setOnAction(event -> ballPane.add());
        subtract.setOnAction(event -> ballPane.subtract());

        BorderPane root = new BorderPane(ballPane);
        root.setBottom(new HBox(8, start, stop, add, subtract));
        stage.setTitle("Exercise 30.21: Colliding bouncing balls with a thread");
        stage.setScene(new Scene(root, 500, 320));
        stage.show();
        ballPane.start();
    }

    private static class MultipleBallPane extends Pane {
        private volatile boolean running;
        private final List<Ball> balls = new ArrayList<>();

        void start() {
            if (running) {
                return;
            }
            running = true;
            for (int i = 0; i < 4; i++) {
                add();
            }
            Thread thread = new Thread(() -> {
                while (running) {
                    Platform.runLater(this::moveBalls);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        void stop() {
            running = false;
        }

        void add() {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 0.6);
            Ball ball = new Ball(50, 50, 8, color);
            balls.add(ball);
            getChildren().add(ball);
        }

        void subtract() {
            if (!balls.isEmpty()) {
                Ball ball = balls.remove(balls.size() - 1);
                getChildren().remove(ball);
            }
        }

        private void moveBalls() {
            for (Ball ball : balls) {
                if (ball.getCenterX() < ball.getRadius()
                        || ball.getCenterX() > getWidth() - ball.getRadius()) {
                    ball.dx = -ball.dx;
                }
                if (ball.getCenterY() < ball.getRadius()
                        || ball.getCenterY() > getHeight() - ball.getRadius()) {
                    ball.dy = -ball.dy;
                }
                ball.setCenterX(ball.getCenterX() + ball.dx);
                ball.setCenterY(ball.getCenterY() + ball.dy);
            }
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    Ball first = balls.get(i);
                    Ball second = balls.get(j);
                    double distance = Math.hypot(
                            first.getCenterX() - second.getCenterX(),
                            first.getCenterY() - second.getCenterY());
                    if (distance <= first.getRadius() + second.getRadius()) {
                        first.setRadius(first.getRadius() + second.getRadius());
                        balls.remove(j);
                        getChildren().remove(second);
                        j--;
                    }
                }
            }
        }
    }

    private static class Ball extends Circle {
        private double dx = 1;
        private double dy = 1;

        Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            setFill(color);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
