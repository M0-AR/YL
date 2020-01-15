package Exercise_04._17;

import java.util.Scanner;

public class DaysOfAMonth { // g
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a year and a month
        System.out.print( "Enter a year: " );
        int year = scanner.nextInt();
        System.out.print( "Enter a month: " );
        String month = scanner.next();
        scanner.nextLine();


        int days = 31;
        // Handel leap year
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 4 == 0)) {
            days = 29;
            System.out.println( month + " " + year + " has " + days + " days" );
        } else {
            switch (month) {
                case "Jan":
                case "Mar":
                case "May":
                case "Jul":
                case "Aug":
                case "Oct":
                case "Dec":
                    days = 31;
                    System.out.println( month + " " + year + " has " + days + " days" );
                    break;
                case "Abr":
                case "June":
                case "September":
                case "Nov":
                    days = 30;
                    System.out.println( month + " " + year + " has " + days + " days" );
                    break;
                case "Feb":
                    days = 29;
                    System.out.println( month + " " + year + " has " + days + " days" );
                    break;
                default:
                    System.out.println( month + " is not a correct month name" );
                    break;
            }

        }


    }
}
