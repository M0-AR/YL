package Exercise_16._29;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class Exercise_16_29 extends Application {
    private static final String[] DAY_NAMES = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
    private final GridPane calendar = new GridPane();
    private final Label header = new Label();
    private YearMonth month = YearMonth.now();

    @Override
    public void start(Stage stage) {
        Button prior = new Button("Prior");
        Button next = new Button("Next");
        prior.setOnAction(event -> {
            month = month.minusMonths(1);
            render();
        });
        next.setOnAction(event -> {
            month = month.plusMonths(1);
            render();
        });

        calendar.setAlignment(Pos.CENTER);
        calendar.setHgap(4);
        calendar.setVgap(4);
        HBox controls = new HBox(12, prior, header, next);
        controls.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(calendar);
        root.setTop(controls);
        stage.setTitle("Exercise 16.29: Calendar");
        stage.setScene(new Scene(root, 480, 360));
        stage.show();
        render();
    }

    private void render() {
        calendar.getChildren().clear();
        header.setText(month.getMonth().getDisplayName(TextStyle.FULL, Locale.US)
                + " " + month.getYear());
        for (int column = 0; column < DAY_NAMES.length; column++) {
            Label label = new Label(DAY_NAMES[column]);
            label.setMinWidth(52);
            label.setAlignment(Pos.CENTER);
            calendar.add(label, column, 0);
        }

        LocalDate first = month.atDay(1);
        int startColumn = first.getDayOfWeek().getValue() % 7;
        LocalDate date = first.minusDays(startColumn);
        LocalDate today = LocalDate.now();
        for (int cell = 0; cell < 42; cell++) {
            Label label = new Label(String.valueOf(date.getDayOfMonth()));
            label.setMinSize(52, 32);
            label.setAlignment(Pos.CENTER_RIGHT);
            if (date.getMonthValue() != month.getMonthValue()) {
                label.setStyle("-fx-text-fill: gray;");
            }
            if (date.equals(today)) {
                label.setStyle("-fx-text-fill: white; -fx-background-color: steelblue;");
            }
            calendar.add(label, cell % 7, cell / 7 + 1);
            date = date.plusDays(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
