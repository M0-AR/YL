package Exercise_14._27;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exercise_14_27 extends Application {
    @Override
    public void start(Stage stage) {
        DetailedClockPane clock = new DetailedClockPane(4, 20, 45);

        stage.setTitle("Detailed Clock");
        stage.setScene(new Scene(clock, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
