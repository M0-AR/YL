package Exercise_06._06Extra;

import java.util.Scanner;

public class Exercise_06_06Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a credit card number as a string: ");
		String cardNumber = input.nextLine();

		if (isValid(cardNumber)) {
			System.out.println(cardNumber + " is valid");
		}
		else {
			System.out.println(cardNumber + " is invalid");
		}
	}

	public static boolean isValid(String cardNumber) {
		if (cardNumber.length() < 13 || cardNumber.length() > 16) {
			return false;
		}

		for (int i = 0; i < cardNumber.length(); i++) {
			if (!Character.isDigit(cardNumber.charAt(i))) {
				return false;
			}
		}

		char first = cardNumber.charAt(0);
		boolean validPrefix = first == '4' || first == '5' || first == '6'
			|| cardNumber.startsWith("37");
		if (!validPrefix) {
			return false;
		}

		return (sumOfDoubleEvenPlace(cardNumber) + sumOfOddPlace(cardNumber)) % 10 == 0;
	}

	public static int sumOfDoubleEvenPlace(String cardNumber) {
		int sum = 0;
		for (int i = cardNumber.length() - 2; i >= 0; i -= 2) {
			sum += getDigit((cardNumber.charAt(i) - '0') * 2);
		}
		return sum;
	}

	public static int getDigit(int number) {
		return number / 10 + number % 10;
	}

	public static int sumOfOddPlace(String cardNumber) {
		int sum = 0;
		for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
			sum += cardNumber.charAt(i) - '0';
		}
		return sum;
	}
}
