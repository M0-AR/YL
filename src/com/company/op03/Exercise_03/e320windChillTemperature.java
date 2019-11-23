package com.company.op03.Exercise_03;
/*
(Science: wind-chill temperature) Programming Exercise 2.17 gives a formula
to compute the wind-chill temperature. The formula is valid for temperatures in
the range between −58ºF and 41ºF and wind speed greater than or equal to 2.
Write a program that prompts the user to enter a temperature and a wind speed.
The program displays the wind-chill temperature if the input is valid; otherwise,
it displays a message indicating whether the temperature and/or wind speed is
invalid.
*/
import java.text.DecimalFormat;
import java.util.Scanner;

public class e320windChillTemperature { // look at github
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        //1
//        twc = wind-chill temperature
        double temperature, windSpeed, twc;

        //2
        twc = 0;
//        Prompt the user to enter temperature between -58 og 41
        System.out.println("Enter temperature between -58 og 41 in Fahrenheit: ");
        temperature = input.nextDouble();
//        Prompt the user to enter wind speed greater than or equal to 2
        System.out.println("Enter wind speed miles per hour, greater than or equal to 2: ");
        windSpeed = input.nextDouble();

        //3
        if((temperature < -58 || temperature > 41) || windSpeed <2)
        {
            System.out.println("The ");
            if (temperature <= -58 && temperature >= 41)
                System.out.println("temperature ");
            if ((temperature <= -58 && temperature >= 41) && windSpeed <2)
                System.out.println("and ");
            if (windSpeed < 2)
                System.out.println("wind speed ");
            System.out.println("is invalid.");
            System.exit(0);
        }

//        Compute the white chill index
        twc = 35.72 + 0.6215*temperature - 35.75*Math.pow(windSpeed, 0.16) + 0.4275*temperature*Math.pow(windSpeed, 0.16);


        //4

        System.out.println(twc);
//        In short, the %.2f syntax tells Java to return your variable
//        (val) with 2 decimal places (.2) in decimal representation of
//        a floating-point number (f) from the start of the format specifier (%).
        System.out.printf("%.4f", twc);

    }
}
