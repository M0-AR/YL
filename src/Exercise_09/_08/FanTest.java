package Exercise_09._08;

public class FanTest {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setRadius( 5 );
        fan1.setSpeed( Fan.FAST );
        fan1.setOn( true );
        fan1.setColor( "White" );
        System.out.println(fan1.toString());

        System.out.println();

        Fan fan2 = new Fan();
        fan2.setRadius( 5 );
        fan2.setOn( false );
        fan2.setColor( "Black" );
        System.out.println(fan2.toString());
    }
}
