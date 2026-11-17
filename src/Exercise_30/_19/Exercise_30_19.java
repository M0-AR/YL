package Exercise_30._19;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class Exercise_30_19 extends Application {
    private static final int SIZE = 50;
    private final SortPane selectionPane = new SortPane("Selection sort");
    private final SortPane insertionPane = new SortPane("Insertion sort");
    private final SortPane bubblePane = new SortPane("Bubble sort");

    @Override
    public void start(Stage stage) {
        int[] template = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            template[i] = i + 1;
        }
        shuffle(template);
        selectionPane.setValues(template.clone());
        insertionPane.setValues(template.clone());
        bubblePane.setValues(template.clone());

        Button start = new Button("Start");
        start.setOnAction(event -> {
            selectionPane.startThread(() -> selectionSort(selectionPane));
            insertionPane.startThread(() -> insertionSort(insertionPane));
            bubblePane.startThread(() -> bubbleSort(bubblePane));
        });

        HBox panes = new HBox(10, selectionPane, insertionPane, bubblePane);
        BorderPane root = new BorderPane(panes);
        root.setBottom(start);
        stage.setTitle("Exercise 30.19: Sorting animation");
        stage.setScene(new Scene(root, 900, 420));
        stage.show();
    }

    private static void selectionSort(SortPane pane) {
        int[] values = pane.getValues();
        for (int i = 0; i < values.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[min]) {
                    min = j;
                }
            }
            int temp = values[i];
            values[i] = values[min];
            values[min] = temp;
            pane.show(i);
            sleep();
        }
    }

    private static void insertionSort(SortPane pane) {
        int[] values = pane.getValues();
        for (int i = 1; i < values.length; i++) {
            int value = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > value) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = value;
            pane.show(i);
            sleep();
        }
    }

    private static void bubbleSort(SortPane pane) {
        int[] values = pane.getValues();
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = 0; j < values.length - 1 - i; j++) {
                if (values[j] > values[j + 1]) {
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
            pane.show(values.length - 1 - i);
            sleep();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private static void shuffle(int[] values) {
        Random random = new Random();
        for (int i = values.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
    }

    private static class SortPane extends Pane {
        private int[] values;
        private final Text title;

        SortPane(String name) {
            setPrefSize(290, 360);
            title = new Text(10, 20, name);
            getChildren().add(title);
        }

        void setValues(int[] values) {
            this.values = values;
            draw(-1);
        }

        int[] getValues() {
            return values;
        }

        void startThread(Runnable task) {
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        }

        void show(int sortedIndex) {
            Platform.runLater(() -> draw(sortedIndex));
        }

        private void draw(int sortedIndex) {
            getChildren().removeIf(node -> node instanceof Rectangle);
            double width = 5;
            for (int i = 0; i < values.length; i++) {
                Rectangle bar = new Rectangle(10 + i * width, 350 - values[i] * 5,
                        width - 1, values[i] * 5);
                bar.setFill(i == sortedIndex ? Color.RED : Color.STEELBLUE);
                getChildren().add(bar);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
