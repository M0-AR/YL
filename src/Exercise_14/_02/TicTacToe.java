package Exercise_14._02;

/**********************************************************************************
 * (Tic-tac-toe board) Write a program that displays a tic-tac-toe board, as shown *
 * in Figure 14.43b. A cell may be X, O, or empty. What to display at each cell is *
 * randomly decided. The X and O are images in the files x.gif and o.gif.          *
 ***********************************************************************************/
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TicTacToe extends Application{

    public void start(Stage stage){
        Image imageX = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/x.gif" );
        Image imageO = new Image( "file:/Users/mohammad/IdeaProjects/YL/src/image/o.gif" );

        GridPane pane = new GridPane();
        pane.setAlignment( Pos.CENTER );
        pane.setHgap( 5 );
        pane.setVgap( 5 );

        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                int status = (int) (Math.random() * 3);
                if (status == 0)
                    pane.add( new ImageView( imageO ),j,i );
                else {
                    pane.add( new ImageView( imageX ),j,i );
                }
            }
        }

        // Create a scene and place it in the stage
        Scene scene = new Scene( pane );
        stage.setScene( scene );
        stage.setTitle( "Tic Tac Toe" );
        stage.show();

    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch( args );
    }
}
