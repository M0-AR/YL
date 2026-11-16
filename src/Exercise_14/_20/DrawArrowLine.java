package Exercise_14._20;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawArrowLine extends Application {
    public static void drawArrowLine(double startX, double startY,
                                     double endX, double endY, Pane pane) {
        Line shaft = new Line(startX, startY, endX, endY);

        double angle = Math.atan2(endY - startY, endX - startX);
        double headLength = 16;
        double spread = Math.toRadians(28);

        double leftX = endX - headLength * Math.cos(angle - spread);
        double leftY = endY - headLength * Math.sin(angle - spread);
        double rightX = endX - headLength * Math.cos(angle + spread);
        double rightY = endY - headLength * Math.sin(angle + spread);

        Line leftHead = new Line(endX, endY, leftX, leftY);
        Line rightHead = new Line(endX, endY, rightX, rightY);

        pane.getChildren().addAll(shaft, leftHead, rightHead);
    }

    @Override
    public void start(Stage stage) {
        double width = 440;
        double height = 440;
        double margin = 50;

        Pane pane = new Pane();
        double startX = ThreadLocalRandom.current().nextDouble(margin, width - margin);
        double startY = ThreadLocalRandom.current().nextDouble(margin, height - margin);
        double endX = ThreadLocalRandom.current().nextDouble(margin, width - margin);
        double endY = ThreadLocalRandom.current().nextDouble(margin, height - margin);

        drawArrowLine(startX, startY, endX, endY, pane);

        stage.setTitle("Random Arrow Line");
        stage.setScene(new Scene(pane, width, height));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
