// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._08;

import java.util.Scanner;

public class Exercise_02_08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter the time zone offset to GMT: ");
		int offset = input.nextInt();


		long totalMilliseconds = System.currentTimeMillis();


		long totalSeconds = totalMilliseconds / 1000;


		long currentSecond = totalSeconds % 60;


		long totalMinutes = totalSeconds / 60;


		long currentMinute = totalMinutes % 60;


		long totalHours = totalMinutes / 60;


		long currentHour = totalHours % 24;
		currentHour = currentHour + offset;


		System.out.println("Current time is " + currentHour + ":"
			+ currentMinute + ":" + currentSecond);
	}
}
