package BookExercise._14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class _01MyJavaFx extends Application {

//    @Override
//    public void start(Stage stage) throws Exception {
//        Button btnOK = new Button( "OK" );
//        Scene scene = new Scene( btnOK, 200, 250 );
//        stage.setTitle( "MyJavaFx" );
//        stage.setScene( scene );
//        stage.show();
//
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene( new Button( "OK" ), 400, 250 );
        stage.setTitle( "MyJavaFx" );
        stage.setScene( scene );
        stage.show();

        Stage stage1 = new Stage( );
        stage1.setTitle( "Second Stage" );
        stage1.setScene( new Scene( new Button( "New Stage" ) , 200, 250)  );
        stage1.show();
    }


    public static void main(String[] args) {
        Application.launch( args );
    }
}
