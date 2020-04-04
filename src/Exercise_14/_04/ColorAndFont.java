package Exercise_14._04;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ColorAndFont extends Application { // github

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage){
        HBox pane = new HBox(  );
        pane.setAlignment( Pos.CENTER );

        Font font = Font.font( "Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 22 );

        for (int i = 0; i < 5; i++) {
            Text text = new Text( "Java" );
            text.setRotate( 90 );
            text.setFont( font );
            text.setFill( new Color( Math.random(),Math.random(),Math.random(),Math.random() ) );
            pane.getChildren().add( text );
        }

        Scene scene = new Scene( pane, 200, 100 );
        primaryStage.setTitle( "Exercise14_04" );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
