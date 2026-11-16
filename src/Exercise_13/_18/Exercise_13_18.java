// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_13._18;

import java.math.BigInteger;

public class Exercise_13_18 {

	public static void main(String[] args) {

		Rational r = new Rational(BigInteger.ONE, new BigInteger("2"));


		for (BigInteger i = r.getNumerator().add(BigInteger.ONE);
			  i.compareTo(new BigInteger("100")) <= 0;
			  i = i.add(BigInteger.ONE)) {
			r = r.add(new Rational(i, i.add(BigInteger.ONE)));
		}


		System.out.println(r);
	}
}
