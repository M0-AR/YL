// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_24._04;

public class Exercise_24_04 {
	public static void main(String[] args) {

		GenericStack<Integer> stack = new GenericStack<>();
		final int NUMBER_OF_PRIMES = 50;
		int count = 0;
		for (int i = 2; count < NUMBER_OF_PRIMES; i++) {
			if (isPrime(i)) {
				stack.push(i);
				count++;
			}
		}


		System.out.println("The first 50 prime numbers in descending order: ");
		for (int i = 1; !stack.isEmpty(); i++){
			if (i % 10 == 0)
				System.out.printf("%3d\n", stack.pop());
			else
				System.out.printf("%3d ", stack.pop());
		}
		System.out.println();
	}


	public static boolean isPrime(int n) {
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
