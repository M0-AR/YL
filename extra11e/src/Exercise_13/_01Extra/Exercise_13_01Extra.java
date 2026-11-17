package Exercise_13._01Extra;

import java.util.Scanner;

public class Exercise_13_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter time1 (hour minute second): ");
		Time time1 = new Time(input.nextInt(), input.nextInt(), input.nextInt());
		System.out.println(time1);
		System.out.println("Elapsed seconds in time1: " + time1.getSeconds());

		System.out.print("\nEnter time2 (elapsed time): ");
		Time time2 = new Time(input.nextLong());
		System.out.println(time2);
		System.out.println("Elapsed seconds in time2: " + time2.getSeconds());

		System.out.println("\ntime1.compareTo(time2)? "
			+ time1.compareTo(time2));

		System.out.println("\ntime3 is created as a clone of time1");
		Time time3 = (Time)time1.clone();
		System.out.println("time1.compareTo(time3)? "
			+ time1.compareTo(time3));
	}
}

class Time implements Comparable<Time>, Cloneable {
	private long seconds;

	public Time() {
		seconds = System.currentTimeMillis() / 1000;
	}

	public Time(int hour, int minute, int second) {
		seconds = hour * 3600L + minute * 60L + second;
	}

	public Time(long seconds) {
		this.seconds = seconds;
	}

	public long getSeconds() {
		return seconds;
	}

	public int getHour() {
		return (int)(seconds / 3600 % 24);
	}

	public int getMinute() {
		return (int)(seconds / 60 % 60);
	}

	public int getSecond() {
		return (int)(seconds % 60);
	}

	@Override
	public String toString() {
		return getHour() + (getHour() == 1 ? " hour " : " hours ")
			+ getMinute() + (getMinute() == 1 ? " minute " : " minutes ")
			+ getSecond() + (getSecond() == 1 ? " second" : " seconds");
	}

	@Override
	public int compareTo(Time other) {
		return (int)(seconds - other.seconds);
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
