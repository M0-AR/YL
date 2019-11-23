package com.company.op03.Exercise_03;

/**********************************************************************************
 * (Random point) Write a program that displays a random coordinate in a rectangle.
 * The rectangle is centered at (0, 0) with width 100 and height 200.
 *********************************************************************************/

public class e316RandomPoint {
    public static void main(String[]args){

        //1
        double width, height;

        //2
        // Generate random width and height
        width = (int)(Math.random() * 100 - 50);
        height = (int)(Math.random() * 200 - 100);

        //3


        //4
        // Display coordinate
        System.out.println("Random coordinate in rectangle.");
        System.out.println(width + ", " + height);
    }
}
