package Exercise_22._04Extra;

import java.math.BigInteger;
import java.util.Scanner;

public class Exercise_22_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an index for the Fibonacci number: ");
		int index = input.nextInt();

		System.out.println("Fibonacci number at index " + index + " is "
			+ fib(index));
	}

	public static BigInteger fib(long n) {
		BigInteger f0 = BigInteger.ZERO;
		BigInteger f1 = BigInteger.ONE;
		BigInteger f2 = BigInteger.ONE;

		if (n == 0) {
			return f0;
		}
		if (n == 1) {
			return f1;
		}
		if (n == 2) {
			return f2;
		}

		for (long i = 3; i <= n; i++) {
			f0 = f1;
			f1 = f2;
			f2 = f0.add(f1);
		}
		return f2;
	}
}
