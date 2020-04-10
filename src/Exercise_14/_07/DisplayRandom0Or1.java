package Exercise_14._07;
import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;



public class DisplayRandom0Or1 extends Application{

    @Override
    public void start(Stage stage) { // con github
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                Text text = new Text(  );
                text.setText( String.valueOf( (int) Math.random()*2 ) );
                gridPane.add( text, j,i );
            }
        }




        Scene scene = new Scene( gridPane );
        stage.setScene( scene );
        stage.setTitle( "Exercise14_07" );
    }
}
