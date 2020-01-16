package Exercise_04._17;
/*
(Days of a month) Write a program that prompts the user to enter a year and the
first three letters of a month name (with the first letter in uppercase) and displays
the number of days in the month.
*/
import java.util.Scanner;

public class DaysOfAMonth { // done
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
                    days = 28;
                    System.out.println( month + " " + year + " has " + days + " days" );
                    break;
                default:
                    System.out.println( month + " is not a correct month name" );
                    break;
            }

        }

        // Second solution
        // Test for leap year
        boolean leapYear =
                ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);

        // Display the number of day in the month
        System.out.print(month + " " + year + " has ");

        if ( month.equals("Jan") || month.equals("Mar") ||
                month.equals("May") || month.equals("Jul") ||
                month.equals("Aug") || month.equals("Oct") ||
                month.equals("Dec"))
            System.out.println(31 + " days");
        else if (month.equals("Apr") || month.equals("Jun") ||
                month.equals("Sep") || month.equals("Nov"))
            System.out.println(30 + " days");
        else
            System.out.println(((leapYear) ? 29 : 28) + " days");


    }
}
