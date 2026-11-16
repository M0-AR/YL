// Adapted from chill4Eng2Dev/IntroToJava (MIT License, Copyright (c) 2020 chill4Eng2Dev).
package Exercise_14._19;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_19 extends Application {
    @Override
    public void start(Stage stage) {
        final double centerX = 200;
        final double centerY = 100;
        final double scale = 50;

        Pane pane = new Pane();
        pane.setPadding(new Insets(20));

        Polyline sine = new Polyline();
        sine.setStroke(Color.RED);
        sine.setFill(null);

        Polyline cosine = new Polyline();
        cosine.setStroke(Color.BLUE);
        cosine.setFill(null);

        ObservableList<Double> sinePoints = sine.getPoints();
        ObservableList<Double> cosinePoints = cosine.getPoints();

        for (int x = -170; x <= 170; x++) {
            double angle = (x / 100.0) * 2 * Math.PI;
            sinePoints.addAll(x + centerX, centerY - scale * Math.sin(angle));
            cosinePoints.addAll(x + centerX, centerY - scale * Math.cos(angle));
        }

        Line xAxis = new Line(20, centerY, 380, centerY);
        Line yAxis = new Line(centerX, 20, centerX, 180);

        Text xLabel = new Text(385, centerY + 5, "X");
        Text yLabel = new Text(centerX + 5, 15, "Y");
        Text minusTwoPi = new Text(95, centerY + 20, "-2\u03c0");
        Text minusPi = new Text(145, centerY + 20, "-\u03c0");
        Text zero = new Text(195, centerY + 20, "0");
        Text pi = new Text(245, centerY + 20, "\u03c0");
        Text twoPi = new Text(295, centerY + 20, "2\u03c0");

        pane.getChildren().addAll(
                sine,
                cosine,
                xAxis,
                yAxis,
                xLabel,
                yLabel,
                minusTwoPi,
                minusPi,
                zero,
                pi,
                twoPi);

        Scene scene = new Scene(pane, 420, 220);
        stage.setTitle("Sine and Cosine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
