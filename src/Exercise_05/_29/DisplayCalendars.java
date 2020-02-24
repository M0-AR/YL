package Exercise_05._29;

public class DisplayCalendars {
    public static void main(String[] args) {
        int year = 2013;
        int daysNumber = 2;
        int monthConter = 1;
        int numberOfDaysInMonth = 31;
        String month = null;

        for (int i = 1; i <= 12; i++, monthConter++) {

            // Get the right number for year
            switch (monthConter) {
                case 1:month = "Jan";break;
                case 2:month = "Fab";break;
                case 3:month = "Mars";break;
                case 4:month = "April";break;
                case 5:month = "May";break;
                case 6:month = "Juni";break;
                case 7:month = "Juile";break;
                case 8:month = "Aug";break;
                case 9:month = "Sep";break;
                case 10:month = "Oct";break;
                case 11:month = "Nov";break;
                case 12:month = "Dec";break;
            }

            // Display Result
            System.out.println();
            System.out.printf( "%10s", month+" "+ year + "\n" ); // todo
            System.out.printf( "%5s %5s %5s %5s %5s %5s %5s", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat\n");
            switch (daysNumber) {
                case 0: break;
                case 1: System.out.printf( "%5s"," " );break;
                case 2: System.out.printf( "%10s"," " );break;
                case 3: System.out.printf( "%15s"," " );break;
                case 4: System.out.printf( "%20s"," " );break;
                case 5: System.out.printf( "%25s"," " );break;
                case 6: System.out.printf( "%30s"," " );break;
            }
            for (int j = 1; j <= numberOfDaysInMonth ; j++) {
                System.out.printf( "%5d %s", j, (( (daysNumber + j) % 7 == 0 ))? "\n":"" ); // Todo The problem from this line
            }

            // Find day's number in week
            if (monthConter == 2) {
                numberOfDaysInMonth = ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)? 29: 28);
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            } else if (monthConter == 4 || monthConter == 6 ||monthConter == 9 ||monthConter == 11  ){
                numberOfDaysInMonth = 30;
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            }else {
                numberOfDaysInMonth = 31;
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            }


        }
    }
}
