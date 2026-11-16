// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_13._15;

import java.math.BigInteger;

public class Exercise_13_15 {

	public static void main(String[] args) {

		Rational r1 = new Rational(new BigInteger("400000"),
			new BigInteger("200000"));
		Rational r2 = new Rational(new BigInteger("200000"),
			new BigInteger("300000"));


		System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
		System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
		System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
		System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
		System.out.println(r2 + " is " + r2.doubleValue());
	}
}
