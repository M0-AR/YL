package Exercise_30._02;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_30_02 extends Application {
    private final CarPane firstCar = new CarPane();
    private final CarPane secondCar = new CarPane();

    @Override
    public void start(Stage stage) {
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        start.setOnAction(event -> {
            firstCar.start();
            secondCar.start();
        });
        stop.setOnAction(event -> {
            firstCar.stop();
            secondCar.stop();
        });

        BorderPane root = new BorderPane();
        root.setCenter(new Pane(firstCar, secondCar));
        root.setBottom(new HBox(8, start, stop));
        stage.setTitle("Exercise 30.02: Racing cars with threads");
        stage.setScene(new Scene(root, 520, 260));
        stage.show();
    }

    private static class CarPane extends Pane {
        private final Rectangle body = new Rectangle(0, 100, 50, 10);
        private final Polygon roof = new Polygon(10, 100, 20, 90, 30, 90, 40, 100);
        private final Circle frontWheel = new Circle(15, 112, 5);
        private final Circle rearWheel = new Circle(35, 112, 5);
        private volatile boolean running;
        private double x;

        CarPane() {
            body.setFill(Color.BLUE);
            roof.setFill(Color.BLUE);
            getChildren().addAll(body, roof, frontWheel, rearWheel);
        }

        void start() {
            if (running) {
                return;
            }
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    Platform.runLater(this::moveCar);
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

        private void moveCar() {
            x = x >= getWidth() ? 0 : x + 2;
            body.setX(x);
            body.setY(100);
            roof.getPoints().setAll(
                    x + 10, 100.0, x + 20, 90.0, x + 30, 90.0, x + 40, 100.0);
            frontWheel.setCenterX(x + 15);
            rearWheel.setCenterX(x + 35);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
