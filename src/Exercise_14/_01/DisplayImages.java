package Exercise_14._01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.Element;

import javafx.scene.image.*;

public class DisplayImages extends Application { // check github

    @Override
    public void start(Stage stage) throws Exception {
        // Create the pane to hold the image view
        GridPane pane = new GridPane();

        Image image0 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/Exercise_14/_01/image/uk.gif" );
        ImageView imageView0 = new ImageView( image0 );
        imageView0.setFitWidth( 200 );
        imageView0.setFitHeight( 200 );
        pane.add( imageView0, 0, 0 );


        Image image1 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/Exercise_14/_01/image/ca.gif" );
        ImageView imageView1 = new ImageView( image1 );
        imageView1.setFitWidth( 200 );
        imageView1.setFitHeight( 200 );

        pane.add( imageView1, 0, 1 );


        Image image2 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/Exercise_14/_01/image/china.gif" );
        ImageView imageView2 = new ImageView( image2 );
        imageView2.setFitWidth( 200 );
        imageView2.setFitHeight( 200 );
        pane.add( imageView2, 1, 0 );

        Image image3 = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/Exercise_14/_01/image/us.gif" );
        ImageView imageView3 = new ImageView( image3 );
        imageView3.setFitWidth( 200 );
        imageView3.setFitHeight( 200 );
        pane.add( imageView3, 1, 1 );

        // Create a scene and place it in a stage
        Scene scene = new Scene( pane );
        stage.setTitle( "ShowImages" ); // Set the stage title
        stage.setScene( scene ); // Place the scene in the stage
        stage.show(); // Display the stage

    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch( args );
    }


}
