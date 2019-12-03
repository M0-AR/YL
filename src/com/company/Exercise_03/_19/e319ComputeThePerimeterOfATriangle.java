package com.company.Exercise_03._19;

import java.util.Scanner;

/*
(Compute the perimeter of a triangle) Write a program that reads three edges for
a triangle and computes the perimeter if the input is valid. Otherwise, display that
the input is invalid. The input is valid if the sum of every pair of two edges is
greater than the remaining edge.
*/
public class e319ComputeThePerimeterOfATriangle {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        //1
        double x1,y1,x2,y2,x3,y3,side1,side2,side3;

        //2
        // Prompt the user to enter three edges for a triangle:
        System.out.println("Enter three edges for a triangle:");
        System.out.println("Edge 1 points x, y: ");
         x1 = input.nextDouble();
         y1 = input.nextDouble();
        System.out.println("Edge 2 points x, y: ");
         x2 = input.nextDouble();
         y2 = input.nextDouble();
        System.out.println("Edge 3 points x, y: ");
         x3 = input.nextDouble();
         y3 = input.nextDouble();

         // Test if input is valid
         boolean valid = (x1+y1>x3+y3 && x2+y2>x3+y3) || (x1+y1>x2+y2 && x3+y3>x2+y2) || (x3+y3>x1+y1 && x2+y2>x1+y1);

        //3
        if(!valid){
            System.out.println("The input is invalid");
            System.exit(0);
        }

        // Compute the sides of the triangle
        side1 = Math.pow(Math.pow(x1-x3,2) + Math.pow(y1-y3,2), 0.5);
        side2 = Math.pow(Math.pow(x3-x2,2) + Math.pow(y3-y2,2), 0.5);
        side3 = Math.pow(Math.pow(x2-x1,2) + Math.pow(y2-y1,2), 0.5);

        //4
        System.out.println("Perimeter of triangle is " + (side1+side2+side3));
    }
}
