package Exercise_07._11Extra;

public class Exercise_07_11Extra {
	public static void main(String[] args) {
		String[] monthNames = {
			"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"
		};

		int month = (int)(Math.random() * 12);
		System.out.println("Month is " + monthNames[month]);
	}
}
