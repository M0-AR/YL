package Exercise_05._35;

public class Summation {
    public static void main(String[] args) { // github
        double sum =  1.0/ 1 + Math.sqrt( 2 );

        for (int i = 2; i <= 625 ; i++) {
            sum =  1.0 / (Math.sqrt( i ) + Math.sqrt( i +1 ));
        }

        // Display result
        System.out.println(sum);
    }
}
