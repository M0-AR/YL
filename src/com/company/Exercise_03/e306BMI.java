package com.company.Exercise_03;

import java.util.Scanner;

public class e306BMI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        //1
        final double KILOGRAMS_PER_POUND, METERS_PER_INCH;
        double weight, feet, inches, height, bmi;

        //2
        KILOGRAMS_PER_POUND = 0.45359237; // constant
        METERS_PER_INCH = 0.0254;         // constant

        // Prompt the user to enter weight in pounds
        System.out.print("Enter weight in pounds: ");
        weight = input.nextDouble();
        // Prompt the user to enter height
        System.out.print("Enter feet: ");
        feet = input.nextDouble();
        System.out.print("Enter inches: ");
        inches = input.nextDouble();

        //3
        // Compute Height and BMI
        height = feet * 12 + inches;
        bmi = weight * KILOGRAMS_PER_POUND / (Math.pow(height * METERS_PER_INCH, 2));

        //4
        // Display result
        System.out.println("BMI is " + bmi);
        if (bmi < 18.5)
            System.out.println("Underweight");
        else if (bmi < 25)
            System.out.println("Normal");
        else if (bmi < 30)
            System.out.println("Overweight");
        else
            System.out.println("Obese");

    }
}