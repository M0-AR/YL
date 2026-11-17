package Exercise_06._01Extra;

public class Exercise_06_01Extra {
	public static void main(String[] args) {
		long days = System.currentTimeMillis() / 1000 / 86400 + 1;

		int year = 1970;
		while (days > (isLeapYear(year) ? 366 : 365)) {
			days -= isLeapYear(year) ? 366 : 365;
			year++;
		}

		int month = 1;
		while (days > getDaysInMonth(year, month)) {
			days -= getDaysInMonth(year, month);
			month++;
		}

		System.out.println("The current date is " + getMonthName(month) + " "
			+ days + ", " + year);
	}

	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

	public static int getDaysInMonth(int year, int month) {
		switch (month) {
			case 2:
				return isLeapYear(year) ? 29 : 28;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 31;
		}
	}

	public static String getMonthName(int month) {
		String[] names = {
			"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"
		};
		return names[month - 1];
	}
}
