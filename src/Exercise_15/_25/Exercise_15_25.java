package Exercise_15._25;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_25 extends Application {
    private static final double WIDTH = 600;
    private static final double HEIGHT = 300;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Line xAxis = new Line(30, HEIGHT / 2, WIDTH - 30, HEIGHT / 2);
        Line yAxis = new Line(WIDTH / 2, 30, WIDTH / 2, HEIGHT - 30);
        xAxis.setStroke(Color.GRAY);
        yAxis.setStroke(Color.GRAY);

        Polyline curve = new Polyline();
        curve.setFill(Color.TRANSPARENT);
        curve.setStroke(Color.BLUE);
        curve.setStrokeWidth(2);
        for (double x = 40; x <= WIDTH - 40; x += 2) {
            double y = HEIGHT / 2 - 70 * Math.sin((x - 40) / (WIDTH - 80) * 4 * Math.PI);
            curve.getPoints().addAll(x, y);
        }

        Circle ball = new Circle(curve.getPoints().get(0), curve.getPoints().get(1), 10, Color.CRIMSON);
        PathTransition transition = new PathTransition(Duration.seconds(4), curve, ball);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.play();

        Text hint = new Text(20, 24, "Left-click: resume   Right-click: pause");
        hint.setFill(Color.DARKSLATEGRAY);

        pane.getChildren().addAll(xAxis, yAxis, curve, ball, hint);
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                transition.play();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                transition.pause();
            }
        });

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Exercise_15_25");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
