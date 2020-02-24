package Exercise_05._25;

/*
(Compute p) You can approximate p by using the following series:
PI = 4(1 - 1 / 3 + 1 / 5 - 1 / 7 + 1 / 9 - 1 / 11 + ... + ((-1)i + 1 /  2i - 1))
Write a program that displays the p value for i = 10000, 20000, …, and
100000.
*/
public class ComputePi {
    public static void main(String[] args) {
        // Compute PI value for i = 10000, 20000,..., and 100000
        int i = 10000;
        while (i <= 100000){
            double pi = 0;
            int counter = 0;
            for (int j = 1; j < 10000*2; j += 2) {
                pi += 1.0 / j;
                j += 2;
                pi -= 1.0 / j;
            }

            // Display result
            System.out.println(pi * 4);
            i+=10000;
        }

    }
}
