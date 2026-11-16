package Exercise_14._29;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exercise_14_29 extends Application {
    @Override
    public void start(Stage stage) {
        BeanMachinePane pane = new BeanMachinePane(8);

        stage.setTitle("Bean Machine");
        stage.setScene(new Scene(pane, 500, 430));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
