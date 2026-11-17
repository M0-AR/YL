package Exercise_30._03;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_30_03 extends Application {
    @Override
    public void start(Stage stage) {
        FlagPane pane = new FlagPane();
        stage.setTitle("Exercise 30.03: Raise flag with a thread");
        stage.setScene(new Scene(pane, 400, 400));
        stage.show();
        pane.raiseFlag();
    }

    private static class FlagPane extends Pane {
        private final Rectangle flag = new Rectangle(150, 330, 120, 70);
        private final Line pole = new Line(150, 30, 150, 350);

        FlagPane() {
            flag.setFill(Color.RED);
            getChildren().addAll(pole, flag);
        }

        void raiseFlag() {
            Thread thread = new Thread(() -> {
                while (flag.getY() > 30) {
                    Platform.runLater(() -> flag.setY(flag.getY() - 1));
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
