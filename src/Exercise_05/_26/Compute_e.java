package Exercise_05._26;
/*
(Compute e) You can approximate e using the following series:
		e = 1 + 1 / 1! + 1 / 2! + 1 / 3! + 1 / 4! + ... + 1 / i!
Write a program that displays the e value for i = 10000, 20000, …, and
100000. (Hint: Because i! = i * (i - 1) * c * 2 * 1, then
		1 / i! is 1 / (i(i - 1)!)
Initialize e and item to be 1 and keep adding a new item to e. The new item is
the previous item divided by i for i = 2, 3, 4, ....)
*/
public class Compute_e {
    public static void main(String[] args) { // github and y
        int k = 10000; // k is 10000, 20000,..., and 100000
        while (k <= 100000) {
            double e, factorial;
            e = factorial = 1;

            for (int i = 1; i < k; i++) {
                factorial *= i;
                e += 1 / factorial;
            }

            System.out.println( e );

            k+=10000;
        }
    }
}
