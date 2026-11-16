// Adapted from chill4Eng2Dev/IntroToJava (MIT License, Copyright (c) 2020 chill4Eng2Dev).
package Exercise_14._14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_14_14 extends Application {
    @Override
    public void start(Stage stage) {
        final double side = 200;

        Pane pane = new Pane();
        pane.setPadding(new Insets(20));

        Rectangle front = new Rectangle(side / 5, 0, side, side);
        front.widthProperty().bind(pane.widthProperty().subtract(side / 5));
        front.heightProperty().bind(pane.heightProperty().subtract(side / 2));
        front.setFill(Color.TRANSPARENT);
        front.setStroke(Color.BLACK);

        Rectangle back = new Rectangle(0, side / 3, side, side);
        back.widthProperty().bind(pane.widthProperty().subtract(side / 5));
        back.heightProperty().bind(pane.heightProperty().subtract(side / 2));
        back.setFill(Color.TRANSPARENT);
        back.setStroke(Color.BLACK);

        Line topLeft = new Line(back.getX(), back.getY(), front.getX(), front.getY());
        topLeft.setStroke(Color.BLACK);

        Line bottomLeft = new Line();
        bottomLeft.startXProperty().bind(back.xProperty());
        bottomLeft.startYProperty().bind(back.yProperty().add(back.heightProperty()));
        bottomLeft.endXProperty().bind(front.xProperty());
        bottomLeft.endYProperty().bind(front.yProperty().add(front.heightProperty()));
        bottomLeft.setStroke(Color.BLACK);

        Line topRight = new Line();
        topRight.startYProperty().bind(back.yProperty());
        topRight.endYProperty().bind(front.yProperty());
        topRight.startXProperty().bind(back.xProperty().add(back.widthProperty()));
        topRight.endXProperty().bind(front.xProperty().add(front.widthProperty()));
        topRight.setStroke(Color.BLACK);

        Line bottomRight = new Line();
        bottomRight.startXProperty().bind(back.xProperty().add(back.widthProperty()));
        bottomRight.startYProperty().bind(back.yProperty().add(back.heightProperty()));
        bottomRight.endXProperty().bind(front.xProperty().add(front.widthProperty()));
        bottomRight.endYProperty().bind(front.yProperty().add(front.heightProperty()));
        bottomRight.setStroke(Color.BLACK);

        pane.getChildren().addAll(back, front, topLeft, bottomLeft, topRight, bottomRight);

        StackPane root = new StackPane(pane);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, side * 3, side * 2);
        stage.setTitle("Rectanguloid");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
