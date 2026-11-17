package Exercise_10._01Extra;

import java.math.BigInteger;
import java.util.Scanner;

public class Exercise_10_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter n: ");
		int n = input.nextInt();
		System.out.print("Enter k: ");
		int k = input.nextInt();

		System.out.println("The C(n, k) is " + getNumberOfCombinations(n, k));
	}

	public static BigInteger getNumberOfCombinations(int n, int k) {
		return factorial(n).divide(factorial(k).multiply(factorial(n - k)));
	}

	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}
}
