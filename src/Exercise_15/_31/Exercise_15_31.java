package Exercise_15._31;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_31 extends Application {
    private static final double WIDTH = 400;
    private static final double HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        PendulumPane pendulumPane = new PendulumPane(WIDTH, HEIGHT);
        Scene scene = new Scene(pendulumPane, WIDTH, HEIGHT);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> pendulumPane.faster();
                case DOWN -> pendulumPane.slower();
                case S -> pendulumPane.pause();
                case R -> pendulumPane.resume();
                default -> {
                }
            }
        });

        primaryStage.setTitle("Exercise_15_31");
        primaryStage.setScene(scene);
        primaryStage.show();
        pendulumPane.start();
    }

    static class PendulumPane extends Pane {
        private final double pivotX;
        private final double pivotY;
        private final double length;
        private final Circle bob;
        private final Line rod;
        private final Arc swingArc;
        private final AnimationTimer timer;

        private double phase;
        private double speed = 2.0;
        private boolean running = true;
        private long lastNanos;

        PendulumPane(double width, double height) {
            pivotX = width / 2;
            pivotY = 70;
            length = 250;

            swingArc = new Arc(pivotX, pivotY, length, length, 230, 80);
            swingArc.setType(ArcType.OPEN);
            swingArc.setFill(Color.TRANSPARENT);
            swingArc.setStroke(Color.GRAY);

            rod = new Line();
            rod.setStroke(Color.BLACK);
            rod.setStrokeWidth(2);

            bob = new Circle(14, Color.CRIMSON);
            Circle pivot = new Circle(pivotX, pivotY, 5, Color.BLACK);

            Text hint = new Text(16, 24, "UP: faster   DOWN: slower   S: stop   R: resume");
            hint.setFill(Color.DARKSLATEGRAY);

            getChildren().addAll(swingArc, rod, bob, pivot, hint);
            update();

            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (lastNanos == 0) {
                        lastNanos = now;
                    }
                    double elapsed = (now - lastNanos) / 1_000_000_000.0;
                    lastNanos = now;
                    if (running) {
                        phase += speed * elapsed;
                        update();
                    }
                }
            };
        }

        void start() {
            timer.start();
        }

        void faster() {
            speed = Math.min(6.0, speed + 0.4);
        }

        void slower() {
            speed = Math.max(0.4, speed - 0.4);
        }

        void pause() {
            running = false;
        }

        void resume() {
            running = true;
        }

        private void update() {
            double angle = Math.toRadians(40) * Math.cos(phase);
            double bobX = pivotX + length * Math.sin(angle);
            double bobY = pivotY + length * Math.cos(angle);
            rod.setStartX(pivotX);
            rod.setStartY(pivotY);
            rod.setEndX(bobX);
            rod.setEndY(bobY);
            bob.setCenterX(bobX);
            bob.setCenterY(bobY);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
