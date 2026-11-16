package Exercise_15._23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_23 extends Application {
    private final StackPane root = new StackPane();
    private final Polygon sign = new Polygon();
    private final Text label = new Text("STOP");

    @Override
    public void start(Stage primaryStage) {
        sign.setFill(Color.RED);
        sign.setStroke(Color.DARKRED);
        sign.setStrokeWidth(2);
        label.setFill(Color.WHITE);
        root.getChildren().addAll(sign, label);

        Scene scene = new Scene(root, 300, 300);
        scene.widthProperty().addListener((observable, oldValue, newValue) ->
                draw(newValue.doubleValue(), scene.getHeight()));
        scene.heightProperty().addListener((observable, oldValue, newValue) ->
                draw(scene.getWidth(), newValue.doubleValue()));

        draw(300, 300);
        primaryStage.setTitle("Exercise_15_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void draw(double width, double height) {
        double radius = Math.min(width, height) * 0.42;
        sign.getPoints().clear();
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(22.5 + i * 45);
            sign.getPoints().addAll(radius * Math.cos(angle), radius * Math.sin(angle));
        }
        label.setFont(Font.font("Arial", FontWeight.BOLD, Math.max(12, Math.min(width, height) * 0.20)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
