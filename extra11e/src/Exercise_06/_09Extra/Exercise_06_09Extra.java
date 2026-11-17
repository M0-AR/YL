package Exercise_06._09Extra;

import java.util.Scanner;

public class Exercise_06_09Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter total seconds: ");
		long seconds = input.nextLong();
		System.out.println("The hours, minutes, and seconds for total seconds "
			+ seconds + " is " + format(seconds));
	}

	public static String format(long seconds) {
		long hours = seconds / 3600 % 24;
		long minutes = seconds / 60 % 60;
		long remainingSeconds = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
	}
}
