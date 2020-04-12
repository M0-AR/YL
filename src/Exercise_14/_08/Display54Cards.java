package Exercise_14._08;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Display54Cards extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        int[] n = new int[54];
        for (int i = 1; i <= 54; i++)
            n[i - 1] = i;
        shuffle( n );


        GridPane pane = new GridPane();
        pane.setPadding( new Insets( 5, 5, 5, 5 ) );
        int count = 0;
        for (int row = 0; row < 6; row++)
            for (int coulmen = 0; coulmen < 9; coulmen++) {
                pane.add( new ImageView( new Image(
                        "file:/Users/mohammad/IdeaProjects/YL/src/image/card/"
                                                            + n[count] + ".png" ) ), coulmen,row );
                count++;
            }


        Scene scene = new Scene( pane );
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
