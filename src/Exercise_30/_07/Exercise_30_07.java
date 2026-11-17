package Exercise_30._07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;

public class Exercise_30_07 extends Application {
    @Override
    public void start(Stage stage) {
        ClockPane clock = new ClockPane();
        stage.setTitle("Exercise 30.07: Clock with a thread");
        stage.setScene(new Scene(clock, 420, 440));
        stage.show();
        clock.start();
    }

    private static class ClockPane extends Pane {
        private final Line hourHand = new Line();
        private final Line minuteHand = new Line();
        private final Line secondHand = new Line();
        private final Text timeText = new Text();
        private volatile boolean running = true;

        ClockPane() {
            Circle face = new Circle(210, 200, 160);
            face.setStyle("-fx-fill: white; -fx-stroke: black;");
            for (int i = 0; i < 12; i++) {
                double angle = Math.toRadians(i * 30 - 90);
                Line tick = new Line(
                        210 + Math.cos(angle) * 145, 200 + Math.sin(angle) * 145,
                        210 + Math.cos(angle) * 160, 200 + Math.sin(angle) * 160);
                getChildren().add(tick);
            }
            secondHand.setStyle("-fx-stroke: red;");
            getChildren().addAll(face, hourHand, minuteHand, secondHand, timeText);
            setClock();
        }

        void start() {
            Thread thread = new Thread(() -> {
                while (running) {
                    Platform.runLater(this::setClock);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        private void setClock() {
            LocalTime now = LocalTime.now();
            double secondAngle = now.getSecond() * 6 - 90;
            double minuteAngle = now.getMinute() * 6 + now.getSecond() * 0.1 - 90;
            double hourAngle = (now.getHour() % 12) * 30 + now.getMinute() * 0.5 - 90;
            setHand(secondHand, secondAngle, 150);
            setHand(minuteHand, minuteAngle, 120);
            setHand(hourHand, hourAngle, 90);
            timeText.setText(String.format("%02d:%02d:%02d",
                    now.getHour(), now.getMinute(), now.getSecond()));
            timeText.setX(180);
            timeText.setY(380);
        }

        private void setHand(Line hand, double angle, double length) {
            double radians = Math.toRadians(angle);
            hand.setStartX(210);
            hand.setStartY(200);
            hand.setEndX(210 + Math.cos(radians) * length);
            hand.setEndY(200 + Math.sin(radians) * length);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
