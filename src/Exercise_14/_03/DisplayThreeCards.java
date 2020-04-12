package Exercise_14._03;
/*********************************************************************************
 * (Display three cards) Write a program that displays three cards randomly       *
 * selected from a deck of 52, as shown in Figure 14.43c. The card image files    *
 * are named 1.png, 2.png, …, 52.png and stored in the image/card directory. All  *
 * three cards are distinct and selected randomly. Hint: You can select random    *
 * cards by storing the numbers 1–52 to an array list, perform a random shuffle   *
 * introduced in Section 11.12, and use the first three numbers in the array list *
 * as the file names for the image.                                               *
 *********************************************************************************/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class DisplayThreeCards extends Application {

    public void start(Stage stage) {

        /////////////////////////////////////////////
        // Method a_1:
        int[] n = new int[52];
        for (int i = 1; i <= 52; i++)
            n[i - 1] = i;
        shuffle( n );
        // Method a_2:
        ArrayList<Integer> cards = getCards();
        /////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////////////////////
        // Add nodes to pane
        // Method b_1:
        Image imageCard1 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[0] + ".png" );
        Image imageCard2 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[1] + ".png" );
        Image imageCard3 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[2] + ".png" );

        GridPane gridPane = new GridPane();
        gridPane.setHgap( 5 );
        gridPane.setVgap( 5 );

        gridPane.add( new ImageView( imageCard1 ), 0, 0 );
        gridPane.add( new ImageView( imageCard2 ), 1, 0 );
        gridPane.add( new ImageView( imageCard3 ), 2, 0 );

        // Method b_2:
        HBox pane = new HBox( 5 );
        pane.setPadding( new Insets(5,5,5,5) );
        for (int i = 0; i <3 ; i++)
            pane.getChildren().add( new ImageView( new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/card/" + n[i] + ".png") ) );
        //////////////////////////////////////////////////////////////////////////////////////////





//        Scene scene = new Scene( gridPane );
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

    public ArrayList<Integer> getCards(){
        ArrayList<Integer> cards = new ArrayList<>(  );
        for (int i = 1; i <= 52 ; i++)
            cards.add( i );
        java.util.Collections.shuffle( cards );
        return cards;
    }
}
