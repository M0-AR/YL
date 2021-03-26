package Exercise_20._02;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;

public class GraphicalUserInterface extends Application {
   private LinkedList<Integer> list = new LinkedList<>();
   private TextField tfNumber = new TextField();
   private TextArea taNumbers = new TextArea();
   private Button btSort = new Button("Sort");
   private Button btShuffle = new Button("Shuffle");
   private Button btReverse = new Button("Reverse");


   @Override // Override the start method in the Application class
   public void start(Stage primaryStage) throws Exception {
      HBox hBox = new HBox(10);
      hBox.getChildren().addAll(new Label("Enter a number: "), tfNumber);
      hBox.setAlignment( Pos.CENTER);

      BorderPane borderPane = new BorderPane();
      borderPane.setTop(hBox);
      borderPane.setCenter(new ScrollPane(taNumbers));

      HBox hBoxForButtons = new HBox(10);
      hBoxForButtons.getChildren().addAll(btSort, btShuffle, btReverse);
      hBoxForButtons.setAlignment(Pos.CENTER);
      borderPane.setBottom(hBoxForButtons);

      // Create a scene and place it in the stage
      Scene scene = new Scene(borderPane, 400, 210);
      primaryStage.setTitle("Exercise20_02"); // Set the stage title
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage

      tfNumber.setOnAction(e -> {
         if (!list.contains(Integer.valueOf( tfNumber.getText()))) {
            taNumbers.appendText(tfNumber.getText() + " ");
            list.add(Integer.valueOf( tfNumber.getText()));
         }
      });

      btSort.setOnAction(e -> {
         Collections.sort(list);
         display();
      });

      btShuffle.setOnAction(e -> {
         Collections.shuffle(list);
         display();
      });

      btReverse.setOnAction(e -> {
         Collections.reverse(list);
         display();
      });
   }

   private void display() {
      taNumbers.setText(null);
      for (Integer i: list) {
         taNumbers.appendText(i + " ");
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
