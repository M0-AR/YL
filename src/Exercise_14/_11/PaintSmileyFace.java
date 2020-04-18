package Exercise_14._11;
/*********************************************************************************
 * (Paint a smiley face) Write a program that paints a smiley face, as shown in   *
 * Figure 14.46a.                                                                 *
 *********************************************************************************/
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class PaintSmileyFace extends Application { // github


    @Override
    public void start(Stage stage) {
        Pane pane = new Pane(  );


        Circle circle = new Circle(  );

        // Circles to inside eyes
        Circle circle1 = new Circle(  );
        Circle circle2 = new Circle(  );

        // Ellipse for eyes
        Ellipse ellipse1 = new Ellipse(  );
        Ellipse ellipse2 = new Ellipse(  );

        // Arc for mouth
        Arc arc = new Arc(  );
    }
}
