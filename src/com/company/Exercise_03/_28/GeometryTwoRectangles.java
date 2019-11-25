package com.company.Exercise_03._28;

import java.util.Scanner;

public class GeometryTwoRectangles {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Rectangle number 1
        double width1, height1, centerX1, centerY1;

        // Rectangle number 2
        double width2, height2, centerX2, centerY2;

        // Prompt the user to enter two rectangles
        for (int i = 1; i <= 2 ; i++) {
            System.out.print("Enter r"+i+"'s center x-, y-coordinates, width, and height: ");
            if (i == 1){
                width1 = input.nextDouble(); height1 = input.nextDouble();
                centerY1 = input.nextDouble(); centerY1 = input.nextDouble();
            }else {
                width2 = input.nextDouble(); height2 = input.nextDouble();
                centerY2 = input.nextDouble(); centerY2 = input.nextDouble();
            }
        }
        //
    }
}
