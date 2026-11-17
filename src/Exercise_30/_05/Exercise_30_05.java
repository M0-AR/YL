package Exercise_30._05;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise_30_05 extends Application {
    @Override
    public void start(Stage stage) {
        FanPane fan = new FanPane();
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        start.setOnAction(event -> fan.start());
        stop.setOnAction(event -> fan.stop());

        BorderPane root = new BorderPane(fan);
        root.setBottom(new HBox(8, start, stop));
        stage.setTitle("Exercise 30.05: Running fan with a thread");
        stage.setScene(new Scene(root, 400, 440));
        stage.show();
        fan.start();
    }

    private static class FanPane extends Pane {
        private final Arc[] blades = new Arc[4];
        private volatile boolean running;
        private double angle;

        FanPane() {
            Circle hub = new Circle(200, 200, 12);
            getChildren().add(hub);
            for (int i = 0; i < blades.length; i++) {
                blades[i] = new Arc(200, 200, 100, 100, i * 90, 30);
                blades[i].setType(ArcType.ROUND);
                getChildren().add(blades[i]);
            }
        }

        void start() {
            if (running) {
                return;
            }
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    Platform.runLater(() -> {
                        angle += 5;
                        for (int i = 0; i < blades.length; i++) {
                            blades[i].setStartAngle(angle + i * 90);
                        }
                    });
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
