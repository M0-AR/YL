package Exercise_05._29;

import java.util.Scanner;

/*
(Display calendars) Write a program that prompts the user to enter the year and
first day of the year and displays the calendar table for the year on the console. For
example, if the user entered the year 2013, and 2 for Tuesday, January 1, 2013,
your program should display the calendar for each month in the year, as follows:
									January 2013
							Sun Mon Tue Wed Thu Fri Sat
							 		1   2   3   4   5
							 6   7   8   9  10  11  12
							13  14  15  16  17  18  19
							20  21  22  23  24  25  26
							27  28  29  30  31
*/
public class DisplayCalendars {
    public static void main(String[] args) {
        // Create Scanner
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the year and first day of the year
        System.out.print("Enter year: ");
        int year = input.nextInt();
        System.out.print("Enter first day of the year: ");
        int daysNumber = input.nextInt();


        int monthConter = 1;
        int numberOfDaysInMonth = 31;
        String[] month;

        for (int i = 0; i < 12; i++, monthConter++) {

            // Get the right number for year
            // Get the right month
            month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            // Display Result
            System.out.println();
            System.out.printf( "%20s %s %s %s", month[i], " ", year
                        ,"\n-----------------------------------------------\n "); // todo
            System.out.printf( "%5s %5s %5s %5s %5s %5s %5s", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat\n");
            switch (daysNumber) {
                case 0: break;
                case 1: System.out.printf( "%7s"," " );break;
                case 2: System.out.printf( "%12s"," " );break;
                case 3: System.out.printf( "%17s"," " );break;
                case 4: System.out.printf( "%22s"," " );break;
                case 5: System.out.printf( "%27s"," " );break;
                case 6: System.out.printf( "%32s"," " );break;
            }
            for (int j = 1; j <= numberOfDaysInMonth ; j++) {
                System.out.printf( "%5d %s", j, (( (daysNumber + j) % 7 == 0 ))? "\n":"" ); // Todo The problem from this line
            }

            // Find day's number in week
            if (monthConter == 2) {
                numberOfDaysInMonth = ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)? 29: 28);
            } else if (monthConter == 4 || monthConter == 6 ||monthConter == 9 ||monthConter == 11  ){
                numberOfDaysInMonth = 30;
            }else {
                numberOfDaysInMonth = 31;
            }

            // Get the start day for the next month
            daysNumber = (daysNumber + numberOfDaysInMonth) % 7;



        }
    }
}
