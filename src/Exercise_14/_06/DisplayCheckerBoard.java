package Exercise_14._06;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class DisplayCheckerBoard extends Application{ // github and y

    @Override
    public void start(Stage stage) throws Exception {
        final int  WIDTH = 200;
        final int  HEIGHT = 200;

        Pane pane = new Pane();


        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                Rectangle rectangle = new Rectangle(i * WIDTH / 8,
                        j * HEIGHT / 8, WIDTH / 8, HEIGHT / 8);

                rectangle.setStroke(Color.BLACK);

                if (i % 2 == 0 ){
                    if (j % 2 == 0)
                        rectangle.setFill( Color.WHITE );
                    else
                        rectangle.setFill( Color.BLACK );
                }else {
                    if (j % 2 == 1)
                        rectangle.setFill( Color.WHITE );
                    else
                        rectangle.setFill( Color.BLACK );
                }



                pane.getChildren().add( rectangle );

            }
        }



        Scene scene = new Scene( pane );
        stage.setScene( scene );
        stage.setTitle( "Exercise14_07" );
        stage.show();
    }
}
