package Exercise_14._06;
/*********************************************************************************
 * (Game: display a checkerboard) Write a program that displays a checkerboard in *
 * which each white and black cell is a Rectangle with a fill color black or      *
 * white, as shown in Figure 14.44c.                                              *
 *********************************************************************************/
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class DisplayCheckerBoard extends Application { // github and y

    @Override
    public void start(Stage stage)  {
        // Method 1:
        final int WIDTH = 200;
        final int HEIGHT = 200;
        Pane pane = new Pane();
        for (int i = 0; i < 8; i++) {
            boolean isWhite = i % 2 == 0;

            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle( i * WIDTH / 8,
                        j * HEIGHT / 8, WIDTH / 8, HEIGHT / 8 );

                rectangle.setStroke( Color.BLACK );

                if (isWhite)
                        rectangle.setFill( Color.WHITE );
                 else
                        rectangle.setFill( Color.BLACK );

                isWhite = !isWhite;

                pane.getChildren().add( rectangle );

            }
        }


        // Method 2:
        // Create a GridPane
        GridPane gridPane = new GridPane();
        // Create 64 rectangles and add to pane
        int count = 0;
        double s = 100; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                if (count % 2 == 0)
                    r.setFill(Color.WHITE);
                gridPane.add(r, j, i);
                count++;
            }
        }

        Scene scene = new Scene( pane );
        stage.setScene( scene );
        stage.setTitle( "Exercise14_07" );
        stage.show();
    }
}
