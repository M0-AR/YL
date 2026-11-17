package Exercise_13._05Extra;

public class Exercise_13_05Extra {
	public static void main(String[] args) {
		Point1 p1 = new Point1(3, 4);
		Point1 p2 = new Point1(3.4, 1.4);
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p1));
		System.out.println(p1.compareTo(p2));
		System.out.println(p2.compareTo(p1));

		Point1 p3 = (Point1)(p1.clone());
		System.out.println(p3.equals(p1));
		System.out.println(p3);
	}
}

class Point1 implements Comparable<Point1>, Cloneable {
	private final double x;
	private final double y;

	public Point1() {
		this(0, 0);
	}

	public Point1(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Point1)) {
			return false;
		}
		Point1 other = (Point1)object;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() {
		return 31 * Double.hashCode(x) + Double.hashCode(y);
	}

	@Override
	public int compareTo(Point1 other) {
		if (x != other.x) {
			return Double.compare(x, other.x);
		}
		return Double.compare(y, other.y);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException ex) {
			return null;
		}
	}
}
