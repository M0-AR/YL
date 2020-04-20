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
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class PaintSmileyFace extends Application { // github


    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        // Create a stack pane
        StackPane stackPane = new StackPane(  );
        StackPane stackPane2 = new StackPane(  );

        // Create a pane
        Pane pane = new Pane(  );
        stackPane2.setPadding(new Insets(40,40,40,40)); // todo drive debugging and see for what ...


        // create a circle and place it in a stack pane
        Circle circle = new Circle(  );
        stackPane.getChildren().add(circle);

        // Create a Polygon and place it in a stack pane
//        Polygon polygon = getPolygon(circle);
//        stackPane.getChildren().add( polygon );


        // Ellipse for eyes
//        Ellipse ellipse1 = getEllipse(circle);
//        ellipse1.setCenterX( circle.getRadius() - circle.getRadius() / 2.5 );

//        Ellipse ellipse2 = getEllipse(circle);
//        ellipse2.setCenterX( circle.getRadius() + circle.getRadius() / 2.5 );


        // Circles to inside eyes
//        Circle circle1 = getCircle(ellipse1);
//        Circle circle2 = getCircle(ellipse2);



        // Arc for mouth
//        Arc arc = getArc(circle);


        // Place nodes in a pane
//        pane.getChildren().addAll( stackPane, ellipse1, ellipse2,
//                                            circle1, circle2, arc);

        // Place pane in a stack pane
        stackPane2.getChildren().add( pane );


        // Create a scene and place it in the stage
        Scene scene = new Scene(stackPane2);
        stage.setTitle( "Exercise_14_11" ); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}
