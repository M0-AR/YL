package Exercise_13._04Extra;

import java.util.Scanner;

public class Exercise_13_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the first complex number: ");
		Complex c1 = Complex.parseComplexNumber(input.nextLine());
		System.out.print("Enter the second complex number: ");
		Complex c2 = Complex.parseComplexNumber(input.nextLine());

		System.out.println("(" + c1 + ") + (" + c2 + ") = " + c1.add(c2));
	}
}

class Complex {
	private final double real;
	private final double imaginary;

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public Complex(double real) {
		this(real, 0);
	}

	public double getRealPart() {
		return real;
	}

	public double getImaginaryPart() {
		return imaginary;
	}

	public Complex add(Complex other) {
		return new Complex(real + other.real, imaginary + other.imaginary);
	}

	public static Complex parseComplexNumber(String s) {
		String text = s.replace('–', '-').replaceAll("\\s+", "");
		int iIndex = text.indexOf('i');
		if (iIndex < 0) {
			return new Complex(Double.parseDouble(text));
		}

		String withoutI = text.substring(0, iIndex);
		int sign = Math.max(withoutI.indexOf('+', 1),
			withoutI.indexOf('-', 1));
		if (sign >= 0) {
			double realPart = Double.parseDouble(withoutI.substring(0, sign));
			return new Complex(realPart,
				parseImaginary(withoutI.substring(sign)));
		}

		return new Complex(0, parseImaginary(withoutI));
	}

	private static double parseImaginary(String text) {
		if (text.isEmpty() || text.equals("+")) {
			return 1;
		}
		if (text.equals("-")) {
			return -1;
		}
		return Double.parseDouble(text);
	}

	@Override
	public String toString() {
		if (imaginary == 0) {
			return real + "";
		}
		if (real == 0) {
			if (imaginary == 1) {
				return "i";
			}
			if (imaginary == -1) {
				return "-i";
			}
			return imaginary + "i";
		}
		if (imaginary == 1) {
			return real + " + i";
		}
		if (imaginary == -1) {
			return real + " - i";
		}
		if (imaginary < 0) {
			return real + " - " + Math.abs(imaginary) + "i";
		}
		return real + " + " + imaginary + "i";
	}
}
