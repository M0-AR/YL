package Exercise_07._12Extra;

import java.util.Scanner;

public class Exercise_07_12Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter today's day: ");
		int today = input.nextInt();
		System.out.print("Enter the number of days elapsed since today: ");
		int elapsedDays = input.nextInt();

		String[] dayNames = {
			"Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday"
		};

		int futureDay = (today + elapsedDays) % 7;
		System.out.println("Today is " + dayNames[today]
			+ " and the future day is " + dayNames[futureDay]);
	}
}
