package Exercise_14._10;
/*********************************************************************************
 * (Display a cylinder) Write a program that draws a cylinder, as shown in        *
 * Figure 14.45b. You can use the following method to set the dashed stroke for   *
 * an arc:                                                                        *
 *                                                                                *
 *     arc.getStrokeDashArray().addAll(6.0, 21.0);                                *
 *********************************************************************************/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DisplayCylinder extends Application { // github


    @Override // Override start method from Application
    public void start(Stage stage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(new MyCylinder(), 200, 200);
        stage.setTitle("Exercise14_10"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

class MyCylinder extends Pane{
    public void paint(){
        double centerX = getWidth() / 2;
        double radiusX = getWidth() * 0.8 / 2;

        // To add the top ellipse
        Ellipse ellipse = new Ellipse( centerX, 40, radiusX, 20 );
        ellipse.setFill( Color.WHITE );
        ellipse.setStroke( Color.BLACK );

        // To add the below halve of ellipse (dashing line)
        Arc arc1 = new Arc(centerX, 140, radiusX, 20, 0, 180);
        arc1.setFill(Color.WHITE);
        arc1.setStroke(Color.BLACK);
        arc1.getStrokeDashArray().addAll( 6.0, 21.0 );

        // To add the below halve of ellipse
        Arc arc2 = new Arc( centerX, 140, radiusX, 20, 180, 180 );
        arc2.setFill(Color.WHITE);
        arc2.setStroke(Color.BLACK);

        getChildren().clear();
        getChildren().addAll( ellipse, arc1, arc2,
                // To add The side line to cylinder
                new Line(centerX - radiusX, 40, centerX - radiusX, 140),
                new Line(centerX + radiusX, 40, centerX + radiusX, 140));
    }

    @Override
    protected void setWidth(double value) {
        super.setWidth( value );
        paint();
    }

    @Override
    protected void setHeight(double value) {
        super.setHeight( value );
        paint();
    }
}