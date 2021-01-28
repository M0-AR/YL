package Exercise_10._01;

public class TimeTest {
    public static void main(String[] args) {
        Time time1 = new Time();
        Time time2 = new Time( 555550000 );
        Time time3 = new Time( 5, 23, 55 );

        System.out.println( time1.getHour() + ":" +
                            time1.getMinute() + ":" +
                            time1.getSecond() );

        System.out.println( time2.getHour() + ":" +
                            time2.getMinute() + ":" +
                            time2.getSecond() );

        System.out.println( time3.getHour() + ":" +
                            time3.getMinute() + ":" +
                            time3.getSecond() );
    }

}
