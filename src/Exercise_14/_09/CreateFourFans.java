package Exercise_14._09;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateFourFans extends Application { // github

    @Override // Override the start method in the Application class
    public void start(Stage stage) throws Exception {
        // Create a GridPane and set its properties
        GridPane pane = new GridPane();

        for (int i = 0; i < 2 ; i++) {
            for (int j = 0; j < 2 ; j++) {
                pane.add( new ImageView( new Image( "" ) ), j, i);
            }
        }


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        stage.setTitle("Exercise14_08"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}
