package Exercise_05._12Extra;

public class Exercise_05_12Extra {
	public static void main(String[] args) {
		System.out.println("Degree\tSin\t\tCos");

		for (int degree = 0; degree <= 360; degree += 10) {
			double radians = Math.toRadians(degree);
			double sin = Math.sin(radians);
			double cos = Math.cos(radians);
			if (Math.abs(sin) < 0.00005) {
				sin = 0;
			}
			if (Math.abs(cos) < 0.00005) {
				cos = 0;
			}
			System.out.printf("%-3d\t%6.4f\t\t%6.4f%n", degree, sin, cos);
		}
	}
}
