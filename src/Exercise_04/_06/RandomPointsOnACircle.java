package Exercise_04._06;

import java.util.Random;

public class RandomPointsOnACircle { // git
    static final int RADIUS = 40;
    public static void main(String[] args) {
        System.out.println("Three random points are ");
        double angle = Math.random() * 360;
        double x = RADIUS * Math.cos( angle * Math.PI/ 180 );
        double y = RADIUS * Math.sin( angle * Math.PI/ 180 );
        System.out.println("(" + x + ", " + y + ")");

        angle = Math.random() * 360;
        x = RADIUS * Math.cos( angle * Math.PI/ 180 );
        y = RADIUS * Math.sin( angle * Math.PI/ 180 );
        System.out.println("(" + x + ", " + y + ")");

        angle = Math.random() * 360;
        x = RADIUS * Math.cos( angle * Math.PI/ 180 );
        y = RADIUS * Math.sin( angle * Math.PI/ 180 );
        System.out.println("(" + x + ", " + y + ")");
    }
}
