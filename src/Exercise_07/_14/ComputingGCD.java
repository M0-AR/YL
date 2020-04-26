package Exercise_07._14;
/*********************************************************************************
 * (Computing gcd) Write a method that returns the gcd of an unspecified number   *
 * of integers. The method header is specified as follows:                        *
 * public static int gcd(int... numbers)                                          *
 * Write a test program that prompts the user to enter five numbers, invokes the  *
 * method to find the gcd of these numbers, and displays the gcd.                 *
 *********************************************************************************/
public class ComputingGCD {
    public static void main(String[] args) {
        System.out.print("Enter five integers: ");
        java.util.Scanner input = new java.util.Scanner(System.in);

        int[] numbers = new int[5];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = input.nextInt();

        System.out.println("The gcd of these five integers is " +
                gcd(numbers));
    }


    public static int gcd(int... numbers){
        int g = numbers[0];

        for (int i = 0; i < numbers.length ; i++)
            g = gcd(g, numbers[i]);

        return g;

    }


    /** Return the gcd of two integers */
    public static int gcd(int n1, int n2){
        int gcd = 1; // Initial gcd is 1
        int k = 2;   // Possible gcd

        while (k <= n1 && k <= n2){
            if (n1 % k == 0 && n2 % k == 0)
                gcd = k; // Update gcd
            k++;
        }

        return gcd; // Return gcd
    }
}
