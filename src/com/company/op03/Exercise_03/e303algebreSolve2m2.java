package com.company.op03.Exercise_03;
import java.util.Scanner;

class e303algebreSolve2m2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //1
        double a, b, c, d, e, f, x, y;

        //2
        System.out.print("Enter a, b, c, d, e, f:  ");
        a = input.nextDouble(); b = input.nextDouble();
        c = input.nextDouble(); d = input.nextDouble();
        e = input.nextDouble(); f = input.nextDouble();
        x = 0; y = 0;

        //3
        if ((a*d - b*c) != 0){
            x = (e*d - b*f) / (a*d - b*c);
            y = (a*f - e*c) / (a*d - b*c);

            //4.1
            System.out.println("x is " + (int) (x * 10) / 10.0
                    + " and y is " + (int) (y * 10) / 10.0);
        }else{
            //4.2
            System.out.println("The equation has no solution");
        }



    }
}