// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._26;

import java.util.*;
import java.io.*;

public class Exercise_12_26 {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter directory name: ");
		File dir = new File(input.next());


		if (dir.isDirectory()) {
			System.out.println("Directory already exists");
			System.exit(0);
		}


		if (dir.mkdirs()) {
			System.out.println("Directory created successfully");
		}
	}
}
