package Exercise_14._05;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class CharactersAroundCircle extends Application {


    @Override
    public void start(Stage stage) {

        // Create a pane
        Pane pane = new Pane(  );
        pane.setPrefSize( 500,500 );


        String s = "WELCOME TO JAVA";
        double rotation = 0;
        double offset = pane.getPrefWidth() / 2;
        double radius = 100;
        double x,y;

        Font font =  Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 35 );

        for (int i = 0; i < s.length() ; i++) {
            x = offset + Math.cos( Math.toRadians( rotation ) ) * radius;
            y = offset + Math.sin( Math.toRadians( rotation ) ) * radius;
            Text text = new Text( x,y,s.charAt( i ) + "");
            text.setFont( font );
            text.setRotate( rotation );
            pane.getChildren().add( text);
            rotation += 22.5;
        }

        // Create the scene
        Scene scene = new Scene( pane );
        stage.setScene( scene );
        stage.setTitle( "Characters Around Circle" );
        stage.show();

    }
}
