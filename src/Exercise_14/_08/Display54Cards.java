package Exercise_14._08;

/*************************************************************************************
 * (Display 54 cards) Expand Exercise 14.3 to display all 54 cards (including two     *
 * jokers), nine per row. The image files are jokers and are named 53.jpg and 54.jpg. *
 *************************************************************************************/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Display54Cards extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage stage) throws Exception {
        int[] n = new int[54];
        for (int i = 1; i <= 54; i++)
            n[i - 1] = i;
        shuffle( n );


        GridPane pane = new GridPane();
        pane.setPadding( new Insets( 5, 5, 5, 5 ) );
        int count = 0;
        for (int row = 0; row < 6; row++)
            for (int column = 0; column < 9; column++) {
                pane.add( new ImageView( new Image(
                        "file:/Users/mohammad/IdeaProjects/YL/src/image/card/"
                                                            + n[count] + ".png" ) ), column,row );
                count++;
            }


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        stage.setTitle("Exercise14_08"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }


    public void shuffle(int[] n) {
        for (int i = 0; i < n.length; i++) {
            int random = (int) (Math.random() * 52);
            int temp = n[i];
            n[i] = n[random];
            n[random] = temp;
        }
    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
