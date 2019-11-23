package com.company.Exercise_03;

import java.util.Scanner;

public class e311findNumberOfDaysInMonth { // wrong with the output of month
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        //1
        int year, month, days;
        String monthWords;

        //2
        days = 0;
        monthWords = "";
        System.out.println("Enter the year: ");
        year = input.nextInt();
        System.out.println("Enter the month: ");
        month = input.nextInt();

        //3

        switch (month){
            case 1: monthWords = "January"; days = 31; break;
            case 3: monthWords = "March";   days = 31; break;
            case 5: monthWords = "May";     days = 31; break;
            case 7: monthWords = "July";    days = 31; break;
            case 8: monthWords = "August";  days = 31; break;
            case 10: monthWords = "October";days = 31; break;
            case 12: monthWords = "December";days = 31; break;

            case 4: monthWords = "April"; days = 30; break;
            case 6: monthWords = "June";  days = 30; break;
            case 9: monthWords = "September"; days = 30; break;
            case 11: monthWords = "November"; days = 30; break;
            case 2:
                monthWords = "February";
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                    days = 29;
                }else {
                    days = 28;
                }
                break;
            default:
                System.out.println("Invalid month. ");
                break;
        }

        //4
        System.out.println(monthWords + " " + year + " has " + days + " days.");

    }
}
