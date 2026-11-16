package Exercise_14._27;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DetailedClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private double w = 300;
    private double h = 300;

    public DetailedClockPane() {
        setCurrentTime();
    }

    public DetailedClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        paintClock();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
        paintClock();
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        paintClock();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        paintClock();
    }

    private void paintClock() {
        getChildren().clear();

        double radius = Math.min(w, h) * 0.42;
        double centerX = w / 2;
        double centerY = h / 2;

        Circle face = new Circle(centerX, centerY, radius);
        face.setFill(Color.WHITE);
        face.setStroke(Color.BLACK);
        face.setStrokeWidth(2);
        getChildren().add(face);

        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(i * 6);
            double outer = radius - 5;
            double inner = radius - (i % 5 == 0 ? 18 : 10);

            Line tick = new Line(
                    centerX + outer * Math.sin(angle),
                    centerY - outer * Math.cos(angle),
                    centerX + inner * Math.sin(angle),
                    centerY - inner * Math.cos(angle));
            tick.setStroke(i % 5 == 0 ? Color.BLACK : Color.GRAY);
            tick.setStrokeWidth(i % 5 == 0 ? 2 : 1);
            getChildren().add(tick);
        }

        double labelRadius = radius - 34;
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30);
            Text label = new Text(Integer.toString(i));
            label.setFont(Font.font(13));
            label.setX(centerX + labelRadius * Math.sin(angle) - 5);
            label.setY(centerY - labelRadius * Math.cos(angle) + 5);
            getChildren().add(label);
        }

        double secondAngle = Math.toRadians(second * 6);
        Line secondHand = new Line(
                centerX, centerY,
                centerX + radius * 0.82 * Math.sin(secondAngle),
                centerY - radius * 0.82 * Math.cos(secondAngle));
        secondHand.setStroke(Color.RED);

        double minuteAngle = Math.toRadians((minute + second / 60.0) * 6);
        Line minuteHand = new Line(
                centerX, centerY,
                centerX + radius * 0.65 * Math.sin(minuteAngle),
                centerY - radius * 0.65 * Math.cos(minuteAngle));
        minuteHand.setStroke(Color.BLUE);
        minuteHand.setStrokeWidth(2);

        double hourAngle = Math.toRadians(
                (hour % 12 + minute / 60.0 + second / 3600.0) * 30);
        Line hourHand = new Line(
                centerX, centerY,
                centerX + radius * 0.5 * Math.sin(hourAngle),
                centerY - radius * 0.5 * Math.cos(hourAngle));
        hourHand.setStroke(Color.BLACK);
        hourHand.setStrokeWidth(3);

        Circle center = new Circle(centerX, centerY, 4, Color.BLACK);

        getChildren().addAll(hourHand, minuteHand, secondHand, center);
    }
}
