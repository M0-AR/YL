package com.company.Exercise_03;
/*
(Geometry: point in a rectangle?) Write a program that prompts the user to enter
a point (x, y) and checks whether the point is within the rectangle centered at
(0, 0) with width 10 and height 5. For example, (2, 2) is inside the rectangle and
(6, 4) is outside the rectangle, as shown in Figure 3.7b. (Hint: A point is in the
rectangle if its horizontal distance to (0, 0) is less than or equal to 10 / 2 and its
vertical distance to (0, 0) is less than or equal to 5.0 / 2. Test your program to
cover all cases.) Here are two sample runs.
*/
import java.util.Scanner;// I don't understand the logic in //3

public class e323pointInARectangle { // check github
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        //1
        double x, y;
        boolean inOrOutRectangle;

        //2
//        Prompt the user to enter a point
        System.out.println("Enter a point with two coordinates: ");
        x = input.nextDouble();
        y = input.nextDouble();

        //3
//        Compute the point
//        inOrOutRectangle = (x < (10/2) && y < (5.0/2));

        inOrOutRectangle = (Math.pow(Math.pow(x, 2), 0.5) <= 10 / 2 ) || (Math.pow(Math.pow(y, 2), 0.5) <= 5.0 / 2);

        //4
        System.out.println(x +".." + y);
        System.out.println("Point ("+x+", "+y+") is "+ ((inOrOutRectangle) ? "in ": " not in ") + "the rectangle");
    }
}
