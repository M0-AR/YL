package Exercise_14._11;
/*********************************************************************************
 * (Paint a smiley face) Write a program that paints a smiley face, as shown in   *
 * Figure 14.46a.                                                                 *
 *********************************************************************************/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class PaintSmileyFace extends Application {


    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        // Create a stack pane
        StackPane stackPane = new StackPane(  );
        StackPane stackPane2 = new StackPane(  );

        // Create a pane
        Pane pane = new Pane(  );
        stackPane2.setPadding(new Insets(40,40,40,40)); // todo drive debugging and see for what ...


        // create a circle and place it in a stack pane
        Circle circle = getCircle();
        stackPane.getChildren().add(circle);

        // Create a Polygon and place it in a stack pane
        Polygon polygon = getPolygon(circle);
        stackPane.getChildren().add( polygon );


        // Ellipse for eyes
        Ellipse ellipse1 = getEllipse(circle);
        ellipse1.setCenterX( circle.getRadius() - circle.getRadius() / 2.5 );

        Ellipse ellipse2 = getEllipse(circle);
        ellipse2.setCenterX( circle.getRadius() + circle.getRadius() / 2.5 );


        // Circles to inside eyes
        Circle circle1 = getCircle(ellipse1);
        Circle circle2 = getCircle(ellipse2);



        // Arc for mouth
        Arc arc = getArc(circle);


        // Place nodes in a pane
        pane.getChildren().addAll( stackPane, ellipse1, ellipse2,
                                            circle1, circle2, arc);

        // Place pane in a stack pane
        stackPane2.getChildren().add( pane );


        // Create a scene and place it in the stage
        Scene scene = new Scene(stackPane2);
        stage.setTitle( "Exercise_14_11" ); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    /** Return a Arc of specified properties */
    private Arc getArc(Circle c){
        Arc a = new Arc(c.getRadius(), c.getRadius() * 1.30,
                c.getRadius() / 2, c.getRadius() / 4, 0, -180);
        a.setType( ArcType.OPEN);
        a.setFill(Color.WHITE);
        a.setStroke(Color.BLACK);
        return a;
    }


    /** Return a circle of specified properties */
    private Circle getCircle(Ellipse e){
        Circle c = new Circle(e.getCenterX(), e.getCenterY(),
                e.getRadiusY() - (e.getRadiusX() - e.getRadiusY()));
        return c;
    }

    /** Return an Ellipse of specified properties */
    private Ellipse getEllipse(Circle c){
        Ellipse e = new Ellipse();
        e.setCenterY(c.getRadius() - c.getRadius() / 3);
        e.setRadiusX(c.getRadius() / 4);
        e.setRadiusY( c.getRadius() / 3 - 20 );
        e.setStroke( Color.BLACK );
        e.setFill(Color.WHITE);
        return e;
    }

    /** Return a Polygon of specified properties */
    private Polygon getPolygon(Circle c){
        double length = c.getRadius() / 4;
        Polygon p = new Polygon(c.getCenterX(), c.getCenterY() - length,
                c.getCenterX() - length, c.getCenterY() + length, c.getCenterX() + length,
                c.getCenterY() + length);
        p.setFill( Color.WHITE );
        p.setStroke( Color.BLACK );
        return p;
    }

    /** Returns a circle of specified properties*/
    private Circle getCircle(){
        Circle c = new Circle(  );
        c.setRadius(150);
        c.setStroke( Color.BLACK );
        c.setFill(Color.WHITE);
        return c;
    }


}
