package Exercise_13._04;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintCalendarTest {

    String expectedCalendar;

    @Before
    public void setUp() throws Exception {
        expectedCalendar = "           May 2016          \n" +
                           "-----------------------------\n" +
                           " Sun Mon Tue Wed Thu Fri Sat\n" +
                           "   1   2   3   4   5   6   7\n" +
                           "   8   9  10  11  12  13  14\n" +
                           "  15  16  17  18  19  20  21\n" +
                           "  22  23  24  25  26  27  28\n" +
                           "  29  30  31";
    }

    @Test
    public void testPrintCalendar() {
        // Look at the console to see the output
        new PrintCalendar().printCalendar(5, 2016);
    }
}