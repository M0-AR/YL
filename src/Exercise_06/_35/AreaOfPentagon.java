package Exercise_06._35;

import java.util.Scanner;

public class AreaOfPentagon { // github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a side
        System.out.println( "Enter the side: " );
        double side = input.nextDouble();

        System.out.println("The area of the pentagon is "+area( side));
    }

    /** Compute the area of pentagon*/
    public static double area(double side){
        return (5 * Math.pow( side, 2 )) / (4 * Math.tan( Math.PI/ 5 ));
    }
}
