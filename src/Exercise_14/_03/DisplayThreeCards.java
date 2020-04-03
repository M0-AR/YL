package Exercise_14._03;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayThreeCards extends Application {

    public void start(Stage stage) {
        int[] n = new int[52];
        for (int i = 1; i <= 52; i++)
            n[i - 1] = i;
        shuffle( n );
        Image imageCard1 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[0] + ".png" );
        Image imageCard2 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[1] + ".png" );
        Image imageCard3 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[2] + ".png" );


        GridPane gridPane = new GridPane();
        gridPane.setHgap( 5 );
        gridPane.setVgap( 5 );

        gridPane.add( new ImageView( imageCard1 ), 0, 0 );
        gridPane.add( new ImageView( imageCard2 ), 1, 0 );
        gridPane.add( new ImageView( imageCard3 ), 2, 0 );

        Scene scene = new Scene( gridPane );
        stage.setScene( scene );
        stage.setTitle( "Exercise14_03" );
        stage.show();


    }

    public void shuffle(int[] n) {
        for (int i = 0; i < n.length; i++) {
            int random = (int) (Math.random() * 52);
            int temp = n[i];
            n[i] = n[random];
            n[random] = temp;
        }
    }
}
