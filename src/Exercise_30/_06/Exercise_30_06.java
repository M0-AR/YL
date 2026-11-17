package Exercise_30._06;

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

public class Exercise_30_06 extends Application {
    @Override
    public void start(Stage stage) {
        BallPane ballPane = new BallPane();
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        start.setOnAction(event -> ballPane.start());
        stop.setOnAction(event -> ballPane.stop());

        BorderPane root = new BorderPane(ballPane);
        root.setBottom(new HBox(8, start, stop));
        stage.setTitle("Exercise 30.06: Bouncing ball with a thread");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
        ballPane.start();
    }

    private static class BallPane extends Pane {
        private final Circle ball = new Circle(200, 150, 14, Color.CORNFLOWERBLUE);
        private volatile boolean running;
        private double dx = 2;
        private double dy = 3;

        BallPane() {
            getChildren().add(ball);
        }

        void start() {
            if (running) {
                return;
            }
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    Platform.runLater(this::move);
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

        private void move() {
            if (ball.getCenterX() < ball.getRadius()
                    || ball.getCenterX() > getWidth() - ball.getRadius()) {
                dx = -dx;
            }
            if (ball.getCenterY() < ball.getRadius()
                    || ball.getCenterY() > getHeight() - ball.getRadius()) {
                dy = -dy;
            }
            ball.setCenterX(ball.getCenterX() + dx);
            ball.setCenterY(ball.getCenterY() + dy);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
