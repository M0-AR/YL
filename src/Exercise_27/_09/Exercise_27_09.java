// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_27._09;

public class Exercise_27_09 {
	public static void main(String[] args) {

		String[] list = {"Mark", "Smith", "tops", "pots", "tod", "dot"};


		for (String e : list) {
			System.out.println("The hash code for \"" + e +
				"\" is " + hashCodeForString(e));
		}

	}


	public static int hashCodeForString(String s) {
		int b = 31;
		int hashCode = 0;
		for (int i = 0; i < s.length(); i++) {
			hashCode = b * hashCode + (int)s.charAt(i);
		}
		return hashCode;
	}
}
