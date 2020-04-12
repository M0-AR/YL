package Exercise_14._07;
/***************************************************************************
 * (Display random 0 or 1) Write a program that displays a 10-by-10 square  *
 * matrix, as shown in Figure 14.45a. Each element in the matrix is 0 or 1, *
 * randomly generated. Display each number centered in a text field. Use    *
 * TextField’s setText method to set value 0 or 1 as a string.              *
 ***************************************************************************/
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.scene.control.TextField;


public class DisplayRandom0Or1 extends Application{

    @Override // Override the start method in the Application
    public void start(Stage stage) {
        // Create a GridPane and set its properties
        GridPane gridPane = new GridPane();
        gridPane.setPadding( new Insets( 5,5,5,5 ) );
        gridPane.setHgap( 5 );
        gridPane.setVgap( 5 );


        // Place text fields containing a centered
        // Randomly generated string of 0 or 1 int the pane
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                TextField text = new TextField(  );
                text.setPrefColumnCount( 1 );
                text.setText( String.valueOf( (int) (Math.random()*2) ) );
                gridPane.add( text, i,j );
                gridPane.setHalignment( text, HPos.CENTER );
                gridPane.setValignment( text, VPos.CENTER );
            }
        }



        // Create a scene and plane it in the stage
        Scene scene = new Scene( gridPane );
        stage.setTitle( "Exercise14_07" ); // Set the stage title
        stage.setScene( scene ); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}
