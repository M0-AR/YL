// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._12;

import java.util.*;

public class Exercise_21_12 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a file name for baby name ranking: ");
		String fileName = input.next();


		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		try {
			java.net.URL url = new java.net.URL(
				"http://www.cs.armstrong.edu/liang/data/" + fileName);


			Scanner inputStream = new Scanner(url.openStream());
			while (inputStream.hasNext()) {
				inputStream.next();
				set1.add(inputStream.next());
				inputStream.next();
				set2.add(inputStream.next());
				inputStream.next();
			}
		}
		catch (java.net.MalformedURLException ex) {
			System.out.println("Invalid URL");
		}
		catch (java.io.IOException ex) {
			System.out.println("I/O Errors; no such file");
		}


		set1.retainAll(set2);
		System.out.println(set1.size() + " names used for both genders");
		System.out.print("They are ");
		for (String name: set1) {
			System.out.print(name + " ");
		}
		System.out.println();
	}
}
