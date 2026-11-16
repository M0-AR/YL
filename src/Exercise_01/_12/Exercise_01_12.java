// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_01._12;

public class Exercise_01_12 {
	public static void main(String[] agrs) {
		System.out.println("Miles / (hour + (minutes / 60) + (seconds / 3600)) * 1.6");
		System.out.println("24    / (1    + (40      / 60) + (35      / 3600))  * 1.6");
		System.out.println((24 / (1 + (40 / 60.0) + (35 / 3600.0))) * 1.6);
	}
}
