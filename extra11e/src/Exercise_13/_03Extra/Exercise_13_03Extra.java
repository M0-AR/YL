package Exercise_13._03Extra;

import java.util.Scanner;

public class Exercise_13_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the first rational number: ");
		Rational r1 = Rational.parseRationalNumber(input.nextLine());
		System.out.print("Enter the second rational number: ");
		Rational r2 = Rational.parseRationalNumber(input.nextLine());

		System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
	}
}

class Rational {
	private final int numerator;
	private final int denominator;

	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero");
		}
		int sign = denominator < 0 ? -1 : 1;
		int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
		this.numerator = sign * numerator / gcd;
		this.denominator = Math.abs(denominator) / gcd;
	}

	public Rational add(Rational other) {
		return new Rational(
			numerator * other.denominator + other.numerator * denominator,
			denominator * other.denominator);
	}

	public static Rational parseRationalNumber(String s) {
		String text = s.replaceAll("\\s+", "");
		int slash = text.indexOf('/');
		if (slash >= 0) {
			return new Rational(
				Integer.parseInt(text.substring(0, slash)),
				Integer.parseInt(text.substring(slash + 1)));
		}
		return new Rational(Integer.parseInt(text), 1);
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	@Override
	public String toString() {
		return denominator == 1 ? numerator + "" : numerator + "/" + denominator;
	}
}
