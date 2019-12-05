package com.company.Exercise_03._05;

import java.util.Scanner;

public class e305FindFutureDates {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //1
        int today, futuresday;
        String dayOfWeek;

        //2
        // today + futuresday but how about after 31 day wich
        today = input.nextInt(); futuresday = input.nextInt();

        //3.01
        futuresday =(today + futuresday) % 7;



        //3.02
        switch (today){
        case 0: dayOfWeek = "Sunday"; break;
        case 1: dayOfWeek = "Monday"; break;
        case 2: dayOfWeek = "Tuesday"; break;
        case 3: dayOfWeek = "Wednesday"; break;
        case 4: dayOfWeek = "Thursday"; break;
        case 5: dayOfWeek = "Friday"; break;
        case 6: dayOfWeek = "Saturday"; break;
            default:
                throw new IllegalStateException("Unexpected value: " + today);
        }

        //3.03
        switch (futuresday) {
            case 0:
                System.out.println("Today is " + dayOfWeek + " and the future day is Sunday."); break;
            case 1:
                System.out.println("Today is " + dayOfWeek + " and the future day is Monday."); break;
            case 2:
                System.out.println("Today is " + dayOfWeek + " and the future day is Tuesday."); break;
            case 3:
                System.out.println("Today is " + dayOfWeek + " and the future day is Wednesday."); break;
            case 4:
                System.out.println("Today is " + dayOfWeek + " and the future day is Thursday."); break;
            case 5:
                System.out.println("Today is " + dayOfWeek + " and the future day is Friday."); break;
            case 6:
                System.out.println("Today is " + dayOfWeek + " and the future day is Saturday.");
        }

        //4
    }
}