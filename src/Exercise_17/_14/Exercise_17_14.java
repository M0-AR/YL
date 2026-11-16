// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_17._14;

import java.util.Scanner;
import java.io.*;

public class Exercise_17_14 {
	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter input flie name: ");
		String inFile = input.next();
		System.out.print("Enter output flie name: ");
		String outFile = input.next();

		try (
			RandomAccessFile source = new RandomAccessFile(inFile, "r");
			RandomAccessFile target = new RandomAccessFile(outFile, "rw");
		) {


			int r = 0;
			source.seek(0);
			while ((r = source.read()) != -1) {
				target.write(((byte)r) + 5);
			}
		}

	}
}
