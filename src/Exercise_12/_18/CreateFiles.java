// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._18;

import java.util.*;
import java.io.*;

public class CreateFiles {

	public static void main(String[] args) throws Exception {

		ArrayList<File> directories = new ArrayList<>();


		addDirectories(directories);


		makeFiles(directories);
	}


	public static void makeFiles(ArrayList<File> d)
		throws FileNotFoundException {
		for (int i = 0; i < d.size(); i++) {
			makeFiles(3, d.get(i), "TestJavafile", ".java");
			makeFiles(2, d.get(i), "TestTextfile", ".txt");
			makeFiles(2, d.get(i), "TestDatfile", ".dat");
		}
	}


	public static void makeFiles(
		int n, File dir, String fileName, String ext)
		throws FileNotFoundException {
		for (int j = 1; j <= n; j++) {
			File file = new File(dir, fileName + j + ext);
			if(!file.exists()) {
				try (

						PrintWriter output = new PrintWriter(file);
				) {

					for (int i = 0; i < 100; i++) {
						output.print(((int)(Math.random() * 500) + 1));
						output.print(" ");
					}
				}
			}
		}
	}


	public static void addDirectories(ArrayList<File> list) {
		for (int i = 1; i <= 34; i++) {

			File dir = new File("srcRootDirectory/chapter" + i);
			if (!dir.isDirectory()) {
				if (dir.mkdirs())
					list.add(dir);
			}
		}
	}
}
