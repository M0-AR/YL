package Exercise_05._35;
/*
(Summation) Write a program to compute the following summation.
1 / (1 + √2) + 1 / (√2 + √3) + 1 / (√3 + √4) + ... +  1 / (√624 + √625)
*/
public class Summation {
    public static void main(String[] args) { // github
        double sum = 0;

        for (int i = 1; i <= 625 ; i++) {
            sum =  1.0 / (Math.sqrt( i ) + Math.sqrt( i +1 ));
        }

        // Display result
        System.out.println("Summation: "+sum);
    }
}
