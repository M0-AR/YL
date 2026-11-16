// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_20._18;

import java.io.File;
import java.util.*;

public class Exercise_20_18 {
	public static void main(String[] args) {

		System.out.print("Enter a directory or a file: ");
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();


		System.out.println(getSize(new File(directory)) + " bytes");
	}


	public static long getSize(File file) {

		Queue<File> queue = new LinkedList<>();
		long size = 0;

		addDirectory(queue, file);

		while (!queue.isEmpty()) {

			File t = queue.remove();
			if (t.isFile())
				size += t.length();
			else
				addDirectory(queue, t);
		}

		return size;
	}


	private static void addDirectory(
			Queue<File> queue, File directory) {
		for (File file: directory.listFiles()) {
			queue.offer(file);
		};
	}
}
