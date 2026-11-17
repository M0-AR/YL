package Exercise_25._11;

import Exercise_25._common.BST;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Exercise_25_11 extends Application {
    @Override
    public void start(Stage stage) {
        BST<Integer> tree = new BST<>(new Integer[] {50, 30, 70, 20, 40, 60, 80});
        BSTView view = new BSTView();
        view.setTree(tree);

        ScrollPane scroll = new ScrollPane(view);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        stage.setScene(new Scene(scroll, 900, 600));
        stage.setTitle("Exercise 25.11 - Display Tree");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
