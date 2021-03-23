package Exercise_09._05;

import java.util.GregorianCalendar;

public class GregorianCalendarAPI {
    public static void main(String[] args) {
       GregorianCalendar gregorianCalendar = new GregorianCalendar();

       // TODO Display the right output according to the question
       System.out.println(GregorianCalendar.YEAR + ":" +
                GregorianCalendar.MONTH + ":" + GregorianCalendar.DAY_OF_MONTH);

        gregorianCalendar.setTimeInMillis( 12345678765L );
        System.out.println(gregorianCalendar.getTimeInMillis());

    }
}
