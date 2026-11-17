package Exercise_16._24;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_16_24 extends Application {
    private static final String DEFAULT_URL =
            "http://cs.armstrong.edu/liang/common/media/Video.mp4";
    private final TextField urlField = new TextField(DEFAULT_URL);
    private final Slider slider = new Slider();
    private final Label timeLabel = new Label("--:-- / --:--");
    private final MediaView mediaView = new MediaView();
    private MediaPlayer player;
    private boolean updatingSlider;

    @Override
    public void start(Stage stage) {
        Button play = new Button("Play");
        Button pause = new Button("Pause");
        Button stop = new Button("Stop");
        play.setOnAction(event -> {
            if (player == null) {
                loadMedia();
            }
            if (player != null) {
                player.play();
            }
        });
        pause.setOnAction(event -> {
            if (player != null) {
                player.pause();
            }
        });
        stop.setOnAction(event -> {
            if (player != null) {
                player.stop();
            }
        });

        HBox top = new HBox(6, new Label("URL:"), urlField, play, pause, stop);
        top.setAlignment(Pos.CENTER);
        slider.setDisable(true);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!updatingSlider && player != null) {
                player.seek(Duration.seconds(newValue.doubleValue()));
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(new StackPane(mediaView));
        root.setBottom(new VBox(4, slider, timeLabel));
        stage.setTitle("Exercise 16.24: Media with time slider");
        stage.setScene(new Scene(root, 640, 420));
        stage.show();
    }

    private void loadMedia() {
        try {
            if (player != null) {
                player.dispose();
            }
            player = new MediaPlayer(new Media(urlField.getText().trim()));
            mediaView.setMediaPlayer(player);
            player.setOnReady(() -> {
                slider.setMax(player.getTotalDuration().toSeconds());
                slider.setDisable(false);
                timeLabel.setText(format(Duration.ZERO) + " / "
                        + format(player.getTotalDuration()));
            });
            player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                updatingSlider = true;
                slider.setValue(newValue.toSeconds());
                timeLabel.setText(format(newValue) + " / "
                        + format(player.getTotalDuration()));
                updatingSlider = false;
            });
        } catch (RuntimeException exception) {
            timeLabel.setText("Cannot load media: " + exception.getMessage());
        }
    }

    private String format(Duration duration) {
        int totalSeconds = (int) duration.toSeconds();
        return String.format("%d:%02d", totalSeconds / 60, totalSeconds % 60);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
