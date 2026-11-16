// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_27._10;

public class Exercise_27_10 {
	static final int VALUES = 1000000;

	public static void main(String[] args) {
		MySet<Double> set = new MyHashSet<>();
		MyList<Double> arrayList = new MyArrayList<>();


		for (int i = 0; i < VALUES; i++) {
			double randomDouble = Math.random() * VALUES;
			set.add(randomDouble);
			arrayList.add(randomDouble);
		}


		double[] list = new double[VALUES];
		for (int i = 0; i < list.length; i++) {
			list[i] = Math.random() * VALUES;
		}

		System.out.println("Total time for the hash set: " +
			testTimeSet(list, set) + " milliseconds");

		System.out.println("Total time for the array list: " +
			testTimeArray(list, arrayList) + " milliseconds");
	}


	public static long testTimeSet(double[] list, MySet<Double> set) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < VALUES; i++) {
			set.contains(list[i]);
		}
		return System.currentTimeMillis() - startTime;
	}


	public static long testTimeArray(double[] list, MyList<Double> array) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < VALUES; i++) {
			array.contains(list[i]);
		}
		return System.currentTimeMillis() - startTime;
	}
}
