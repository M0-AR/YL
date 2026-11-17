package Exercise_03._03Extra;

public class Exercise_03_03Extra {
	public static void main(String[] args) {
		double x;
		double y;
		do {
			x = Math.random() * 10 - 5;
			y = Math.random() * 10 - 5;
		} while (x * x + y * y > 25);

		double distance = Math.sqrt(x * x + y * y);
		System.out.println("The point is (" + x + ", " + y
			+ ") and its distance to the center is " + distance);
	}
}
