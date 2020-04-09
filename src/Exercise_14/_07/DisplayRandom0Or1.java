package Exercise_14._07;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class DisplayRandom0Or1 extends Application{

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth( 100 );
        gridPane.setPrefHeight( 100 );




        Scene scene = new Scene( gridPane );
        stage.setScene( scene );
        stage.setTitle( "Exercise14_07" );
    }
}
