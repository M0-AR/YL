package Exercise_06._36;

import java.util.Scanner;

public class AreaOfRegularPolygon { // github and y
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );


        // Prompt the user to enter a number of size
        System.out.print( "Enter the number of sides: " );
        int n = input.nextInt();

        // Prompt the user to enter a side
        System.out.print( "Enter the side: " );
        double side = input.nextDouble();

        // Display result
        System.out.println("The area of the pentagon is "+area(n, side));
    }

    /** Compute the area of pentagon, where n is the number of side*/
    public static double area(int n,double side){
        return (5 * Math.pow( side, 2 )) / (4 * Math.tan( Math.PI/ n ));
    }



}
